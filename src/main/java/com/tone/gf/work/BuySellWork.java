package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMInputElement;
import com.teamdev.jxbrowser.chromium.dom.DOMNode;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventPhase;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventTarget;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventType;
import com.teamdev.jxbrowser.chromium.dom.events.DOMKeyEvent;
import com.teamdev.jxbrowser.chromium.dom.internal.KeyEvent;
import com.tone.gf.AppInfo;
import com.tone.gf.util.KeyUtil;
import com.tone.gf.util.SleepUtil;

import java.util.List;

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
//        domElementCode.setValue(code);
        KeyUtil.inputStringToDom(domElementCode, code);
        SleepUtil.shortSleep();
        // 涨停、跌停
        DOMElement domElementRise = domElementBuySell.findElement(By.className("item rise"));
        DOMElement domElementRed = domElementRise.findElement(By.className("red"));
        DOMElement domElementGreen = domElementRise.findElement(By.className("green"));
        double rightPrice;
        try {
            double up = Double.parseDouble(domElementRed.getInnerText());
            double down = Double.parseDouble(domElementGreen.getInnerText());
            // 修正价格
            rightPrice = down > price ? down : price > up ? up : price;
        } catch (NumberFormatException e) {
            rightPrice = price;
        }
        // 价格
        DOMInputElement domElementPrice = (DOMInputElement)domElementBuySell.findElement(By.className("item price")).findElement(By.tagName("input"));
        domElementPrice.setValue("");
        KeyUtil.inputStringToDom(domElementPrice, String.valueOf(rightPrice));
        SleepUtil.shortSleep();
        // 数量
        DOMInputElement domElementAmount = (DOMInputElement)domElementBuySell.findElement(By.className("item amount")).findElement(By.tagName("input"));
//        domElementAmount.setValue(String.valueOf(amount));
        KeyUtil.inputStringToDom(domElementAmount, String.valueOf(amount));
        SleepUtil.shortSleep();
        // 提交
        DOMElement domElementButton = domElementBuySell.findElement(By.className("ui-button"));
        domElementButton.click();
        SleepUtil.shortSleep();
        // 确定。有两个button，0为确定，1为取消
        List<DOMElement> buttons = AppInfo.BROWSER.getDocument().findElement(By.className("TMPDOMRoot Mask"))
                .findElement(By.className("Footer button-num-2")).findElements(By.tagName("button"));
        buttons.get(0).click();
    }

    protected abstract DOMElement getBuyOrSellElement();
}
