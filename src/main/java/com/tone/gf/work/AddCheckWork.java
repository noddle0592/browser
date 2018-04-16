package com.tone.gf.work;

import com.tone.gf.AppInfo;
import com.tone.gf.util.SleepUtil;

/**
 * 策略停止运行时检测是否有自选股加入
 */
public class AddCheckWork implements Runnable {
    private final StockWork stockWork;
    private final ClosePriceWork closePriceWork;

    public AddCheckWork(StockWork stockWork, ClosePriceWork closePriceWork) {
        this.stockWork = stockWork;
        this.closePriceWork = closePriceWork;
    }

    @Override
    public void run() {
        while (!AppInfo.START) {
            if (stockWork.isStockSizeChange()) {
                stockWork.run();
                closePriceWork.run();
            }
            SleepUtil.sleep();
        }
    }
}
