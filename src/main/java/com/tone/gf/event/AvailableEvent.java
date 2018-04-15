package com.tone.gf.event;

import com.tone.event.BasicEvent;
import com.tone.gf.event.model.PositionModel;

public class AvailableEvent extends BasicEvent<PositionModel> {
    public AvailableEvent(PositionModel source) {
        super(source, EventTypes.AVAILABLE_CHANGE);
    }
}
