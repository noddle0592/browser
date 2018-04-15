package com.tone.gf;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends JPanel {
    private final AddPanel addPanel;

    public SettingPanel() {
        super(new GridLayout(2, 1));
        addPanel = new AddPanel();

        this.add(addPanel);
        ModelTable modelTable = new ModelTable();
        JScrollPane scrollPane = new JScrollPane(modelTable);
//        scrollPane.setPreferredSize(new Dimension(addPanel.getWidth(), 40));
        this.add(scrollPane);

        addPanel.setModelTable(modelTable);
        modelTable.setAddPanel(addPanel);

        AppInfo.MODEL_TABLE = modelTable;
    }

    public void setButtonEnable() {
        addPanel.setAddEnable();
    }
}
