package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.tone.gf.AppInfo;

public class PriceWork implements Runnable {

    @Override
    public void run() {
        // 获取 AppInfo.STOCK_DOCUMENT 对象
        new StockWork().run();
        // 获取昨收价格
        new ClosePriceWork().run();
        while (AppInfo.START) {
            try {
                AppInfo.STOCK_DOCUMENT.forEach((key, value) -> {
                    AppInfo.PRICES.put(key, Double.parseDouble(value.findElement(By.className("now")).getInnerText()));
                });
                System.out.println(AppInfo.PRICES);
                Thread.sleep(AppInfo.SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
