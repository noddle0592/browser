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
     * 买入日志记录事件
     */
    EventType BUY_LOG = new ByteEventType((byte)2);
    /**
     * 卖出日志记录事件
     */
    EventType SELL_LOG = new ByteEventType((byte)3);
}
