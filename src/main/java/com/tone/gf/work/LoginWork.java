package com.tone.gf.work;

import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.DOMFormControlElement;
import com.teamdev.jxbrowser.chromium.dom.DOMNode;
import com.tone.gf.AppInfo;
import com.tone.gf.util.KeyUtil;
import com.tone.gf.util.PropertyUtil;
import com.tone.gf.util.SleepUtil;

import java.awt.*;
import java.util.List;

public class LoginWork implements Runnable {

    @Override
    public void run() {
        // 判断是否有登录
//        DOMElement domElementUserAccount = AppInfo.BROWSER.getDocument().findElement(By.id("user-account-ctl"));
        DOMElement domElementLogin = AppInfo.BROWSER.getDocument().findElement(By.className("FramelessApp")).findElement(By.className("button login"));
        DOMElement domElementUserAccount = domElementLogin.findElement(By.className("ico-status ico-user logined"));
        if (domElementUserAccount == null) {
            // 未登录，需要登录
            domElementLogin.findElement(By.className("login")).click();
            SleepUtil.shortSleep();
            // 交易登录，广发通登录为login-btn GF
            AppInfo.BROWSER.getDocument().findElement(By.className("account-layer")).findElement(By.className("login-btn Trade")).click();
            DOMElement domElementLoginForm = AppInfo.BROWSER.getDocument().findElement(By.className("Popup  LoginPopup TradeLoginPopup")).findElement(By.className("LoginForm"));
            DOMFormControlElement domElementUser = (DOMFormControlElement)domElementLoginForm.findElement(By.className("useridInput"));
            if (domElementUser.getValue().isEmpty()) {
                KeyUtil.inputStringToDom(domElementUser, PropertyUtil.getProperty("user"), true);
                // 记住账号
                DOMElement domElementRemeber = domElementLoginForm.findElement(By.className("rememberpwdcheckbox"));
                if (domElementRemeber.getAttribute("checked") != null) {
                    domElementRemeber.click();
//                    domElementRemeber.setAttribute("checked", "checked");
                }
            }
            DOMFormControlElement domElementPassword = (DOMFormControlElement)domElementLoginForm.findElement(By.name("password"));
            domElementPassword.setValue(PropertyUtil.getProperty("password"));
            // 选4小时的持续时间
            DOMElement domElementTime = domElementLoginForm.findElement(By.className("DownSelect timeSelect"));
            domElementTime.click();
            DOMElement domElementOptions = domElementTime.findElement(By.className("options"));
            List<DOMNode> options = domElementOptions.getChildren();
            options.get(options.size() - 1).click();
            // 登录按钮
            domElementLoginForm.findElement(By.className("button submit")).click();
        }
    }
}
