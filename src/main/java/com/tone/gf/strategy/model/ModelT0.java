package com.tone.gf.strategy.model;

import com.tone.gf.strategy.Strategy;

public class ModelT0 extends Model {
    private final double price;
    private final double floatPrice;
    private final double highest;
    private final double lowest;

    public ModelT0(double price, double floatPrice, double highest, double lowest) {
        super(Strategy.T0);
        this.price = price;
        this.floatPrice = floatPrice;
        this.highest = highest;
        this.lowest = lowest;
    }

    public double getPrice() {
        return price;
    }

    public double getFloatPrice() {
        return floatPrice;
    }

    public double getHighest() {
        return highest;
    }

    public double getLowest() {
        return lowest;
    }

    public Object[] toArray() {
        return new Object[]{strategy, code, price, floatPrice, 0, getAvailable(), getPosition(), 0, 0};
    }
}
