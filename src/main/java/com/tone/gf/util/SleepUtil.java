package com.tone.gf.util;

import com.tone.gf.AppInfo;

public class SleepUtil {

    public static void sleep() {
        sleep(1);
    }

    public static void sleep(int count) {
        try {
            Thread.sleep(AppInfo.SLEEP_TIME * count);
        } catch (InterruptedException e) {
            // don't care
        }
    }

    public static void shortSleep() {
        shortSleep(1);
    }

    public static void shortSleep(int count) {
        try {
            Thread.sleep(AppInfo.SLEEP_TIME * count);
        } catch (InterruptedException e) {
            // don't care
        }
    }
}
