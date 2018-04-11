package com.tone.gf.strategy.ui;

import com.tone.gf.strategy.model.Model;
import com.tone.gf.strategy.model.ModelT0;

import javax.swing.*;
import java.awt.*;

public class UIT0 extends JPanel implements UI2Model<ModelT0> {
    private final JTextField txtPrice;
    private final JTextField txtFloatPrice;
    private final JTextField txtHighest;
    private final JTextField txtLowest;

    public UIT0() {
        super();
        JLabel lblPrice = new JLabel("价格：");
        txtPrice = new JTextField(5);
        this.add(lblPrice, BorderLayout.LINE_START);
        this.add(txtPrice, BorderLayout.LINE_END);
        JLabel lblFloatPrice = new JLabel("价差：");
        txtFloatPrice = new JTextField(5);
        this.add(lblFloatPrice, BorderLayout.LINE_END);
        this.add(txtFloatPrice, BorderLayout.LINE_END);
        JLabel lblHighest = new JLabel("最高：");
        txtHighest = new JTextField(5);
        this.add(lblHighest, BorderLayout.LINE_END);
        this.add(txtHighest, BorderLayout.LINE_END);
        JLabel lblLowest = new JLabel("最低：");
        txtLowest = new JTextField(5);
        this.add(lblLowest, BorderLayout.LINE_END);
        this.add(txtLowest, BorderLayout.LINE_END);
    }

    @Override
    public ModelT0 getModel() throws NumberFormatException {
        return new ModelT0(getDouble(txtPrice), getDouble(txtFloatPrice), getDouble(txtHighest), getDouble(txtLowest));
    }

    public void setModel(ModelT0 model) {
        txtPrice.setText(String.valueOf(model.getPrice()));
        txtFloatPrice.setText(String.valueOf(model.getFloatPrice()));
        txtHighest.setText(String.valueOf(model.getHighest()));
        txtLowest.setText(String.valueOf(model.getLowest()));
    }
}
