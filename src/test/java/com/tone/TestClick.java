package com.tone;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.az;
import com.teamdev.jxbrowser.chromium.dom.By;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;

public class TestClick {

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

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(view, BorderLayout.CENTER);
        frame.setSize(1024, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                System.out.println("main frame = " + event.isMainFrame());
                if (event.isMainFrame()) {
                    DOMElement flip = browser.getDocument().findElement(By.className("flip"));
//                    BrowserMouseEvent mouseEvent = new BrowserMouseEvent.BrowserMouseEventBuilder().setButtonType(BrowserMouseEvent.MouseButtonType.PRIMARY)
//                        .setEventType(BrowserMouseEvent.MouseEventType.MOUSE_MOVED).setClickCount(2).setX(100)
//                        .setY(100).build();
//                    System.out.println("x = " + mouseEvent.getX() + ", y = " + mouseEvent.getY());
//                    browser.forwardMouseEvent(mouseEvent);
                    int count = 10;
                    while (count-- > 0) {
                        flip.click();
                        System.out.println("click " + (10 - count));
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("end");
                }
            }
        });
        browser.loadURL("http://www.w3school.com.cn/jquery/jquery_hide_show.asp");
    }
}
