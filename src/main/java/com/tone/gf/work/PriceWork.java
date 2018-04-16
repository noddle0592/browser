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

    @Override
    public void run() {
        while (AppInfo.START) {
            AppInfo.STOCK_DOCUMENT.forEach((key, value) -> {
                // 价格
                AppInfo.PRICES.put(key, Double.parseDouble(value.findElement(By.className("now")).getInnerText()));
                // 持仓
                DOMElement domElementPosition = AppInfo.POSITION_DOCUMENT.get(key);
                if (domElementPosition != null) {
                    List<DOMElement> tds = domElementPosition.findElements(By.tagName("td"));
                    // name, code, nowAvailable, currentAvailable, costPrice, nowPrice, profitAndLoss, profitAndLossPercent, tradeType, tradeAccount
                    int newPosition = Integer.valueOf(tds.get(2).getInnerText());
                    Integer oldPosition = AppInfo.AVAILABLES.get(key);
                    if (oldPosition != null) {
                        if (oldPosition.intValue() != newPosition) {
                            // 发送可用持仓变动通知
                            EventContext.getInstance().publishEvent(new AvailableEvent(new PositionModel(key, oldPosition, newPosition)));
                        }
                    }
                    AppInfo.AVAILABLES.put(key, newPosition);

                    newPosition = Integer.valueOf(tds.get(3).getInnerText());
                    oldPosition = AppInfo.POSITIONS.get(key);
                    if (oldPosition != null) {
                        if (oldPosition.intValue() != newPosition) {
                            // 发送总持仓变动通知
                            EventContext.getInstance().publishEvent(new PositionEvent(new PositionModel(key, oldPosition, newPosition)));
                        }
                    }
                    AppInfo.POSITIONS.put(key, newPosition);
                } else {
                    AppInfo.AVAILABLES.put(key, 0);
                    AppInfo.POSITIONS.put(key, 0);
                }
            });
            System.out.println(AppInfo.PRICES);
            SleepUtil.sleep();
        }
    }
}
