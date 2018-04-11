package com.tone.gf.event;

import com.tone.gf.event.model.BuySellModel;

public class SellEvent extends BuySellEvent {
    public SellEvent(BuySellModel source) {
        super(source, EventTypes.SELL);
    }
}
