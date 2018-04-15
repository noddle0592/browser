package com.tone.gf.strategy;

import com.tone.gf.AppInfo;
import com.tone.gf.event.BuyEvent;
import com.tone.gf.event.EventContext;
import com.tone.gf.event.SellEvent;
import com.tone.gf.event.model.BuySellModel;
import com.tone.gf.strategy.model.ModelT1;

import java.util.ArrayList;
import java.util.List;

/**
 * T+1格子战法
 */
public class StrategyT1 implements Runnable {
    private final ModelT1 model;
    /**
     * 当前的卖出队列
     */
    private List<Double> sell;
    /**
     * 当前的买入队列
     */
    private List<Double> buy;

    public StrategyT1(ModelT1 model) {
        this.model = model;
        this.sell = new ArrayList<>();
        this.buy = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            int count = 0;
            double closePrice = AppInfo.CLOSE_PRICES.get(model.getCode());
            while (AppInfo.START) {
                // 获取价格
                double gradient = model.getGradient();
                double curPrice = AppInfo.PRICES.get(model.getCode());
                if (curPrice > gradient) {
                    if (curPrice > ArithUtil.add(gradient, model.getFloatPrice())) {
                        // 纠正当前阶梯价
                        gradient = ArithUtil.add(gradient, model.getFloatPrice());
                        model.setGradient(gradient);
                        // 当前已经卖出，删除当前卖出
                        if (!sell.remove(gradient)) {
                            count++;
                        }
                    } else {
                        // 是否需要阶梯卖出
                        if (count > 0) {
                            // 卖出委托指令，非真正卖出
                            EventContext.getInstance().publishEvent(new SellEvent(new BuySellModel(model.getCode(), curPrice, model.getAmount() * count)));
                            buy.add(curPrice);
                            count = 0;
                        }
                        // 是否发出过卖出
                        double sellPrice = ArithUtil.add(gradient, model.getFloatPrice());
                        if (sell.stream().noneMatch(p -> p.equals(sellPrice))) {
                            if (sellPrice < closePrice * 1.1) {
                                // 卖出委托指令，非真正卖出
                                EventContext.getInstance().publishEvent(new SellEvent(new BuySellModel(model.getCode(), sellPrice, model.getAmount())));
                                sell.add(sellPrice);
                            }
                        }
                    }
                } else if (curPrice < gradient) {
                    if (curPrice < ArithUtil.sub(gradient, model.getFloatPrice())) {
                        // 纠正当前阶梯价
                        gradient = ArithUtil.sub(gradient, model.getFloatPrice());
                        model.setGradient(gradient);
                        // 当前已经买入，删除当前买入
                        if (!buy.remove(gradient)) {
                            count--;
                        }
                    } else {
                        // 是否需要阶梯买入
                        if (count < 0) {
                            // 买入委托指令，非真正买入
                            EventContext.getInstance().publishEvent(new BuyEvent(new BuySellModel(model.getCode(), curPrice, model.getAmount() * (-count))));
                            buy.add(curPrice);
                            count = 0;
                        }
                        // 是否发出过买入
                        double buyPrice = ArithUtil.sub(gradient, model.getFloatPrice());
                        if (buy.stream().noneMatch(p -> p.equals(buyPrice))) {
                            if (buyPrice > closePrice * 0.9) {
                                // 买入委托指令，非真正买入
                                EventContext.getInstance().publishEvent(new BuyEvent(new BuySellModel(model.getCode(), buyPrice, model.getAmount())));
                                buy.add(buyPrice);
                            }
                        }
                    }
                }
                Thread.sleep(AppInfo.SLEEP_TIME);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
