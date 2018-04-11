package com.tone.gf.event;

import com.tone.gf.event.model.BuySellModel;

public class BuyEvent extends BuySellEvent {
    public BuyEvent(BuySellModel source) {
        super(source, EventTypes.BUY);
    }
}
