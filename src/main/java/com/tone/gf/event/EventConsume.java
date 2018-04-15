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
        eventContext.addListener(new AbstractSmartListener<AvailableEvent>(EventTypes.AVAILABLE_CHANGE) {
            @Override
            public void onEvent(AvailableEvent event) throws Exception {
                int diff = event.getSource().getNewPosition() - event.getSource().getPosition();
                rightCornerPopMessage.showPopMessage("可用持仓", String.format(PopMessages.POSITION, "可用",
                        diff > 0 ? "red" : "green", diff > 0 ? "增加" : "减少", Math.abs(diff)));
            }
        });
        eventContext.addListener(new AbstractSmartListener<PositionEvent>(EventTypes.POSITION_CHANGE) {
            @Override
            public void onEvent(PositionEvent event) throws Exception {
                int diff = event.getSource().getNewPosition() - event.getSource().getPosition();
                rightCornerPopMessage.showPopMessage("总持仓", String.format(PopMessages.POSITION, "总",
                        diff > 0 ? "red" : "green", diff > 0 ? "增加" : "减少", Math.abs(diff)));
            }
        });
    }
}
