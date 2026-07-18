package com.veroshop.demo.models;

import java.util.List;

public class Order {
    private String orderId;
    private String date;
    private List<CartItem> items;
    private double total;
    private String status;
    private String paymentMethod;

    public Order(String orderId, String date, List<CartItem> items,
                 double total, String status, String paymentMethod) {
        this.orderId = orderId;
        this.date = date;
        this.items = items;
        this.total = total;
        this.status = status;
        this.paymentMethod = paymentMethod;
    }

    public String getOrderId() { return orderId; }
    public String getDate() { return date; }
    public List<CartItem> getItems() { return items; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
    public String getPaymentMethod() { return paymentMethod; }
    public int getItemCount() { return items != null ? items.size() : 0; }
}
