package com.tone.gf.event;

import com.tone.event.BasicEvent;
import com.tone.gf.event.model.PositionModel;

public class PositionEvent extends BasicEvent<PositionModel> {
    public PositionEvent(PositionModel source) {
        super(source, EventTypes.POSITION_CHANGE);
    }
}
