package Models;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int idOrder;
    private int idUser;
    private List<Food> listFood;
    private List<Seat> listSeat;
    private List<PromoCode> listPromoCode;
    private double total;
    private String statusOrder;
    private String statusPay;
    private LocalDateTime orderDate;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public List<Food> getListFood() {
        return listFood;
    }

    public void setListFood(List<Food> listFood) {
        this.listFood = listFood;
    }

    public List<Seat> getListSeat() {
        return listSeat;
    }

    public void setListSeat(List<Seat> listSeat) {
        this.listSeat = listSeat;
    }

    public List<PromoCode> getListPromoCode() {
        return listPromoCode;
    }

    public void setListPromoCode(List<PromoCode> listPromoCode) {
        this.listPromoCode = listPromoCode;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getStatusPay() {
        return statusPay;
    }

    public void setStatusPay(String statusPay) {
        this.statusPay = statusPay;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", idUser=" + idUser +
                ", listFood=" + listFood +
                ", listSeat=" + listSeat +
                ", listPromoCode=" + listPromoCode +
                ", total=" + total +
                ", statusOrder='" + statusOrder + '\'' +
                ", statusPay='" + statusPay + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
