package com.tone.gf.event.model;

public class OrderCancelModel {
    private final String code;
    private final double price;

    public OrderCancelModel(String code, double price) {
        this.code = code;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }
}
