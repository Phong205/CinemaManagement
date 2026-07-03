package Models;

import java.util.Date;

public class SystemLog {
    private String logId;
    private Date timestamp;
    private String errorLevel;
    private String message;

    public SystemLog(String logId, String errorLevel, String message) {
        this.logId = logId;
        this.timestamp = new Date();
        this.errorLevel = errorLevel;
        this.message = message;
    }

    @Override
    public String toString() {
        return "SystemLog [Log ID=" + logId + " | " + errorLevel + " | " + message + "]";
    }
}