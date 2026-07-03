package Models;

import java.util.List;
import java.util.Map;

public class ReportData {
    private List<String> columns;
    private List<Map<String, Object>> records;

    public ReportData(List<String> columns, List<Map<String, Object>> records) {
        this.columns = columns;
        this.records = records;
    }

    public boolean isEmpty() {
        return records == null || records.isEmpty();
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<Map<String, Object>> getRecords() {
        return records;
    }
}