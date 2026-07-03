package HuyVe;


import HuyVe.Service.HuyVeService;
import HuyVe.Service.LoginService;
import Models.Order;
import java.util.List;
import java.util.Scanner;

public class HuyVeView {
    public static void main(String[] args) {


        boolean isRunning = true;
        whileloop1: while (isRunning) {
            System.out.println("Chọn chức năng hủy vé");
            System.out.println("1: hủy vé");
            System.out.println("2: Thoát");
            System.out.println("chọn số thứ tự chức năng: ");
            Scanner input = new Scanner(System.in);
            String chucNang = input.nextLine();

            if ("2".equals(chucNang)) return;

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
                    continue whileloop1;
                }

                System.out.println("dánh sách vé đã đặt");
                HuyVeService huyVeService = new HuyVeService();
                List<Order> listOrder = huyVeService.getListOrder();

                for (int i = 0; i < listOrder.size(); i++) {
                    System.out.println(listOrder.get(i).toString());
                    if (i == listOrder.size() - 1) {
                        System.out.println(listOrder.size()+1 + " Thoát");
                    }
                }
                System.out.println("Điều kiện hủy vé: ");
                System.out.println(huyVeService.getPolicy());
                System.out.println("Chọn id vé muốn hủy");
                int idVeHuy = input.nextInt();

                listOrder = huyVeService.huyVe(idVeHuy);

                for (int i = 0; i < listOrder.size(); i++) {
                    System.out.println(listOrder.get(i).toString());
                }
            }
        }

    }
}
