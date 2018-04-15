package com.tone.gf;

import com.tone.gf.strategy.model.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ModelTable extends JTable {
    private AddPanel addPanel;
    private final DefaultTableModel tableModel;
    private final List<Model> models = new ArrayList<>();

    public ModelTable() {
        super();
        this.tableModel = new DefaultTableModel(new String[]{"策略", "代码", "价格", "价差", "数量", "可用", "持仓", "今买", "今卖"}, 1) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.setModel(tableModel);
        ListSelectionModel selectionModel = this.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                if (getSelectedRow() > 0) {
                    Model model = models.get(getSelectedRow() - 1);
                    addPanel.setModel(model);
                }
            }
        });
    }

    public void addModel(Model model) {
        int index = models.indexOf(model);
        if (index < 0) {
            models.add(model);
            tableModel.addRow(model.toArray());
        } else {
            // 替换
            models.set(index, model);
            Object[] values = model.toArray();
            for (int i = 0; i < values.length; i++) {
                tableModel.setValueAt(values[i], index, i);
            }
        }
    }

    public void setAddPanel(AddPanel addPanel) {
        this.addPanel = addPanel;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setValue(String code, int column, Object value) {
        int index = -1;
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i).getCode().equals(code)) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            tableModel.setValueAt(value, index, column);
        }
    }
}
