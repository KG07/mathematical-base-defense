package com.mathematicalbasedefense.mathematicalbasedefense.game.userinterface;

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


import com.mathematicalbasedefense.mathematicalbasedefense.game.Display;
import javax.swing.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageButton {

    public int xPos, yPos, width, height;
    public BufferedImage bufferedImage;
    public String name, nameOfBounds;
    Display.Screen screenToShowIn;

    public ImageButton(BufferedImage bufferedImage, int xPos, int yPos, int width, int height,  String name, String nameOfBounds, Display.Screen screenToShowIn, BufferStrategy bufferStrategy){

        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.bufferedImage = bufferedImage;
        this.name = name;
        this.nameOfBounds = nameOfBounds;
        this.screenToShowIn = screenToShowIn;

        JPanel panel = new JPanel();
        panel.setBounds(xPos, yPos, width, height);
        switch (screenToShowIn){
            case MAIN_MENU_SCREEN:  {
                Display.mainMenuScreenImageButtonImages.add(bufferedImage);
                Display.mainMenuScreenImageButtonPanels.add(panel);
                Display.mainMenuScreenImageButtonPanelNames.add(nameOfBounds);
                break;
            }
            case SINGLEPLAYER_SCREEN:  {
                Display.singleplayerScreenImageButtonImages.add(bufferedImage);
                Display.singleplayerScreenImageButtonPanels.add(panel);
                Display.singleplayerScreenImageButtonPanelNames.add(nameOfBounds);
                break;
            }
            case GAME_OVER_SCREEN: {
                Display.gameOverScreenImageButtonImages.add(bufferedImage);
                Display.gameOverScreenImageButtonPanels.add(panel);
                Display.gameOverScreenImageButtonPanelNames.add(nameOfBounds);
                break;
            }
            case SETTINGS_SCREEN : {
                Display.settingsScreenImageButtonImages.add(bufferedImage);
                Display.settingsScreenImageButtonPanels.add(panel);
                Display.settingsScreenImageButtonPanelNames.add(nameOfBounds);
                break;
            }
            case CREDITS_SCREEN: {
                Display.creditsScreenImageButtonImages.add(bufferedImage);
                Display.creditsScreenImageButtonPanels.add(panel);
                Display.creditsScreenImageButtonPanelNames.add(nameOfBounds);
                break;
            }
        }
    }

}