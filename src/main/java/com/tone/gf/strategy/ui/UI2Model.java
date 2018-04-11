package com.tone.gf.strategy.ui;

import com.tone.gf.strategy.model.Model;

import javax.swing.*;

/**
 * 从界面获取模型设置值的接口
 */
public interface UI2Model<M extends Model> {
    /**
     * 获取UI代表的模型
     * @return 模型对象数据
     */
    M getModel() throws NumberFormatException;

    default double getDouble(JTextField textField) throws NumberFormatException {
        return Double.parseDouble(textField.getText());
    }

    default int getInt(JTextField textField) throws NumberFormatException {
        return Integer.parseInt(textField.getText());
    }
}
