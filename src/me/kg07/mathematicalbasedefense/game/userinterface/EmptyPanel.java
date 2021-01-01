package me.kg07.mathematicalbasedefense.game.userinterface;

import me.kg07.mathematicalbasedefense.game.Display;

import javax.swing.*;

public class EmptyPanel {

    int xPos, yPos, width, height;
    String name, nameOfBounds;
    Display.Screen screenToShowIn;

    public EmptyPanel(int xPos, int yPos, int width, int height, String name, String nameOfBounds, Display.Screen screenToShowIn){

        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.name = name;
        this.nameOfBounds = nameOfBounds;
        this.screenToShowIn = screenToShowIn;

        JPanel panel = new JPanel();
        panel.setBounds(xPos, yPos, width, height);
        switch (screenToShowIn){
            case SINGLEPLAYER_SCREEN: {
                Display.singleplayerEmptyPanelBounds.add(panel);
                Display.singleplayerEmptyPanelBoundNames.add(nameOfBounds);
            }
        }

    }


}
