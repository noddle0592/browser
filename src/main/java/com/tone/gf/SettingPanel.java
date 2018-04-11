package com.tone.gf;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends JPanel {
    public SettingPanel() {
        super(new GridLayout(2, 1));
        AddPanel addPanel = new AddPanel();

        this.add(addPanel);
        ModelTable modelTable = new ModelTable();
        JScrollPane scrollPane = new JScrollPane(modelTable);
//        scrollPane.setPreferredSize(new Dimension(addPanel.getWidth(), 40));
        this.add(scrollPane);

        addPanel.setModelTable(modelTable);
        modelTable.setAddPanel(addPanel);
    }
}
