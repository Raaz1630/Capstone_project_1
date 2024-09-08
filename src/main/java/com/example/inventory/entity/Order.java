package com.example.inventory.entity;

public class Order {
    private Long id;
    private Long productId;
    private int quantity;
    private double totalPrice;
    private String customerName; // Ensure this field exists

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName; // Ensure this method exists
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName; // Ensure this method exists
    }
}

