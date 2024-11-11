package Entity;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int customerId;
    private LocalDateTime orderDate;

    public Order(){;}
    public Order(int id, int customerId, LocalDateTime orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
