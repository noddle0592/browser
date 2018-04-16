package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.tone.gf.AppInfo;
import com.tone.gf.event.AvailableEvent;
import com.tone.gf.event.EventContext;
import com.tone.gf.event.PositionEvent;
import com.tone.gf.event.model.PositionModel;

import java.util.List;

public class PositionStoreWork implements Runnable {
    private String code;
    
    @Override
    public void run() {
        // 持仓
        DOMElement domElementPosition = AppInfo.POSITION_DOCUMENT.get(code);
        if (domElementPosition != null) {
            List<DOMElement> tds = domElementPosition.findElements(By.tagName("td"));
            // name, code, nowAvailable, currentAvailable, costPrice, nowPrice, profitAndLoss, profitAndLossPercent, tradeType, tradeAccount
            int newPosition = Integer.valueOf(tds.get(2).getInnerText());
            if (AppInfo.START) {
                Integer oldPosition = AppInfo.AVAILABLES.get(code);
                if (oldPosition != null) {
                    if (oldPosition.intValue() != newPosition) {
                        // 发送可用持仓变动通知
                        EventContext.getInstance().publishEvent(new AvailableEvent(new PositionModel(code, oldPosition, newPosition)));
                    }
                }
            }
            AppInfo.AVAILABLES.put(code, newPosition);

            newPosition = Integer.valueOf(tds.get(3).getInnerText());
            if (AppInfo.START) {
                Integer oldPosition = AppInfo.POSITIONS.get(code);
                if (oldPosition != null) {
                    if (oldPosition.intValue() != newPosition) {
                        // 发送总持仓变动通知
                        EventContext.getInstance().publishEvent(new PositionEvent(new PositionModel(code, oldPosition, newPosition)));
                    }
                }
            }
            AppInfo.POSITIONS.put(code, newPosition);
        } else {
            AppInfo.AVAILABLES.put(code, 0);
            AppInfo.POSITIONS.put(code, 0);
        }
    }

    public void setCode(String code) {
        this.code = code;
    }
}
