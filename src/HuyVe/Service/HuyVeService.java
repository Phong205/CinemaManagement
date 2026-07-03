package HuyVe.Service;

import HuyVe.Enum.OrderStatus;
import Models.Food;
import Models.Order;
import Models.PromoCode;
import Models.Seat;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HuyVeService {
    public List<Order> getListOrder() {
        Seat seat1 = new Seat(1, 1, "A1", "Thường", "empty", 100000, LocalDateTime.of(LocalDate.of(2026, 7, 10), LocalTime.of(0,0,0)));
        Seat seat2 = new Seat(2, 1, "A2", "giá rẻ", "empty", 300000,LocalDateTime.of(LocalDate.of(2026, 7, 10), LocalTime.of(0,0,0)));
        Seat seat3 = new Seat(3, 1, "A3", "vip", "empty", 500000, LocalDateTime.of(LocalDate.of(2026, 7, 10), LocalTime.of(0,0,0)));

        Food food1 = new Food(1, "bap rang", 20000);
        Food food2 = new Food(2, "snack", 10000);
        Food food3 = new Food(3, "coca", 15000);

        PromoCode promo1 = new PromoCode(1, "magiam10", "ma_giam_10", 0.1,
                LocalDateTime.of(LocalDate.of(2026, 7, 10), LocalTime.of(0,0,0)));

        // order 1
        Order order1 = new Order();
        order1.setIdOrder(1);
        order1.setIdUser(1);
        order1.setOrderDate(LocalDateTime.now());
        order1.setStatusOrder(OrderStatus.CHUA_THANH_TOAN.name());
        order1.setStatusPay(OrderStatus.CHUA_THANH_TOAN.name());

        List<Seat> seatsOrder1 = new ArrayList<>();
        seatsOrder1.add(seat1);
        order1.setListSeat(seatsOrder1);

        List<Food> foodsOrder1 = new ArrayList<>();
        foodsOrder1.add(food1);
        order1.setListFood(foodsOrder1);

        order1.setListPromoCode(new ArrayList<>());
        order1.setTotal(120000);

        // order 2
        Order order2 = new Order();
        order2.setIdOrder(2);
        order2.setIdUser(102);
        order2.setOrderDate(LocalDateTime.now());
        order2.setStatusOrder(OrderStatus.DA_THANH_TOAN.name());
        order2.setStatusPay(OrderStatus.DA_THANH_TOAN.name());

        List<Seat> seatsOrder2 = new ArrayList<>();
        seatsOrder2.add(seat2);
        seatsOrder2.add(seat3);
        order2.setListSeat(seatsOrder2);

        List<Food> foodsOrder2 = new ArrayList<>();
        foodsOrder2.add(food2);
        foodsOrder2.add(food3);
        order2.setListFood(foodsOrder2);

        order2.setListPromoCode(new ArrayList<>());
        order2.setTotal(825000);

        // ORDER 3
        Order order3 = new Order();
        order3.setIdOrder(3);
        order3.setIdUser(103);
        order3.setOrderDate(LocalDateTime.now());
        order3.setStatusOrder(OrderStatus.DA_THANH_TOAN.name());
        order3.setStatusPay(OrderStatus.DA_THANH_TOAN.name());

        List<Seat> seatsOrder3 = new ArrayList<>();
        seatsOrder3.add(seat1);
        order3.setListSeat(seatsOrder3);

        order3.setListFood(new ArrayList<>());

        List<PromoCode> promoOrder3 = new ArrayList<>();
        promoOrder3.add(promo1);
        order3.setListPromoCode(promoOrder3);

        order3.setTotal(90000);



        List<Order> listOrder = new ArrayList<>();
        listOrder.add(order1);
        listOrder.add(order2);
        listOrder.add(order3);
        return listOrder;

    }

    public String getPolicy() {
        return "1. Không được hủy vé trước 1 ngày hết hạng \n " +
                "2. Không được hủy vé đã hết hạng \n" +
                "3. Nếu hạng sử dụng vé cách hiện tại lới hơn 3 ngày thì được hoàn tiền 100 phần trăm \n" +
                "4. Các trường hợp còn lại chỉ hoàn tiền 50 phần trăm";
    }
    public List<Order> huyVe(int id) {
        List<Order> listOrder = getListOrder();

        if (id == listOrder.size() + 1) {
            System.out.println("thoát hủy vé");
            return listOrder;
        }
        int index = id - 1;

        if (index < 0 || index >= listOrder.size() || listOrder.get(index) == null) {
            System.out.println("Vui lòng chọn đúng ID vé cần hủy!");
            return listOrder;
        }

        Order order = listOrder.get(index);
        LocalDateTime bayGio = LocalDateTime.now();
        LocalDateTime hanSuDung = order.getListSeat().get(0).getExpiry();


        // vé hết hạng
        if (hanSuDung.isBefore(bayGio)) {
            System.out.println("Không thể hủy! Vé này đã hết hạn sử dụng.");
            return listOrder;
        }

        // Tính khoảng cách số ngày từ hiện tại cho đến hết hạng
        long soNgayConLai = ChronoUnit.DAYS.between(bayGio, hanSuDung);

        // Không được hủy vé trước 1 ngày hết hạn
        if (soNgayConLai < 1) {
            System.out.println("Không thể hủy! Không được phép hủy vé trước khi hết hạn ít hơn 1 ngày.");
            return listOrder;
        }

        double soTienHoan = 0;

        //Hạn sử dụng cách hiện tại lớn hơn 3 ngày
        if (soNgayConLai > 3) {
            soTienHoan = order.getTotal();
            order.setStatusOrder(OrderStatus.DA_HUY.name());
            System.out.println("Hủy vé thành công! Bạn được hoàn tiền 100%: " + soTienHoan + " đồng.");
            return listOrder;
        } else {
            soTienHoan = order.getTotal() * 0.5;
            order.setStatusOrder(OrderStatus.DA_HUY.name());
            System.out.println("Hủy vé thành công! Bạn được hoàn tiền 50%: " + soTienHoan + " đồng.");
            return listOrder;
        }

    }
}
