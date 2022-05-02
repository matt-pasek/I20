package com.company;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(int width, int height, Team team) {
        setSize(width, height);
        setLocationRelativeTo(null);
        add(new GameField(team));
        setVisible(true);
    }
}
