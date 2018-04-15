package com.tone.gf.event.model;

public class PositionModel {
    private final int position;
    private final int newPosition;

    public PositionModel(int position, int newPosition) {
        this.position = position;
        this.newPosition = newPosition;
    }

    public int getPosition() {
        return position;
    }

    public int getNewPosition() {
        return newPosition;
    }
}
