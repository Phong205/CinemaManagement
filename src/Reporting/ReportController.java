package Reporting;

import Models.ReportData;
import Models.ReportFile;
import Models.ReportFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ReportController {
    private final DatabaseModel databaseModel = new DatabaseModel();

    public ReportData generateReportData(ReportFilter filter) {
        return databaseModel.queryDataSet(filter);
    }

    public ReportFile exportToExcel(ReportData data, String fileName) {
        return writeToFile(data, fileName + ".csv", "Excel (CSV)");
    }

    public ReportFile exportToPDF(ReportData data, String fileName) {
        return writeToFile(data, fileName + ".pdf", "PDF");
    }

    private ReportFile writeToFile(ReportData data, String fullFileName, String format) {
        if (data == null || data.isEmpty()) return null;

        File outputDir = new File("reports");
        if (!outputDir.exists()) outputDir.mkdirs();
        String filePath = outputDir.getPath() + File.separator + fullFileName;

        StringBuilder content = new StringBuilder();
        content.append(String.join(",", data.getColumns())).append("\n");
        for (Map<String, Object> row : data.getRecords()) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < data.getColumns().size(); i++) {
                if (i > 0) line.append(",");
                line.append(row.get(data.getColumns().get(i)));
            }
            content.append(line).append("\n");
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content.toString());
        } catch (IOException e) {
            System.out.println("[LỖI] Không thể ghi file: " + e.getMessage());
            return null;
        }

        return new ReportFile(fullFileName, format, filePath, content.toString().getBytes().length);
    }
}