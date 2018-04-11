package com.tone;

import com.tone.gf.event.ui.RightCornerPopMessage;

public class TestPopMessage {
    public static void main(String[] args) {
        RightCornerPopMessage popMessage = new RightCornerPopMessage();
        popMessage.showPopMessage("1", "1");
        popMessage.showPopMessage("2", "2");
    }
}
