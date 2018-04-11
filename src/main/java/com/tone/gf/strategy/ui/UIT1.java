package com.tone.gf.strategy.ui;

import com.tone.gf.strategy.model.ModelT1;

import javax.swing.*;
import java.awt.*;

public class UIT1 extends JPanel implements UI2Model<ModelT1> {
    private final JTextField txtPrice;
    private final JTextField txtFloatPrice;
    private final JTextField txtAmount;

    public UIT1() {
        super();
        JLabel lblPrice = new JLabel("价格：");
        txtPrice = new JTextField(5);
        this.add(lblPrice, BorderLayout.LINE_START);
        this.add(txtPrice, BorderLayout.LINE_END);
        JLabel lblFloatPrice = new JLabel("价差：");
        txtFloatPrice = new JTextField(5);
        this.add(lblFloatPrice, BorderLayout.LINE_END);
        this.add(txtFloatPrice, BorderLayout.LINE_END);
        JLabel lblAmount = new JLabel("数量：");
        txtAmount = new JTextField(5);
        this.add(lblAmount, BorderLayout.LINE_END);
        this.add(txtAmount, BorderLayout.LINE_END);
    }

    @Override
    public ModelT1 getModel() throws NumberFormatException {
        return new ModelT1(getDouble(txtPrice), getDouble(txtFloatPrice), getInt(txtAmount));
    }

    public void setModel(ModelT1 model) {
        txtPrice.setText(String.valueOf(model.getPrice()));
        txtFloatPrice.setText(String.valueOf(model.getFloatPrice()));
        txtAmount.setText(String.valueOf(model.getAmount()));
    }
}
