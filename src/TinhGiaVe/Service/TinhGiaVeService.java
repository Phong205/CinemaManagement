package TinhGiaVe.Service;

import Models.Order;

import java.util.ArrayList;
import java.util.List;

public class TinhGiaVeService {
    public Order tinhGia(Order order) {
        double tongGia = 0;
        for (int i = 0; i < order.getListSeat().size(); i++) {
            tongGia += order.getListSeat().get(i).getPrice();
        }
        for (int i = 0; i < order.getListFood().size(); i++) {
            tongGia += order.getListFood().get(i).getPrice();
        }

        List<Double> listGiaGiam = new ArrayList<Double>();
        for (int i = 0; i < order.getListPromoCode().size(); i++) {
            double giaGiam = 0;
            giaGiam = tongGia * order.getListPromoCode().get(i).getDiscountRate();
            listGiaGiam.add(giaGiam);
        }
        for (int i = 0; i < listGiaGiam.size(); i++) {
            tongGia -= listGiaGiam.get(i);
        }
        order.setTotal(tongGia);
        return order;
     }
}
