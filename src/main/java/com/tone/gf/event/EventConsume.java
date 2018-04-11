package com.tone.gf.event;

import com.tone.event.listener.AbstractSmartListener;
import com.tone.gf.event.ui.PopMessages;
import com.tone.gf.event.ui.RightCornerPopMessage;
import com.tone.gf.work.*;

/**
 * 事件处理对象
 */
public class EventConsume {
    private RightCornerPopMessage rightCornerPopMessage;

    public EventConsume() {
        this.rightCornerPopMessage = new RightCornerPopMessage();

        EventContext eventContext = EventContext.getInstance();
        eventContext.addListener(new AbstractSmartListener<BuyEvent>(EventTypes.BUY) {
            @Override
            public void onEvent(BuyEvent event) throws Exception {
                new BuyLogWork(event.getSource()).run();
                rightCornerPopMessage.showPopMessage("委托买入", String.format(PopMessages.BUY_SELL, "买入",
                        event.getSource().getPrice(), event.getSource().getCode(), event.getSource().getAmount()));
            }
        });
        eventContext.addListener(new AbstractSmartListener<SellEvent>(EventTypes.SELL) {
            @Override
            public void onEvent(SellEvent event) throws Exception {
                new SellLogWork(event.getSource()).run();
                rightCornerPopMessage.showPopMessage("委托卖出", String.format(PopMessages.BUY_SELL, "卖出",
                        event.getSource().getPrice(), event.getSource().getCode(), event.getSource().getAmount()));
            }
        });
    }
}
