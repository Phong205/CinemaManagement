package Reporting;

import Models.ReportData;
import Models.ReportFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseModel {

    public ReportData queryDataSet(ReportFilter filter) {
        if (filter == null || !filter.isValid()) {
            return new ReportData(new ArrayList<>(), new ArrayList<>());
        }

        List<String> columns;
        List<Map<String, Object>> records = new ArrayList<>();
        String type = filter.getReportType();

        switch (type) {
            case "REVENUE":
                columns = Arrays.asList("Ngày", "Số Vé Bán", "Doanh Thu (VNĐ)");
                records.add(createRow(columns, "01/07/2026", 1250, "112,500,000"));
                records.add(createRow(columns, "02/07/2026", 1420, "127,800,000"));
                break;
            case "MOVIE_PERFORMANCE":
                columns = Arrays.asList("Tên Phim", "Lượt Xem", "Tỷ Lệ Lấp Đầy");
                records.add(createRow(columns, "Lật Mặt 7", 3450, "94.5%"));
                records.add(createRow(columns, "Hành Tinh Khỉ", 2100, "72.0%"));
                break;
            case "CUSTOMER_STAT":
                columns = Arrays.asList("Hạng Thành Viên", "Số Lượng", "Tổng Chi Tiêu");
                records.add(createRow(columns, "VIP", 450, "85,000,000"));
                records.add(createRow(columns, "Standard", 3200, "150,000,000"));
                break;
            case "INVENTORY":
                columns = Arrays.asList("Vật Phẩm", "Tồn Kho", "Đã Bán");
                records.add(createRow(columns, "Bắp Rang Bơ", "150 kg", "850 suất"));
                records.add(createRow(columns, "Nước Ngọt", "500 lít", "1100 ly"));
                break;
            default:
                columns = new ArrayList<>();
        }

        return new ReportData(columns, records);
    }

    private Map<String, Object> createRow(List<String> keys, Object... values) {
        Map<String, Object> row = new LinkedHashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            row.put(keys.get(i), values[i]);
        }
        return row;
    }
}