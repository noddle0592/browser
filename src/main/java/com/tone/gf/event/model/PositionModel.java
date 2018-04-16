package com.tone.gf.event.model;

public class PositionModel {
    private final String code;
    private final int position;
    private final int newPosition;

    public PositionModel(String code, int position, int newPosition) {
        this.code = code;
        this.position = position;
        this.newPosition = newPosition;
    }

    public String getCode() {
        return code;
    }

    public int getPosition() {
        return position;
    }

    public int getNewPosition() {
        return newPosition;
    }
}
