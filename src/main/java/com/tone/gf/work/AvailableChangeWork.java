package com.tone.gf.work;

/**
 * 可用持仓数变动
 */
public class AvailableChangeWork extends PositionChangeWork {

    public AvailableChangeWork(String code, int newPosition) {
        super(code, newPosition);
        super.column = 5;
    }
}
