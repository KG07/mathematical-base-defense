package me.kg07.mathematicalbasedefense.game.userinterface;

import me.kg07.mathematicalbasedefense.game.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Image {

    int xPos, yPos, width, height;
    BufferedImage bufferedImage;
    String name, nameOfBounds;
    BufferStrategy bufferStrategy;
    Display.Screen screenToShowIn;

    public Image(BufferedImage bufferedImage, int xPos, int yPos, int width, int height,  String name, String nameOfBounds, Display.Screen screenToShowIn, BufferStrategy bufferStrategy){
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.bufferedImage = bufferedImage;
        this.name = name;
        this.nameOfBounds = nameOfBounds;
        this.screenToShowIn = screenToShowIn;
        this.bufferStrategy = bufferStrategy;
        JPanel panel = new JPanel();
        panel.setBounds(xPos, yPos, width, height);
        Display.singleplayerImagePanels.add(panel);
        Display.singleplayerImageImages.add(bufferedImage);
        Display.singleplayerImagePanelNames.add(nameOfBounds);
    }


    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public String getName() {
        return name;
    }

    public String getNameOfBounds() {
        return nameOfBounds;
    }

    public Display.Screen getScreenToShowIn() {
        return screenToShowIn;
    }

    public BufferStrategy getBufferStrategy() {
        return bufferStrategy;
    }
}
