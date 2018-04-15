package com.tone.gf.event;

import com.tone.event.ByteEventType;
import com.tone.event.EventType;

public interface EventTypes {
    /**
     * 买入事件
     */
    EventType BUY = new ByteEventType((byte)0);
    /**
     * 卖出事件
     */
    EventType SELL = new ByteEventType((byte)1);
    /**
     * 可用持仓变动
     */
    EventType AVAILABLE_CHANGE = new ByteEventType((byte)2);
    /**
     * 总持仓变动
     */
    EventType POSITION_CHANGE = new ByteEventType((byte)3);
}
