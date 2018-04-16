package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.tone.gf.AppInfo;

/**
 * 撤单
 */
public class OrderCancelWork implements Runnable {
    @Override
    public void run() {
        DOMElement domElement = AppInfo.BROWSER.getDocument().findElement(By.className("AppBar-StockTrade"));
        domElement.click();
        DOMElement domElementTabsContent = AppInfo.BROWSER.getDocument().findElement(By.className("Tabs-Content"));
        DOMElement domElementOrderCancel = domElementTabsContent.findElement(By.className("Tabs-Bar-Item Tab-1"));
        domElementOrderCancel.click();
//        domElementTabsContent.findElement()
    }
}
