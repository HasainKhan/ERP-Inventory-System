package com.erp.model;

public class Inventory {

    private int id;
    private String productName;
    private int currentStock;
    private int reorderLevel;
    private double price;

    // Constructor
    public Inventory(int id,
                     String productName,
                     int currentStock,
                     int reorderLevel,
                     double price) {

        this.id = id;
        this.productName = productName;
        this.currentStock = currentStock;
        this.reorderLevel = reorderLevel;
        this.price = price;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public double getPrice() {
        return price;
    }
}