package com.tone.gf.work;

import com.tone.event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuySellLogWork implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(BuySellWork.class);
    protected final String buySell;
    protected final String code;
    protected final double price;
    protected final int amount;

    public BuySellLogWork(String buySell, String code, double price, int amount) {
        this.buySell = buySell;
        this.code = code;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public void run() {
        logger.info("{} code = {}, price = {}, amount = {}", buySell, code, price, amount);
    }
}
