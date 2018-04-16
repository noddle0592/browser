package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
import com.tone.gf.AppInfo;

public abstract class BuySellWork implements Runnable {
    protected final String code;
    protected final double price;
    protected final int amount;

    public BuySellWork(String code, double price, int amount) {
        this.code = code;
        this.price = price;
        this.amount = amount;
    }

    @Override
    public void run() {
        DOMElement domElement = AppInfo.BROWSER.getDocument().findElement(By.className("AppBar-StockTrade"));
        domElement.click();
        DOMElement domElementBuySell = this.getBuyOrSellElement();
        // 代码
        DOMInputElement domElementCode = (DOMInputElement)domElementBuySell.findElement(By.className("item code")).findElement(By.tagName("input"));
        domElementCode.setValue(code);
        // 价格
        DOMInputElement domElementPrice = (DOMInputElement)domElementBuySell.findElement(By.className("item price")).findElement(By.tagName("input"));
        domElementPrice.setValue(String.valueOf(price));
        // 涨停、跌停
//        DOMElement domElementRise = domElementBuySell.findElement(By.className("item rise"));
//        DOMElement domElementRed = domElementRise.findElement(By.className("red"));
//        DOMElement domElementGreen = domElementRise.findElement(By.className("green"));
//        double up = Double.parseDouble(domElementRed.getInnerText());
//        double down = Double.parseDouble(domElementGreen.getInnerText());
        // 数量
        DOMInputElement domElementAmount = (DOMInputElement)domElementBuySell.findElement(By.className("item amount")).findElement(By.tagName("input"));
        domElementAmount.setValue(String.valueOf(amount));
        DOMElement domElementButton = domElementBuySell.findElement(By.className("ui-button"));
        domElementButton.click();
    }

    protected abstract DOMElement getBuyOrSellElement();
}
