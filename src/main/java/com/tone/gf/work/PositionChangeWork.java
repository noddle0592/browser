package com.tone.gf.work;

import com.tone.gf.AppInfo;
import com.tone.gf.TableColumns;

/**
 * 总持仓变动
 */
public class PositionChangeWork implements Runnable {
    private final String code;
    private final int position;
    private final int newPosition;

    public PositionChangeWork(String code, int position, int newPosition) {
        this.code = code;
        this.position = position;
        this.newPosition = newPosition;
    }

    @Override
    public void run() {
        AppInfo.MODEL_TABLE.setValue(code, TableColumns.POSITION, newPosition);
        // 今买或者今卖
        int diff = newPosition - position;
        AppInfo.MODEL_TABLE.setValue(code, diff > 0 ? TableColumns.BUY : TableColumns.SELL, Math.abs(diff));
    }
}
