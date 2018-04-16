package com.tone.gf.work;

import com.tone.gf.AppInfo;
import com.tone.gf.TableColumns;

/**
 * 可用持仓变动
 */
public class AvailableChangeWork implements Runnable {
    private final String code;
    private final int newPosition;

    public AvailableChangeWork(String code, int newPosition) {
        this.code = code;
        this.newPosition = newPosition;
    }

    @Override
    public void run() {
        AppInfo.MODEL_TABLE.setValue(code, TableColumns.AVAILABLE, newPosition);
    }
}
