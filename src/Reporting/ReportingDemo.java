package Reporting;

import Models.ReportData;
import Models.ReportFile;
import Models.ReportFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class ReportingDemo {
    public static void run(BufferedReader reader) throws IOException {
        System.out.println("\n--- [ADMIN] VÀO CHỨC NĂNG THỐNG KÊ ---");
        ReportController controller = new ReportController();

        //Chọn loại báo cáo
        System.out.println("Chọn loại báo cáo cần xem:");
        System.out.println("1. Báo cáo doanh thu");
        System.out.println("2. Hiệu suất phim");
        System.out.println("3. Thống kê khách hàng");
        System.out.println("4. Thống kê kho");
        System.out.print("Chọn (1-4): ");
        String choice = reader.readLine().trim();

        String reportType;
        switch (choice) {
            case "2":
                reportType = "MOVIE_PERFORMANCE";
                break;
            case "3":
                reportType = "CUSTOMER_STAT";
                break;
            case "4":
                reportType = "INVENTORY";
                break;
            default:
                reportType = "REVENUE";
                break;
        }

        // Tạo điều kiện lọc
        ReportFilter filter = new ReportFilter(reportType, "2026-07-01", "2026-07-31");

        //Lọc dữ liệu
        System.out.println("\n[Hệ thống] Đang lọc dữ liệu...");
        ReportData data = controller.generateReportData(filter);

        //Hiển thị dữ liệu
        System.out.println("\n--- DỮ LIỆU HIỂN THỊ ---");
        if (data.isEmpty()) {
            System.out.println("Không có dữ liệu thỏa mãn.");
        } else {
            for (String col : data.getColumns()) System.out.printf("%-20s | ", col);
            System.out.println("\n------------------------------------------------------------------");
            for (Map<String, Object> row : data.getRecords()) {
                for (String col : data.getColumns()) System.out.printf("%-20s | ", row.get(col));
                System.out.println();
            }
        }

        System.out.print("\nBạn có muốn Xuất báo cáo không? (y/n): ");
        if (reader.readLine().trim().equalsIgnoreCase("y")) {

            System.out.println("Chọn định dạng xuất file:");
            System.out.println("1. Xuất PDF");
            System.out.println("2. Xuất Excel");
            System.out.print("Chọn (1-2): ");
            String formatChoice = reader.readLine().trim();

            String baseFileName = "Report_" + reportType + "_" + System.currentTimeMillis();
            ReportFile exportedFile;

            // Hệ thống xử lý xuất
            if (formatChoice.equals("1")) {
                System.out.println("[Hệ thống] Đang kết xuất PDF...");
                exportedFile = controller.exportToPDF(data, baseFileName);
            } else {
                System.out.println("[Hệ thống] Đang kết xuất Excel...");
                exportedFile = controller.exportToExcel(data, baseFileName);
            }

            //Download file
            if (exportedFile != null) {
                System.out.println("\n--- [ADMIN] DOWNLOAD FILE ---");
                System.out.println("Tải xuống hoàn tất! Chi tiết file:");
                System.out.println(exportedFile);
            }
        }

        System.out.println("\nNhấn Enter để quay lại menu chính...");
        reader.readLine();
    }
}