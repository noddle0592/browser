package com.tone.gf;

import com.tone.gf.strategy.model.Model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelTable extends JTable {
    private AddPanel addPanel;
    private final DefaultTableModel tableModel;
    private final List<Model> models = new ArrayList<>();

    public ModelTable() {
        super();
        this.tableModel = new DefaultTableModel(new String[]{"策略", "代码", "价格", "价差", "数量", "次数"}, 1) {
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
                Model model = models.get(getSelectedRow() - 1);
                addPanel.setModel(model);
            }
        });
    }

    public void addModel(Model model) {
        models.add(model);
        tableModel.addRow(model.toArray());
    }

    public void setAddPanel(AddPanel addPanel) {
        this.addPanel = addPanel;
    }

    public List<Model> getModels() {
        return models;
    }
}
