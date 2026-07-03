import HuyVe.HuyVeView;
import MailSending.MailSendingDemo;
import Reporting.ReportingDemo;
import TinhGiaVe.TinhGiaVeView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println("\n==================================================");
                System.out.println("   HỆ THỐNG QUẢN LÝ RẠP CHIẾU PHIM (CONSOLE)   ");
                System.out.println("==================================================");
                System.out.println(" 1. Hệ thống gửi Gmail (MailSending)");
                System.out.println(" 2. Thống kê và báo cáo (Reporting)");
                System.out.println(" 3. Tính giá vé tự động");
                System.out.println(" 4. Hủy vé");
                System.out.println(" 0. Thoát");
                System.out.println("==================================================");
                System.out.print("Chọn chức năng (0-4): ");

                String choice = reader.readLine().trim();
                switch (choice) {
                    case "1":
                        MailSendingDemo.run(reader);
                        break;
                    case "2":
                        ReportingDemo.run(reader);
                        break;
                    case "3":
                        TinhGiaVeView.run();
                        break;
                    case "4":
                        HuyVeView.run();
                        break;
                    case "0":
                        System.out.println("\nTạm biệt!");
                        return;
                    default:
                        System.out.println("\n[LỖI] Lựa chọn không hợp lệ, vui lòng chọn lại.");
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR] Lỗi hệ thống: " + e.getMessage());
        }
    }
}