package com.mathematicalbasedefense.mathematicalbasedefense.game.core;

import com.mathematicalbasedefense.mathematicalbasedefense.game.Display;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow {

    public ErrorWindow(String messageToShowInScrollBox){


        JFrame errorWindow = new JFrame();
        JLabel label = new JLabel();
        errorWindow.setSize(600, 400);
        errorWindow.setResizable(false);
        errorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        errorWindow.setTitle("Mathematical Base Defense 0.3.0");
        label.setText("An error has occurred while the game is running.");
        errorWindow.getContentPane().setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea(15, 30);
        textArea.setEditable(false);
        textArea.setText(messageToShowInScrollBox);
        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        errorWindow.getContentPane().add(scrollPane);
        errorWindow.add(label);
        errorWindow.setVisible(true);
    }
}
