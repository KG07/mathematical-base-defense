package com.mathematicalbasedefense.mathematicalbasedefense.game.userinterface;

import com.mathematicalbasedefense.mathematicalbasedefense.game.Display;

import javax.swing.*;
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

        switch (screenToShowIn){
            case MAIN_MENU_SCREEN: {
                Display.mainMenuScreenImageImages.add(bufferedImage);
                Display.mainMenuScreenImagePanels.add(panel);
                Display.mainMenuScreenImagePanelNames.add(nameOfBounds);
                break;
            }
            case SINGLEPLAYER_SCREEN: {
                Display.singleplayerScreenImagePanels.add(panel);
                Display.singleplayerScreenImageImages.add(bufferedImage);
                Display.singleplayerScreenImagePanelNames.add(nameOfBounds);
                break;
            }
            case CREDITS_SCREEN: {
                Display.creditsScreenImagePanels.add(panel);
                Display.creditsScreenImageImages.add(bufferedImage);
                Display.creditsScreenImagePanelNames.add(nameOfBounds);
            }
        }

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
