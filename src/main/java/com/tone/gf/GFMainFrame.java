package com.tone.gf;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.dom.DOMDocument;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import com.teamdev.jxbrowser.chromium.az;
import com.tone.gf.event.EventConsume;
import com.tone.gf.util.PropertyUtil;
import com.tone.gf.util.SleepUtil;
import com.tone.gf.work.StartWork;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;

public class GFMainFrame {

    static {
        try {
            Field e = az.class.getDeclaredField("e");
            e.setAccessible(true);
            Field f = az.class.getDeclaredField("f");
            f.setAccessible(true);
            Field modifersField = Field.class.getDeclaredField("modifiers");
            modifersField.setAccessible(true);
            modifersField.setInt(e, e.getModifiers() & ~Modifier.FINAL);
            modifersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            e.set(null, new BigInteger("1"));
            f.set(null, new BigInteger("1"));
            modifersField.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Browser browser = new Browser();
        BrowserView view = new BrowserView(browser);

        SettingPanel settingPanel = new SettingPanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, settingPanel, view);
        frame.add(splitPane);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
            if (event.isMainFrame()) {
                DOMDocument document = event.getBrowser().getDocument();
                settingPanel.setButtonEnable();
                new Thread(new StartWork()).start();
            }
            }
        });
        browser.loadURL("http://hippo.gf.com.cn");
        AppInfo.BROWSER = browser;
        new EventConsume();
    }
}