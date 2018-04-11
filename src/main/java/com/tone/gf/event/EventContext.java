package com.tone.gf.event;

import com.tone.event.bus.BasicContext;

import java.util.concurrent.Executors;

public class EventContext extends BasicContext {
    private static EventContext eventContext = new EventContext();

    private EventContext() {
        super(Executors.newFixedThreadPool(3));
    }

    public static EventContext getInstance() {
        return eventContext;
    }
}
