package com.tone.gf;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;

import java.util.HashMap;
import java.util.Map;

public class AppInfo {
    /**
     * 程序是否开始工作
     */
    public static boolean START = false;
    /**
     * 是否模拟运行
     */
    public static boolean MONITOR = false;
    /**
     * 策略
     */
    public static int STRATEGY;
    /**
     * 睡眠时间
     */
    public static long SLEEP_TIME = 1000;
    /**
     * 浏览器
     */
    public static Browser BROWSER;
    /**
     * 文档根
     */
    public static DOMDocument DOCUMENT;
    /**
     * 股代码和股对象映射关系
     */
    public static Map<String, DOMElement> STOCK_DOCUMENT;
    /**
     * 当前价格
     */
    public static Map<String, Double> PRICES = new HashMap<>();
    /**
     * 昨收价格
     */
    public static Map<String, Double> CLOSE_PRICES = new HashMap<>();
}
