package TinhGiaVe.Service;

import Models.Food;
import Models.Order;
import Models.PromoCode;
import Models.Seat;
import TinhGiaVe.Enum.DatVeStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatVeService {

    public void datVe(int idUser) {
        List<Seat> listSeat = new ArrayList<Seat>();
        listSeat.add(new Seat(1, 1, "A1", "Thường", "empty", 100000, LocalDateTime.of(LocalDate.of(2026, 7, 10), LocalTime.of(0,0,0))));
        listSeat.add(new Seat(2, 1, "A2", "giá rẻ", "empty", 300000, LocalDateTime.of(LocalDate.of(2026, 7, 10), LocalTime.of(0,0,0))));
        listSeat.add(new Seat(3, 1, "A3", "vip", "empty", 500000, LocalDateTime.of(LocalDate.of(2026, 7, 10), LocalTime.of(0,0,0))));

        List<Food> listFood = new ArrayList<Food>();
        listFood.add(new Food(1,"bap rang", 20000));
        listFood.add(new Food(2, "snack", 10000));
        listFood.add(new Food(3, "coca", 15000));

        List<PromoCode> listPromoCode = new ArrayList<PromoCode>();
        listPromoCode.add(new PromoCode(1, "magiam10", "ma_giam_10", 0.1,
                LocalDateTime.of(LocalDate.of(2026, 7, 10), LocalTime.of(0,0,0))));
        listPromoCode.add(new PromoCode(2, "magiam15", "ma_giam_15", 0.2,
                LocalDateTime.of(LocalDate.of(2026, 7, 15), LocalTime.of(0,0,0))));
        listPromoCode.add(new PromoCode(3, "magiam20", "ma_giam_20", 0.3,
                LocalDateTime.of(LocalDate.of(2026, 7, 20), LocalTime.of(0,0,0))));

        Scanner input = new Scanner(System.in);

        List<Seat> listSeatOrder = new ArrayList<Seat>();
        List<Food> listFoodOrder = new ArrayList<Food>();
        List<PromoCode> listCodeOrder = new ArrayList<PromoCode>();
        Order order = new Order();
        order.setIdOrder(1);
        order.setOrderDate(LocalDateTime.now());
        order.setStatusOrder(DatVeStatus.CHUA_THANH_TOAN.name());
        order.setIdUser(idUser);
        order.setListSeat(listSeatOrder);
        order.setListFood(listFoodOrder);
        order.setListPromoCode(listCodeOrder);
        order.setStatusPay(DatVeStatus.CHUA_THANH_TOAN.name());
        order.setTotal(0);

        boolean chonGhe = true;

        whileLoop1: while (chonGhe) {
            order.setListSeat(listSeatOrder);
            TinhGiaVeService tinhGiaVeService = new TinhGiaVeService();
            Order newOrder = tinhGiaVeService.tinhGia(order);
            System.out.println("Tổng tiền " + newOrder.getTotal() + " đồng");
            System.out.println("Chọn ghế: ");
            System.out.println("Danh sách ghế: ");
            for (int i = 0; i < listSeat.size(); i++) {
                System.out.println(listSeat.get(i).toString());
                if (i == listSeat.size() - 1) System.out.println(listSeat.size()+1 + " next");
            }
            System.out.println("chọn ID ghế: ");

            int idSeatInput = input.nextInt();
            if (listSeatOrder.isEmpty() && idSeatInput == (listSeat.size() + 1)) {
                System.out.println("Chọn ít nhât một ghế");
                break;
            }
            for (int i = 0; i < listSeatOrder.size(); i++) {
                if (listSeatOrder.get(i).getSeatId() == idSeatInput) {
                    System.out.println("Ghế đã được chọn");
                    continue whileLoop1;
                }
            }
            if (idSeatInput == listSeat.size() + 1) {
                chonGhe = false;
                break;
            }
            listSeatOrder.add(listSeat.get(idSeatInput - 1));


        }

        boolean chonFood = true;
        whileLoop2: while (chonFood) {
            order.setListFood(listFoodOrder);
            TinhGiaVeService tinhGiaVeService = new TinhGiaVeService();
            Order newOrder = tinhGiaVeService.tinhGia(order);
            System.out.println("Tổng tiền " + newOrder.getTotal() + " đồng");
            System.out.println("Chọn thức ăn: ");
            System.out.println("Danh sách thức ăn: ");
            for (int i = 0; i < listFood.size(); i++) {
                System.out.println(listFood.get(i).toString());
                if (i == listFood.size() - 1) System.out.println(listFood.size()+1 + " next");
            }
            System.out.println("chọn ID food: ");

            int idFoodInput = input.nextInt();

            if (idFoodInput == listFood.size() + 1) {
                chonFood = false;
                break;
            }

            for (int i = 0; i < listFoodOrder.size(); i++) {
                if (listFoodOrder.get(i).getId() == idFoodInput) {
                    System.out.println("Thức ăn đã được chọn");
                    continue whileLoop2;
                }
            }

            listFoodOrder.add(listFood.get(idFoodInput - 1));

        }

        boolean chonMa = true;

        whileLoop3: while (chonMa) {
            order.setListPromoCode(listCodeOrder);
            TinhGiaVeService tinhGiaVeService = new TinhGiaVeService();
            Order newOrder = tinhGiaVeService.tinhGia(order);
            System.out.println("Tổng tiền " + newOrder.getTotal() + " đồng");
            System.out.println("Chọn Mã: ");
            System.out.println("Danh sách mã: ");
            for (int i = 0; i < listPromoCode.size(); i++) {
                System.out.println(listPromoCode.get(i).toString());
                if (i == listPromoCode.size() - 1) System.out.println(listPromoCode.size()+1 + " xác nhận");
            }
            System.out.println("chọn ID mã: ");

            int idCodeInput = input.nextInt();

            if (idCodeInput == listPromoCode.size() + 1) {
                chonMa = false;
                System.out.println("tổng: " + order.getTotal());
                break;
            }

            if (listPromoCode.get(idCodeInput - 1).getExpiryDate().isBefore(LocalDateTime.now())) {
                System.out.println("mã đã hết hạng");
                continue whileLoop3;
            }

            for (int i = 0; i < listCodeOrder.size(); i++) {
                if (listCodeOrder.get(i).getId() == idCodeInput) {
                    System.out.println("mã đã được chọn");
                    continue whileLoop3;
                }
            }

            listCodeOrder.add(listPromoCode.get(idCodeInput - 1));

        }


    }

}
