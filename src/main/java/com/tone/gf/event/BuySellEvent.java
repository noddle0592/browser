package com.tone.gf.event;

import com.tone.event.BasicEvent;
import com.tone.event.EventType;
import com.tone.gf.event.model.BuySellModel;

/**
 * 买卖处理事件
 */
public abstract class BuySellEvent extends BasicEvent<BuySellModel> {

    public BuySellEvent(BuySellModel source, EventType eventType) {
        super(source, eventType);
    }

}
