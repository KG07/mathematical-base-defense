package me.kg07.mathematicalbasedefense.game.core;

import javax.swing.*;

public class ErrorWindow {

    public ErrorWindow(){
        JFrame errorWindow = new JFrame();
        JLabel label = new JLabel();
        errorWindow.setSize(500, 100);
        errorWindow.setResizable(false);
        errorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.setText("An error has occurred while trying the game is running.");
        errorWindow.add(label);
        errorWindow.setVisible(true);
    }
}
