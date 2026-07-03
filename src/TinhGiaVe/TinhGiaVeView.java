package TinhGiaVe;

import TinhGiaVe.Service.DatVeService;
import TinhGiaVe.Service.LoginService;
import TinhGiaVe.Service.TinhGiaVeService;

import java.util.Date;
import java.util.Scanner;

public class TinhGiaVeView {
    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Chọn chức năng đặt vé");
            System.out.println("1: Đặt vé");
            System.out.println("chọn số thứ tự chức năng: ");
            Scanner input = new Scanner(System.in);
            String chucNang = input.nextLine();

            if (!"1".equals(chucNang)) {
                System.out.println("chọn đúng chức năng");
            } else {
                System.out.println("đăng nhập");
                System.out.print("Tên Đăng nhập: ");
                String username = input.nextLine();
                System.out.print("Mật khẩu: ");
                String password = input.nextLine();

                LoginService loginService = new LoginService();
                boolean isLogin = loginService.login(username, password);
                if (!isLogin) {
                    System.out.println("Username hoặc password không đúng");
                    break;
                }
                DatVeService datVeService = new DatVeService();
                datVeService.datVe(1);
                isRunning = false;
            }
        }

    }
}
