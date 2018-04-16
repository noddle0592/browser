package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.tone.gf.AppInfo;

public class ClosePriceWork implements Runnable {
    @Override
    public void run() {
        AppInfo.STOCK_DOCUMENT.forEach((key, value) -> {
            AppInfo.CLOSE_PRICES.put(key, Double.parseDouble(value.findElement(By.className("closeprice")).getInnerText()));
        });
    }
}
