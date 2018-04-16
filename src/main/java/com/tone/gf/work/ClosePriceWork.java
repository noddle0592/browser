package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.tone.gf.AppInfo;
import com.tone.gf.util.SleepUtil;

public class ClosePriceWork implements Runnable {
    private PositionStoreWork positionStoreWork = new PositionStoreWork();

    @Override
    public void run() {
        AppInfo.STOCK_DOCUMENT.forEach((key, value) -> {
            double closePrice = 0;
            while (closePrice <= 0) {
                try {
                    closePrice = Double.parseDouble(value.findElement(By.className("closeprice")).getInnerText());
                } catch (NumberFormatException e) {
                    SleepUtil.sleep();
                }
            }
            AppInfo.CLOSE_PRICES.put(key, closePrice);
            // 持仓
            positionStoreWork.setCode(key);
            positionStoreWork.run();
        });
    }
}
