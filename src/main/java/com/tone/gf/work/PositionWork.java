package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.tone.gf.AppInfo;
import com.tone.gf.util.SleepUtil;

import java.util.List;

/**
 * 持仓查询
 */
public class PositionWork implements Runnable {
    @Override
    public void run() {
        DOMElement domElement = AppInfo.BROWSER.getDocument().findElement(By.className("AppBar-StockTrade"));
        domElement.click();
        SleepUtil.sleep();
        DOMElement domElementTabsContent = AppInfo.BROWSER.getDocument().findElement(By.className("Tabs-Content"));
        DOMElement domElementPosition = domElementTabsContent.findElement(By.className("Tabs-Bar-Item Tab-2"));
        domElementPosition.click();
        SleepUtil.sleep();
        // 可用金额
//        DOMElement domElementEnable = domElementTabsContent.findElement(By.className("general-inventory trade-piece")).findElement(By.className("enable"));
//        System.out.println(domElementEnable.getInnerText());
        // 持仓
        List<DOMElement> positions = domElementTabsContent.findElement(By.className("trade-data-list")).findElement(
                By.className("body-table-wrap")).findElements(By.tagName("tr"));
        positions.stream().forEach(position -> {
            List<DOMElement> tds = position.findElements(By.tagName("td"));
            // name, code, nowAvailable, currentAvailable, costPrice, nowPrice, profitAndLoss, profitAndLossPercent, tradeType, tradeAccount
            DOMElement domElementCode = tds.get(1);
            String code = domElementCode.getInnerText();
            AppInfo.POSITION_DOCUMENT.put(code, position);
        });
    }
}
