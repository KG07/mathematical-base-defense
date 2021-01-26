package com.mathematicalbasedefense.mathematicalbasedefense.game.entities;

import com.mathematicalbasedefense.mathematicalbasedefense.game.Display;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Blood {

    int startXPos, startYPos, width, height, age, rotation;
    Color color;

    public Blood (int startXPos, int startYPos, int width, int height, Color color){
        this.startXPos = startXPos;
        this.startYPos = startYPos;
        this.width = width;
        this.height = height;
        this.color = color;
        this.age = 0;

        Random random = new Random();
        this.rotation = random.nextInt(360);

        JPanel panel = new JPanel();
        panel.setBounds(startXPos, startYPos, width, height);
        Display.singleplayerScreenBloodPanels.add(panel);
        Display.singleplayerScreenBloodAge.add(age);
        Display.singleplayerScreenBloodRotation.add(rotation);
        Display.singleplayerScreenBloodColor.add(color);

    }

    public int getStartXPos() {
        return startXPos;
    }

    public int getStartYPos() {
        return startYPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

}
