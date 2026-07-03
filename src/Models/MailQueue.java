package Models;

import java.util.Queue;

public class MailQueue {
    private String queueId;
    private Queue<MailTask> mailTasks;

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public Queue<MailTask> getMailTasks() {
        return mailTasks;
    }

    public void setMailTasks(Queue<MailTask> mailTasks) {
        this.mailTasks = mailTasks;
    }
}
