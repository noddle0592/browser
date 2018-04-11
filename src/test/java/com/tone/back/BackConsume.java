package com.tone.back;

import com.tone.event.Event;
import com.tone.event.listener.AbstractSmartListener;
import com.tone.gf.event.*;
import com.tone.gf.event.ui.PopMessages;
import com.tone.gf.event.ui.RightCornerPopMessage;
import com.tone.gf.work.BuyLogWork;
import com.tone.gf.work.SellLogWork;

import java.util.HashMap;
import java.util.Map;

/**
 * 事件处理对象
 */
public class BackConsume {
    /**
     * 委托价格，4个价格分别是buyMax, buyMin, sellMax, sellMin
     */
    private double[] delegatePrice = new double[]{0, Double.MAX_VALUE, 0, Double.MAX_VALUE};
    /**
     * 委托数量，buyAmount, sellAmount
     */
    private int[] delegateAmount = new int[]{0, 0};
    /**
     * 真正买卖价格，4个价格分别是buyMax, buyMin, sellMax, sellMin
     */
    private double[] realPrice = new double[]{0, Double.MAX_VALUE, 0, Double.MAX_VALUE};
    /**
     * 真正买卖数量，buyAmount, sellAmount
     */
    private int[] realAmount = new int[]{0, 0};
    /**
     * 最大的连续买和连续卖次数
     */
    private int[] realContinue = new int[]{0, 0};
    /**
     * 当前的连续买和连续卖次数
     */
    private int[] currentContinue = new int[]{0, 0};
    /**
     * 一天的最大买卖次数
     */
    private int[] dayAmount = new int[]{0, 0};
    /**
     * 当前的一天买和卖次数
     */
    private int[] currentAmount = new int[]{0, 0};
    private Map<Double, BuySellEvent> buySellMap = new HashMap<>();

    public BackConsume() {
        EventContext eventContext = EventContext.getInstance();
        eventContext.addListener(new AbstractSmartListener<BuyEvent>(EventTypes.BUY) {
            @Override
            public void onEvent(BuyEvent event) throws Exception {
                new BuyLogWork(event.getSource()).run();
                if (delegatePrice[0] < event.getSource().getPrice()) {
                    delegatePrice[0] = event.getSource().getPrice();
                }
                if (delegatePrice[1] > event.getSource().getPrice()) {
                    delegatePrice[1] = event.getSource().getPrice();
                }
                delegateAmount[0] += event.getSource().getAmount();

                buySellMap.put(event.getSource().getPrice(), event);
            }
        });
        eventContext.addListener(new AbstractSmartListener<SellEvent>(EventTypes.SELL) {
            @Override
            public void onEvent(SellEvent event) throws Exception {
                new SellLogWork(event.getSource()).run();
                if (delegatePrice[2] < event.getSource().getPrice()) {
                    delegatePrice[2] = event.getSource().getPrice();
                }
                if (delegatePrice[3] > event.getSource().getPrice()) {
                    delegatePrice[3] = event.getSource().getPrice();
                }
                delegateAmount[1] += event.getSource().getAmount();

                buySellMap.put(event.getSource().getPrice(), event);
            }
        });
    }

    public void checkReal(double price) {
        BuySellEvent event = buySellMap.remove(price);
        if (event != null) {
            if (event.getEventType() == EventTypes.BUY) {
                if (realPrice[0] < event.getSource().getPrice()) {
                    realPrice[0] = event.getSource().getPrice();
                }
                if (realPrice[1] > event.getSource().getPrice()) {
                    realPrice[1] = event.getSource().getPrice();
                }
                realAmount[0] += event.getSource().getAmount();

                if (currentContinue[1] > realContinue[1]) {
                    realContinue[1] = currentContinue[1];
                }
                currentContinue[1] = 0;
                currentContinue[0] += event.getSource().getAmount();

                currentAmount[0] += event.getSource().getAmount();
            } else if (event.getEventType() == EventTypes.SELL) {
                if (realPrice[2] < event.getSource().getPrice()) {
                    realPrice[2] = event.getSource().getPrice();
                }
                if (realPrice[3] > event.getSource().getPrice()) {
                    realPrice[3] = event.getSource().getPrice();
                }
                realAmount[1] += event.getSource().getAmount();

                if (currentContinue[0] > realContinue[0]) {
                    realContinue[0] = currentContinue[0];
                }
                currentContinue[0] = 0;
                currentContinue[1] += event.getSource().getAmount();

                currentAmount[1] += event.getSource().getAmount();
            }
        }
    }

    public void oneDayClean() {
        if (currentAmount[0] > dayAmount[0]) {
            dayAmount[0] = currentAmount[0];
        }
        if (currentAmount[1] > dayAmount[1]) {
            dayAmount[1] = currentAmount[1];
        }
        currentAmount[0] = 0;
        currentAmount[1] = 0;
    }

    @Override
    public String toString() {
        return "delegate : {" +
                "buyMax=" + delegatePrice[0] +
                ", buyMin=" + delegatePrice[1] +
                ", sellMax=" + delegatePrice[2] +
                ", sellMin=" + delegatePrice[3] +
                ", buyAmount=" + delegateAmount[0] +
                ", sellAmount=" + delegateAmount[1] +
                "}, \n real : {" +
                "buyMax=" + realPrice[0] +
                ", buyMin=" + realPrice[1] +
                ", sellMax=" + realPrice[2] +
                ", sellMin=" + realPrice[3] +
                ", buyAmount=" + realAmount[0] +
                ", sellAmount=" + realAmount[1] +
                "}, \n continue : {" +
                "continueBuyMax = " + realContinue[0] +
                ", continueSellMax = " + realContinue[1] +
                ", dayBuyMax = " + dayAmount[0] +
                ", daySellMax = " + dayAmount[1] +
                "}";
    }

    public void getSummary(double floatPrice, int amount) {
        System.out.println(this.toString());
        double totalProfit = Math.min(realAmount[0], realAmount[1]) * floatPrice * amount * 100;
        int expense = (realAmount[0] + realAmount[1]) * 20;
        System.out.println("totalProfit = " + totalProfit + ", expense = " + expense + ", profit = " + (totalProfit - expense));
    }
}
