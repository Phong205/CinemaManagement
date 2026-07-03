package MailSending;

import Models.EmailTemplate;
import Models.MailHistory;
import Models.MailQueue;
import Models.MailTask;
import Models.SystemLog;

import java.io.BufferedReader;
import java.io.IOException;

public class MailSendingDemo {
    public static void run(BufferedReader reader) throws IOException {
        System.out.println("\n--- [CHỨC NĂNG] HỆ THỐNG GỬI MAIL ---");
        MailQueue queue = new MailQueue("QUEUE-01");
        MailService mailService = new MailService(queue);

        EmailTemplate welcomeTemplate = new EmailTemplate();
        welcomeTemplate.setTemplateId("TPL-WELCOME");
        welcomeTemplate.setCategory("ONBOARDING");
        welcomeTemplate.setSubject("Chào mừng bạn đến với hệ thống rạp phim!");
        mailService.registerTemplate(welcomeTemplate);

        EmailTemplate promoTemplate = new EmailTemplate();
        promoTemplate.setTemplateId("TPL-PROMO");
        promoTemplate.setCategory("MARKETING");
        promoTemplate.setSubject("Ưu đãi đặc biệt dành riêng cho bạn");
        mailService.registerTemplate(promoTemplate);

        System.out.print("Nhập email người nhận (recipientEmail): ");
        String recipient = reader.readLine().trim();
        if (recipient.isEmpty()) recipient = "customer@example.com";

        System.out.println("Chọn mẫu Email (EmailTemplate):\n1. [TPL-WELCOME] Đăng ký thành công\n2. [TPL-PROMO] Mã giảm giá");
        System.out.print("Chọn mẫu (1-2): ");
        String templateChoice = reader.readLine().trim();
        String templateId = templateChoice.equals("2") ? "TPL-PROMO" : "TPL-WELCOME";

        System.out.print("Nhập tiêu đề thư (subject) [Để trống dùng mặc định]: ");
        String subject = reader.readLine().trim();
        System.out.print("Nhập nội dung bổ sung (content): ");
        String content = reader.readLine().trim();

        MailTask task = new MailTask();
        task.setTaskId("TASK-" + (int) (Math.random() * 10000));
        task.setRecipientEmail(recipient);
        task.setTemplateId(templateId);
        task.setMetadata("{\"custom_content\":\"" + content + "\"}");
        task.setStatus("PENDING");

        if (!subject.isEmpty()) {
            if (templateId.equals("TPL-WELCOME")) welcomeTemplate.setSubject(subject);
            else welcomeTemplate.setSubject(subject);
        }

        System.out.print("Lựa chọn phương thức:\n1. Gửi trực tiếp\n2. Đưa vào hàng đợi\nChọn (1-2): ");
        String method = reader.readLine().trim();

        queue.enqueue(task);
        if (method.equals("2")) System.out.println("\n[MailQueue] Đã lưu tác vụ vào hàng chờ.");

        mailService.processQueue();

        System.out.println("--- Lịch sử hệ thống lưu trữ (MailHistory) ---");
        if (mailService.getHistoryStore().isEmpty()) System.out.println("(Chưa ghi nhận lịch sử)");
        else for (MailHistory h : mailService.getHistoryStore()) System.out.println(h);

        System.out.println("\n--- Nhật ký ghi nhận lỗi (SystemLog) ---");
        if (mailService.getSystemLogs().isEmpty()) System.out.println("(Hệ thống chạy mượt mà, không có lỗi)");
        else for (SystemLog log : mailService.getSystemLogs()) System.out.println(log);

        System.out.println("\nNhấn Enter để quay lại menu chính...");
        reader.readLine();
    }
}