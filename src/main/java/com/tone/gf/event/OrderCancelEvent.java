package com.tone.gf.event;

import com.tone.event.BasicEvent;
import com.tone.gf.event.model.OrderCancelModel;

public class OrderCancelEvent extends BasicEvent<OrderCancelModel> {

    public OrderCancelEvent(OrderCancelModel source) {
        super(source, EventTypes.ORDER_CANCEL);
    }

}
