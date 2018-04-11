package com.tone.gf.work;

import com.tone.gf.event.model.BuySellModel;

public class SellLogWork extends BuySellLogWork {
    public SellLogWork(BuySellModel buySellModel) {
        super("sell", buySellModel.getCode(), buySellModel.getPrice(), buySellModel.getAmount());
    }
}
