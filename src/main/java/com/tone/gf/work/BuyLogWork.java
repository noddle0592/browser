package com.tone.gf.work;

import com.tone.gf.event.model.BuySellModel;

public class BuyLogWork extends BuySellLogWork {
    public BuyLogWork(BuySellModel buySellModel) {
        super("buy", buySellModel.getCode(), buySellModel.getPrice(), buySellModel.getAmount());
    }
}
