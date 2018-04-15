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
     * 股文档对象
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
    /**
     * 股持仓对象
     */
    public static Map<String, DOMElement> POSITION_DOCUMENT = new HashMap<>();
    /**
     * 持仓情况，当前可用
     */
    public static Map<String, Integer> AVAILABLES = new HashMap<>();
    /**
     * 持仓情况，所有可用
     */
    public static Map<String, Integer> POSITIONS = new HashMap<>();

    /**
     * 用于更新表内UI的表模型对象
     */
    public static ModelTable MODEL_TABLE;
}
