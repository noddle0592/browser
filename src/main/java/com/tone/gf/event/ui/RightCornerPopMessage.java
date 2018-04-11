package com.tone.gf.event.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * 右下角弹出式提示框
 * 1.自动上升
 * 2.停留一段时间，本例子中5秒
 * 3.自动下降直至消失
 * <p>
 * 4.线程控制窗口的出现和消失，同时添加鼠标事件控制，可以提前使提示框消失
 * 5.鼠标事件结合自己的需求实现，此处只是实现一个点击事件
 */
public class RightCornerPopMessage extends JWindow {
    private static final long serialVersionUID = -3564453685861233338L;
    /**
     * 屏幕宽度
     */
    private int screenWidth;
    /**
     * 屏幕高度
     */
    private int screenHeight;
    /**
     * 设置提示窗口宽度
     */
    private int windowWidth = 200;
    /**
     * 设置提示窗口高度
     */
    private int windowHeight = 100;
    /**
     * 底部任务栏高度，如果没有任务栏则为零
     */
    private int bottmToolKitHeight;
    /**
     * 设置提示框停留时间，单位毫秒
     */
    private int stayTime = 5000;

    /**
     * 窗口起始X坐标
     */
    private int x;
    /**
     * 窗口起始Y坐标
     */
    private int y;

    /**
     * 提示标题
     */
    private String title = "温馨提示";
    /**
     * 提示消息
     */
    private String message = "一个小小的提示消息例子！";

    /**
     * 主面板
     */
    private JPanel mainPanel;
    /**
     * 标题栏标签
     */
    private JLabel titleLabel;
    /**
     * 标题栏面板
     */
    private JPanel titlePanel;
    /**
     * 内容标签
     */
    private JLabel messageLabel;
    /**
     * 内容面板
     */
    private JPanel messagePanel;
    private Executor executor = Executors.newSingleThreadExecutor();

    public RightCornerPopMessage() {
        this.init();
    }

    private void init() {
        bottmToolKitHeight = Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration()).bottom;
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = dimension.width;
        screenHeight = dimension.height;

        x = screenWidth - windowWidth;
        y = screenHeight;
        this.setLocation(x, y - bottmToolKitHeight - windowHeight);
        mainPanel = new JPanel(new BorderLayout());

        titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.BLACK);
        titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(titleLabel);

        messageLabel = new JLabel(message);
        messagePanel = new JPanel();
        messagePanel.add(messageLabel);
        messagePanel.setBackground(Color.LIGHT_GRAY);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(messagePanel, BorderLayout.CENTER);

        this.setSize(windowWidth, windowHeight);
        this.setAlwaysOnTop(false);
        this.getContentPane().add(mainPanel);
        this.addMouseListener(new MouseCloseListener());
        // 播放系统声音，提示一下
        Toolkit.getDefaultToolkit().beep();
    }

    /**
     * 显示窗口
     */
    public void popUp() {
        Integer delay = 10;
        Integer step = 1;
        Integer end = windowHeight + bottmToolKitHeight;
        while (true) {
            try {
                step++;
                y = y - 1;
                this.setLocation(x, y);
                if (step > end) {
                    Thread.sleep(stayTime);
                    break;
                }
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        step = 1;
        while (true) {
            try {
                step++;
                y = y + 1;
                this.setLocation(x, y);
                if (step > end) {
                    this.dispose();
                    break;
                }
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setMessage(String title, String message) {
        titleLabel.setText(title);
        messageLabel.setText(message);
    }

    public void showPopMessage(String title, String message) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                setMessage(title, message);
                setVisible(true);
                popUp();
            }
        });
    }

    private class MouseCloseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            RightCornerPopMessage.this.dispose();
        }
    }

}