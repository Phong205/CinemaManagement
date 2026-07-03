package Models;

import java.util.LinkedList;
import java.util.Queue;

public class MailQueue {
    private String queueId;
    private final Queue<MailTask> queue = new LinkedList<>();

    public MailQueue(String queueId) {
        this.queueId = queueId;
    }

    public void enqueue(MailTask task) {
        queue.add(task);
    }

    public MailTask dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}