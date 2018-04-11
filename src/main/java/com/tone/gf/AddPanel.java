package com.tone.gf;

import com.tone.gf.strategy.Strategy;
import com.tone.gf.strategy.StrategyT0;
import com.tone.gf.strategy.StrategyT1;
import com.tone.gf.strategy.model.Model;
import com.tone.gf.strategy.model.ModelT0;
import com.tone.gf.strategy.model.ModelT1;
import com.tone.gf.strategy.ui.UIT0;
import com.tone.gf.strategy.ui.UIT1;
import com.tone.gf.work.PriceWork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddPanel extends JPanel {
    private ModelTable modelTable;
    private final JButton btnStart;
    private final JTextField txtCode;
    private final JComboBox<Strategy> cbxStrategy;
    private UIT0 uit0;
    private UIT1 uit1;

    public AddPanel() {
        super();
        uit0 = new UIT0();
        uit1 = new UIT1();
        JPanel strategyUI = new JPanel();
        strategyUI.add(uit0, BorderLayout.CENTER);

        JLabel lblStrategy = new JLabel("策略：");
        cbxStrategy = new JComboBox<>(Strategy.values());
        cbxStrategy.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                switch ((Strategy)cbxStrategy.getSelectedItem()) {
                    case T0:
                        strategyUI.removeAll();
                        strategyUI.add(uit0, BorderLayout.CENTER);
                        strategyUI.updateUI();
                        break;
                    case T1:
                        strategyUI.removeAll();
                        strategyUI.add(uit1, BorderLayout.CENTER);
                        strategyUI.updateUI();
                        break;
                }
            }
        });
        this.add(lblStrategy, BorderLayout.LINE_START);
        this.add(cbxStrategy, BorderLayout.LINE_END);

        JCheckBox cbxMonitor = new JCheckBox("模拟");
        cbxMonitor.addChangeListener(e -> {
            if (cbxMonitor.isSelected()) {
                AppInfo.MONITOR = true;
            } else {
                AppInfo.MONITOR = false;
            }
        });
        this.add(cbxMonitor, BorderLayout.LINE_END);
        JLabel lblCode = new JLabel("代码");
        txtCode = new JTextField(4);
        this.add(lblCode, BorderLayout.LINE_END);
        this.add(txtCode, BorderLayout.LINE_END);

        this.add(strategyUI, BorderLayout.LINE_END);

        JButton btnAdd = new JButton("添加");
        btnAdd.addActionListener(e -> {
            Model model = null;
            switch ((Strategy)cbxStrategy.getSelectedItem()) {
                case T0:
                    model = uit0.getModel();
                    break;
                case T1:
                    model = uit1.getModel();
                    break;
            }
            model.setCode(txtCode.getText());
            modelTable.addModel(model);
        });
        this.add(btnAdd, BorderLayout.LINE_END);

        JButton btnTest = new JButton("测试");
        btnTest.addActionListener(e -> {
            String code = txtCode.getText();
            double price = uit1.getModel().getPrice();
            AppInfo.PRICES.put(code, price);
        });
        this.add(btnTest, BorderLayout.LINE_END);

        btnStart = new JButton("开始");
//        btnStart.setEnabled(false);
        JButton btnStop = new JButton("停止");
        btnStop.setEnabled(false);
        btnStart.addActionListener(e -> {
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
            AppInfo.START = true;
//            new Thread(new PriceWork()).start();
            modelTable.getModels().forEach(model -> {
                switch (model.getStrategy()) {
                    case T0:
                        new Thread(new StrategyT0((ModelT0) model)).start();
                        break;
                    case T1:
                        new Thread(new StrategyT1((ModelT1) model)).start();
                        break;
                }
            });
            btnAdd.setEnabled(false);
        });
        btnStop.addActionListener(e -> {
            btnStop.setEnabled(false);
            btnStart.setEnabled(true);
            AppInfo.START = false;
            btnAdd.setEnabled(true);
        });
        this.add(btnStart, BorderLayout.LINE_END);
        this.add(btnStop, BorderLayout.LINE_END);
    }

    public void setStartEnable() {
        btnStart.setEnabled(true);
    }

    public void setModelTable(ModelTable modelTable) {
        this.modelTable = modelTable;
    }

    public void setModel(Model model) {
        txtCode.setText(model.getCode());
        switch (model.getStrategy()) {
            case T0:
                uit0.setModel((ModelT0) model);
                break;
            case T1:
                uit1.setModel((ModelT1) model);
                break;
        }
        cbxStrategy.setSelectedItem(model.getStrategy());
    }
}
