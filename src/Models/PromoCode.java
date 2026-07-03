package Models;

import java.time.LocalDateTime;

public class PromoCode {
    private int id;
    private String code;
    private String name;
    private double discountRate;
    private LocalDateTime expiryDate;

    public PromoCode(int id, String code, String name, double discountRate, LocalDateTime expiryDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.discountRate = discountRate;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "PromoCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", discountRate=" + discountRate +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
