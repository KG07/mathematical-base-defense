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
import java.awt.image.BufferedImage;

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
                Display.mainMenuImageButtonImages.add(bufferedImage);
                Display.mainMenuImageButtonPanels.add(panel);
                Display.mainMenuImageButtonPanelNames.add(nameOfBounds);
                break;
            }
            case SINGLEPLAYER_SCREEN:  {
                Display.singleplayerImageButtonImages.add(bufferedImage);
                Display.singleplayerImageButtonPanels.add(panel);
                Display.singleplayerImageButtonPanelNames.add(nameOfBounds);
                break;
            }
            case GAME_OVER_SCREEN: {
                Display.gameOverImageButtonImages.add(bufferedImage);
                Display.gameOverImageButtonPanels.add(panel);
                Display.gameOverImageButtonPanelNames.add(nameOfBounds);
                break;
            }
        }
    }
}