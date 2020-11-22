package me.kg07.mathematicalbasedefense.game.userinterface;

/*
 *  ImageButton class for Mathematical Base Defense
 *  The ImageButton class allows the program to create image buttons.
 *
 *  @author mistertfy64
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */


import me.kg07.mathematicalbasedefense.game.Display;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class ImageButton {
    public ImageButton(int xPos, int yPos, int width, int height, Image image, String name, String nameOfBounds, String screenToShowIn, BufferStrategy bufferStrategy){
        JPanel panel = new JPanel();
        panel.setBounds(xPos, yPos, width, height);
        switch (screenToShowIn){
            case "titleScreen" : {
                Display.titlePanelImages.add(image);
                Display.titlePanels.add(panel);
                Display.titlePanelNames.add(nameOfBounds);
            }
        }
    }
}