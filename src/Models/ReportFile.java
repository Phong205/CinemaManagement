package Models;

public class ReportFile {
    private String fileName;
    private String formatType;
    private String filePath;
    private long fileSizeBytes;

    public ReportFile(String fileName, String formatType, String filePath, long fileSizeBytes) {
        this.fileName = fileName;
        this.formatType = formatType;
        this.filePath = filePath;
        this.fileSizeBytes = fileSizeBytes;
    }

    @Override
    public String toString() {
        return fileName + " | Định dạng: " + formatType + " | Dung lượng: " + fileSizeBytes + " bytes\nĐường dẫn: " + filePath;
    }
}