package com.tone.gf.strategy.model;

import com.tone.gf.AppInfo;
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
    protected int buy;
    protected int sell;

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

    public int getBuy() {
        return buy;
    }

    public void addBuy(int buy) {
        this.buy = this.buy + buy;
    }

    public int getSell() {
        return sell;
    }

    public void addSell(int sell) {
        this.sell = this.sell + sell;
    }

    public abstract Object[] toArray();

    /**
     * 获取可用持仓数
     * @return 可用持仓数
     */
    protected int getAvailable() {
        return AppInfo.AVAILABLES.get(code) != null ? AppInfo.AVAILABLES.get(code) : 0;
    }

    /**
     * 获取总持仓数
     * @return 总持仓数
     */
    protected int getPosition() {
        return AppInfo.POSITIONS.get(code) != null ? AppInfo.POSITIONS.get(code) : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        return code.equals(model.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
