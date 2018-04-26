package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.tone.gf.AppInfo;

import java.util.List;

/**
 * 撤单
 */
public class OrderCancelWork implements Runnable {
    private final String code;
    private final String direction = "卖出";
    private final double price;

    public OrderCancelWork(String code, double price) {
        this.code = code;
        this.price = price;
    }

    @Override
    public void run() {
        DOMElement domElement = AppInfo.BROWSER.getDocument().findElement(By.className("AppBar-StockTrade"));
        domElement.click();
        DOMElement domElementTabsContent = AppInfo.BROWSER.getDocument().findElement(By.className("Tabs-Content"));
        DOMElement domElementOrderCancel = domElementTabsContent.findElement(By.className("Tabs-Bar-Item Tab-1"));
        domElementOrderCancel.click();
        List<DOMElement> orders = domElementTabsContent.findElement(By.className("trade-data-list")).findElement(By.className("body-table-wrap")).findElements(By.tagName("tr"));
        DOMElement cancelOrder = null;
        for (DOMElement order : orders) {
            List<DOMElement> tds = order.findElements(By.tagName("td"));
            // 是否当前股票代码，并且买卖方向正确
            if (tds.get(4).getInnerText().equals(code) && tds.get(5).getInnerText().equals(direction)) {
                // 价格是否相同
                double dirPrice = 0;
                try {
                    dirPrice = Double.parseDouble(tds.get(8).getInnerText());
                } catch (NumberFormatException e) {
                    // don't care
                }
                if (dirPrice == price) {
                    cancelOrder = order;
                    break;
                }
            }
        }
        if (cancelOrder != null) {
            // checkbox选中
            cancelOrder.findElement(By.className("cell-checkbox")).findElement(By.tagName("input")).click();
        }
        // 撤单按钮
        domElementTabsContent.findElement(By.className("trade-panel__head")).findElement(By.className("ui-button")).click();
    }
}
