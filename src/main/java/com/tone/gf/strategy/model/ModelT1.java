package com.tone.gf.strategy.model;

import com.tone.gf.strategy.Strategy;

public class ModelT1 extends Model {
    private final double price;
    private final double floatPrice;
    private final int amount;
    /**
     * 上下的梯度价格
     */
    private double gradient;

    public ModelT1(double price, double floatPrice, int amount) {
        super(Strategy.T1);
        this.price = price;
        this.floatPrice = floatPrice;
        this.amount = amount;
        this.gradient = price;
    }

    public double getPrice() {
        return price;
    }

    public double getFloatPrice() {
        return floatPrice;
    }

    public int getAmount() {
        return amount;
    }

    public double getGradient() {
        return gradient;
    }

    public void setGradient(double gradient) {
        this.gradient = gradient;
    }

    @Override
    public Object[] toArray() {
        return new Object[]{strategy, code, price, floatPrice, amount, getAvailable(), getPosition(), 0, 0};
    }
}
