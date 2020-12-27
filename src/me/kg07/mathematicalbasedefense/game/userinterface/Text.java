package me.kg07.mathematicalbasedefense.game.userinterface;

import me.kg07.mathematicalbasedefense.game.Display;
import me.kg07.mathematicalbasedefense.game.core.ErrorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Text {

    //declare fonts
    Font computerModernFont = null;

    int xPos, yPos, width, height, boundsXPos, boundsYPos, boundsWidth, boundsHeight;
    String text, name, nameOfBounds;
    Font font;
    float fontSize;
    BufferStrategy bufferStrategy;
    Display.Screen screenToShowIn;


    public Text(String text, int xPos, int yPos, int width, int height, int boundsXPos, int boundsYPos, int boundsWidth, int boundsHeight,  Font font, float fontSize, String name, String nameOfBounds, Display.Screen screenToShowIn, BufferStrategy bufferStrategy) {
        try {

            this.xPos = xPos;
            this.yPos = yPos;
            this.width = width;
            this.height = height;
            this.boundsXPos = boundsXPos;
            this.boundsYPos = boundsYPos;
            this.boundsWidth = boundsWidth;
            this.boundsHeight = boundsHeight;
            this.text = text;
            this.name = name;
            this.nameOfBounds = nameOfBounds;
            this.font = font;
            this.fontSize = fontSize;
            this.bufferStrategy = bufferStrategy;
            this.screenToShowIn = screenToShowIn;

            // sorry for doing this lol
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            computerModernFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/me/kg07/mathematicalbasedefense/game/assets/fonts/computermodern.ttf"));
            graphicsEnvironment.registerFont(computerModernFont.deriveFont(fontSize));


            JPanel panel = new JPanel();
            panel.setBounds(boundsXPos, boundsYPos, boundsWidth, boundsHeight);
            ArrayList<Integer> whatToAdd = new ArrayList<Integer>();
            whatToAdd.add(xPos);
            whatToAdd.add(yPos);
            whatToAdd.add(width);
            whatToAdd.add(height);
            switch (screenToShowIn){
                case MAIN_MENU_SCREEN: {
                    Display.mainMenuTextPanels.add(panel);
                    Display.mainMenuTextTexts.add(text);
                    Display.mainMenuTextFonts.add(computerModernFont.deriveFont((float) fontSize));
                    Display.mainMenuTextPanelNames.add(nameOfBounds);
                    Display.mainMenuTextMetrics.add(whatToAdd);
                    break;
                }
                case GAME_OVER_SCREEN: {
                    Display.gameOverTextPanels.add(panel);
                    Display.gameOverTextTexts.add(text);
                    Display.gameOverTextFonts.add(computerModernFont.deriveFont((float) fontSize));
                    Display.gameOverTextPanelNames.add(nameOfBounds);
                    Display.gameOverTextMetrics.add(whatToAdd);
                    break;
                }
            }

        } catch (FontFormatException | IOException exception){
            new ErrorWindow();
        }
    }
}
