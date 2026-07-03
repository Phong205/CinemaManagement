package Models;

import java.util.Date;

public class MailHistory {
    private String historyId;
    private String taskId;
    private Date sentAt;
    private String status;

    public void recordHistory(MailTask task, String status) {
        this.historyId = "HIS-" + (int) (Math.random() * 1000);
        this.taskId = task.getTaskId();
        this.sentAt = new Date();
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lịch sử [ID=" + historyId + " | Tác vụ=" + taskId + " | Thời gian=" + sentAt + " | Trạng thái=" + status + "]";
    }
}