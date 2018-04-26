package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.tone.gf.AppInfo;
import com.tone.gf.event.EventContext;
import com.tone.gf.event.SellEvent;
import com.tone.gf.event.model.BuySellModel;
import com.tone.gf.util.SleepUtil;

/**
 * 开始时的检测
 * 1. 判断页面是否初始完毕
 * 2. 准备自动登录
 * 3. 检测添加到自选的股
 * 4. 价格和持仓更新启动
 */
public class StartWork implements Runnable {
    @Override
    public void run() {
        SleepUtil.sleep(3);
        DOMElement domElementGrid = null;
        // 判断价格表是否已经载入
        while (domElementGrid == null){
            domElementGrid = AppInfo.BROWSER.getDocument().findElement(By.className("StockList StockGrid ScrollbarOuter"));
            SleepUtil.sleep();
        }
        SleepUtil.sleep();
        // 登录
        new LoginWork().run();
        SleepUtil.sleep();
        // 获取持仓情况
       /* new PositionWork().run();
        SleepUtil.sleep();
        // 获取 AppInfo.STOCK_DOCUMENT 对象
        StockWork stockWork = new StockWork();
        stockWork.run();
        // 获取昨收价格
        ClosePriceWork closePriceWork = new ClosePriceWork();
        closePriceWork.run();
        // 检测自选
        AddCheckWork addCheckWork = new AddCheckWork(stockWork, closePriceWork);
        addCheckWork.run();
        AppInfo.ADD_CHECK = addCheckWork;*/
        EventContext.getInstance().publishEvent(new SellEvent(new BuySellModel("002281", 29.10, 200)));
    }
}
