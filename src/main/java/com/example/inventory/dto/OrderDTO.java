package com.example.inventory.dto;

public class OrderDTO {
    private Long id;
    private Long productId;
    private int quantity;
    private double totalPrice;
    private String customerName; // Ensure this field exists

    // Getters and Setters
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

    public String getCustomerName() { // Add this method
        return customerName;
    }

    public void setCustomerName(String customerName) { // Add this method
        this.customerName = customerName;
    }
}
