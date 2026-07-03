package MailSending;

import Models.EmailTemplate;
import Models.MailHistory;
import Models.MailQueue;
import Models.MailTask;
import Models.SystemLog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MailService {
    private static final int MAX_RETRY = 3;
    private final MailQueue mailQueue;
    private final Map<String, EmailTemplate> templateStore = new HashMap<>();
    private final List<MailHistory> historyStore = new ArrayList<>();
    private final List<SystemLog> systemLogs = new ArrayList<>();
    private final Random random = new Random();

    public MailService(MailQueue mailQueue) {
        this.mailQueue = mailQueue;
    }

    public void registerTemplate(EmailTemplate template) {
        templateStore.put(template.getTemplateId(), template);
    }

    public void processQueue() {
        System.out.println("\n=== Bắt đầu xử lý hàng đợi gửi mail (" + mailQueue.size() + " tác vụ) ===");
        while (!mailQueue.isEmpty()) {
            MailTask task = mailQueue.dequeue();
            if (task == null) continue;
            if (sendMail(task)) {
                handleSuccess(task);
            } else {
                handleFailure(task, "SMTP trả về lỗi hoặc timeout khi gửi tới " + task.getRecipientEmail());
            }
        }
        System.out.println("=== Hoàn tất xử lý hàng đợi ===\n");
    }

    public boolean sendMail(MailTask task) {
        EmailTemplate template = templateStore.get(task.getTemplateId());
        String time = LocalDateTime.now().toString().substring(11, 19);
        if (template == null) {
            task.updateStatus("FAILED");
            logError("Không tìm thấy mẫu thư '" + task.getTemplateId() + "' cho tác vụ " + task.getTaskId());
            return false;
        }
        System.out.println("[" + time + "] -> Đang xử lý gửi mail tới: " + task.getRecipientEmail()
                + " | Tiêu đề: " + template.getSubject() + " | Lần thử: " + (task.getRetryCount() + 1));
        return random.nextInt(100) < 80;
    }

    private void handleSuccess(MailTask task) {
        task.updateStatus("SENT");
        MailHistory history = new MailHistory();
        history.recordHistory(task, "SUCCESS");
        historyStore.add(history);
        System.out.println("   [OK] Gửi thành công tác vụ: " + task.getTaskId());
    }

    private void handleFailure(MailTask task, String reason) {
        task.incrementRetry();
        if (task.getRetryCount() < MAX_RETRY) {
            task.updateStatus("RETRY");
            System.out.println("   [WARN] Thất bại, đưa tác vụ " + task.getTaskId() + " trở lại hàng đợi (Lần " + task.getRetryCount() + ")");
            mailQueue.enqueue(task);
        } else {
            task.updateStatus("FAILED");
            MailHistory history = new MailHistory();
            history.recordHistory(task, "FAILED");
            historyStore.add(history);
            logError("Tác vụ " + task.getTaskId() + " thất bại vĩnh viễn: " + reason);
            System.out.println("   [FAIL] Tác vụ " + task.getTaskId() + " thất bại hoàn toàn sau " + task.getRetryCount() + " lần thử");
        }
    }

    private void logError(String message) {
        systemLogs.add(new SystemLog("LOG-" + System.nanoTime(), "ERROR", message));
    }

    public List<MailHistory> getHistoryStore() {
        return historyStore;
    }

    public List<SystemLog> getSystemLogs() {
        return systemLogs;
    }
}