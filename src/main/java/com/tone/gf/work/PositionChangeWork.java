package com.tone.gf.work;

import com.tone.gf.AppInfo;

/**
 * 总持仓变动
 */
public class PositionChangeWork implements Runnable {
    private final String code;
    private final int newPosition;
    protected int column;

    public PositionChangeWork(String code, int newPosition) {
        this.code = code;
        this.newPosition = newPosition;
        this.column = 6;
    }

    @Override
    public void run() {
        AppInfo.MODEL_TABLE.setValue(code, column, newPosition);
    }
}
