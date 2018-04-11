package com.tone.gf.event.model;

public class BuySellModel {
    private final String code;
    private final double price;
    private final int amount;

    public BuySellModel(String code, double price, int amount) {
        this.code = code;
        this.price = price;
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

}
