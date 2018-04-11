package com.tone.gf.strategy;

import com.tone.gf.AppInfo;
import com.tone.gf.strategy.model.ModelT0;

/**
 * T+0格子战法
 */
public class StrategyT0 implements Runnable {
    private final ModelT0 model;

    public StrategyT0(ModelT0 model) {
        super();
        this.model = model;
    }

    @Override
    public void run() {
        try {
            while (AppInfo.START) {

                Thread.sleep(AppInfo.SLEEP_TIME);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
