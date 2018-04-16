package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.tone.gf.AppInfo;
import com.tone.gf.event.AvailableEvent;
import com.tone.gf.event.EventContext;
import com.tone.gf.event.PositionEvent;
import com.tone.gf.event.model.PositionModel;
import com.tone.gf.util.SleepUtil;

import java.util.List;

public class PriceWork implements Runnable {
    private PositionStoreWork positionStoreWork;

    @Override
    public void run() {
        while (AppInfo.START) {
            AppInfo.STOCK_DOCUMENT.forEach((key, value) -> {
                // 价格
                AppInfo.PRICES.put(key, Double.parseDouble(value.findElement(By.className("now")).getInnerText()));
                // 持仓
                positionStoreWork.setCode(key);
                positionStoreWork.run();
            });
            System.out.println(AppInfo.PRICES);
            SleepUtil.sleep();
        }
    }
}
