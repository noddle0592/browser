package com.tone.gf.util;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserKeyEvent;

import com.teamdev.jxbrowser.chromium.BrowserKeyEvent.KeyCode;
import com.teamdev.jxbrowser.chromium.BrowserKeyEvent.KeyEventType;
import com.teamdev.jxbrowser.chromium.BrowserMouseEvent;
import com.teamdev.jxbrowser.chromium.dom.DOMElement;
import com.teamdev.jxbrowser.chromium.dom.events.DOMEventTarget;
import com.tone.gf.AppInfo;

import java.awt.*;

import static com.teamdev.jxbrowser.chromium.BrowserMouseEvent.MouseEventType.MOUSE_RELEASED;

public class KeyUtil {
//    /**
//     * 模拟按按键
//     * @param eventTarget 需要按按键的对象
//     * @param key 需要键入的内容
//     */
//    public static void inputStringToDom(DOMEventTarget eventTarget, String key) {
//        char[] cs = key.toCharArray();
//        System.out.println("cs = " + cs);
//        for (char c : cs) {
//            try {
//                dispatchKeyEvent(eventTarget, c);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println("start key press " + KeyEvent.getExtendedKeyCodeForChar(c));
//            break;
//        }
//    }
//
//    private static void dispatchKeyEvent(DOMEventTarget eventTarget, char c) {
//        eventTarget.dispatchEvent(new MyKeyPressEvent(DOMEventType.OnKeyDown, eventTarget, KeyEvent.getExtendedKeyCodeForChar(c)));
//        eventTarget.dispatchEvent(new MyKeyPressEvent(DOMEventType.OnKeyPress, eventTarget, KeyEvent.getExtendedKeyCodeForChar(c)));
//        eventTarget.dispatchEvent(new MyKeyPressEvent(DOMEventType.OnKeyUp, eventTarget, KeyEvent.getExtendedKeyCodeForChar(c)));
//    }
//
//    private static class MyKeyPressEvent extends com.teamdev.jxbrowser.chromium.dom.internal.KeyEvent {
//
//        public MyKeyPressEvent(DOMEventType eventType, DOMEventTarget eventTarget, int keyCode) {
//            super(System.currentTimeMillis(), eventType, DOMEventPhase.CapturingPhase,
//                    eventTarget, eventTarget,  false, true, 0, null,
//                    false, false, false, false, keyCode);
//        }
//    }

    private static void forwardMousePressEvent(BrowserMouseEvent.MouseButtonType buttonType,
                                               int x, int y) {
        BrowserMouseEvent.BrowserMouseEventBuilder builder = new BrowserMouseEvent.BrowserMouseEventBuilder();
        builder.setEventType(BrowserMouseEvent.MouseEventType.MOUSE_PRESSED)
                .setButtonType(buttonType)
                .setX(x)
                .setY(y)
//                .setGlobalX(globalX)
//                .setGlobalY(globalY)
                .setClickCount(1)
                .setModifiers(new BrowserKeyEvent.KeyModifiersBuilder().mouseButton().build());
        AppInfo.BROWSER.forwardMouseEvent(builder.build());
    }

    private static void forwardMouseReleaseEvent(BrowserMouseEvent.MouseButtonType buttonType,
                                                 int x, int y) {
        BrowserMouseEvent.BrowserMouseEventBuilder builder = new BrowserMouseEvent.BrowserMouseEventBuilder();
        builder.setEventType(MOUSE_RELEASED)
                .setButtonType(buttonType)
                .setX(x)
                .setY(y)
//                .setGlobalX(globalX)
//                .setGlobalY(globalY)
                .setClickCount(1)
                .setModifiers(BrowserKeyEvent.KeyModifiers.NO_MODIFIERS);
        AppInfo.BROWSER.forwardMouseEvent(builder.build());
    }

    private static void forwardMouseClickEvent(BrowserMouseEvent.MouseButtonType buttonType,
                                               int x, int y) {
        forwardMousePressEvent(buttonType, x, y);
        forwardMouseReleaseEvent(buttonType, x, y);
    }

    /**
     * 模拟按按键
     * @param domElement 需要按按键的对象
     * @param key 需要键入的内容
     */
    public static void inputStringToDom(DOMElement domElement, String key) {
        Rectangle rectangle = domElement.getBoundingClientRect();
        inputStringToDom(rectangle, key);
    }

    /**
     * 模拟按按键
     * @param domElement 需要按按键的对象
     * @param key 需要键入的内容
     * @param adjust 是否修正坐标位置，默认不修正
     */
    public static void inputStringToDom(DOMElement domElement, String key, boolean adjust) {
        Rectangle rectangle = domElement.getBoundingClientRect();
        if (adjust) {
            rectangle.setLocation((int) (rectangle.getX() + rectangle.getWidth() / 2), (int) (rectangle.getY() + rectangle.getHeight() / 2));
        }
        inputStringToDom(rectangle, key);
    }

    private static void inputStringToDom(Rectangle rectangle, String key) {
        // focus
        forwardMouseClickEvent(BrowserMouseEvent.MouseButtonType.PRIMARY, (int)rectangle.getX(), (int)rectangle.getY());
        SleepUtil.shortSleep();
        // press
        char[] cs = key.toUpperCase().toCharArray();
        for (char c : cs) {
            try {
                forwardKeyEvent(c == '.' ? KeyCode.VK_DECIMAL : KeyCode.valueOf(String.format("VK_%c", c)), c);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            System.out.println("start key press " + KeyEvent.getExtendedKeyCodeForChar(c));
        }
    }

    private static void forwardKeyEvent(KeyCode code, char character) {
        AppInfo.BROWSER.forwardKeyEvent(new BrowserKeyEvent(KeyEventType.PRESSED, code, character));
        AppInfo.BROWSER.forwardKeyEvent(new BrowserKeyEvent(KeyEventType.TYPED, code, character));
        AppInfo.BROWSER.forwardKeyEvent(new BrowserKeyEvent(KeyEventType.RELEASED, code, character));
    }

}
