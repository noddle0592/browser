package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.tone.gf.AppInfo;
import com.tone.gf.event.model.BuySellModel;

public class SellWork extends BuySellWork {

    public SellWork(BuySellModel buySellModel) {
        super(buySellModel.getCode(), buySellModel.getPrice(), buySellModel.getAmount());
    }

    @Override
    protected DOMElement getBuyOrSellElement() {
        return AppInfo.BROWSER.getDocument().findElement(By.className("trade-order sell"));
    }

}
