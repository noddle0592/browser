package com.tone.gf.strategy.model;

import com.tone.gf.strategy.Strategy;

/**
 * 模型基类
 */
public abstract class Model {
    protected final Strategy strategy;
    /**
     * 股票代码
     */
    protected String code;

    public Model(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public abstract Object[] toArray();
}
