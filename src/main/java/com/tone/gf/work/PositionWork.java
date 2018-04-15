package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.tone.gf.AppInfo;

import java.util.List;

/**
 * 持仓查询
 */
public class PositionWork implements Runnable {
    @Override
    public void run() {
        DOMElement domElement = AppInfo.DOCUMENT.findElement(By.className("AppBar-StockTrade"));
        domElement.click();
        DOMElement domElementTabsContent = AppInfo.BROWSER.getDocument().findElement(By.className("Tabs-Content"));
        DOMElement domElementPosition = domElementTabsContent.findElement(By.className("Tabs-Bar-Item Tab-2"));
        domElementPosition.click();
        List<DOMElement> positions = domElementTabsContent.findElement(By.className("trade-data-list")).findElements(By.tagName("tr"));
        positions.stream().forEach(position -> {
            List<DOMElement> tds = position.findElements(By.tagName("td"));
            // name, code, nowAvailable, currentAvailable, costPrice, nowPrice, profitAndLoss, profitAndLossPercent, tradeType, tradeAccount
            DOMElement domElementCode = tds.get(1);
            String code = domElement.getInnerText();
            AppInfo.POSITION_DOCUMENT.put(code, position);
        });
    }
}
