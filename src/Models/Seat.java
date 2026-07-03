package Models;

import java.time.LocalDateTime;

public class Seat {
    private int seatId;
    private int showtimeId;
    private String seatNumber;
    private String seatType;
    private String status;
    private double price;
    private LocalDateTime expiry;


    public Seat(int seatId, int showtimeId, String seatNumber, String seatType, String status, double price, LocalDateTime expiry) {
        this.seatId = seatId;
        this.showtimeId = showtimeId;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.status = status;
        this.price = price;
        this.expiry = expiry;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", showtimeId=" + showtimeId +
                ", seatNumber='" + seatNumber + '\'' +
                ", seatType='" + seatType + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", expiry=" + expiry +
                '}';
    }
}
