package com.mathematicalbasedefense.mathematicalbasedefense.game.userinterface;

import com.mathematicalbasedefense.mathematicalbasedefense.game.Display;
import com.mathematicalbasedefense.mathematicalbasedefense.game.core.ErrorWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LaTeXText {
    //declare fonts
    Font computerModernFont = null;

    int xPos, yPos, boundsXPos, boundsYPos, boundsWidth, boundsHeight;
    String text, name, nameOfBounds;
    Font font;
    float fontSize;
    BufferStrategy bufferStrategy;
    Display.Screen screenToShowIn;


    public LaTeXText(String text, int xPos, int yPos, int boundsXPos, int boundsYPos, int boundsWidth, int boundsHeight, Font font, float fontSize, String name, String nameOfBounds, Display.Screen screenToShowIn, BufferStrategy bufferStrategy) {
        try {

            this.xPos = xPos;
            this.yPos = yPos;
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
            computerModernFont = Font.createFont(Font.TRUETYPE_FONT, new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\assets\\fonts\\computermodern.ttf"));
            graphicsEnvironment.registerFont(computerModernFont.deriveFont(fontSize));

            JPanel panel = new JPanel();
            panel.setBounds(boundsXPos, boundsYPos, boundsWidth, boundsHeight);
            ArrayList<Integer> whatToAdd = new ArrayList<Integer>();
            whatToAdd.add(xPos);
            whatToAdd.add(yPos);
            switch (screenToShowIn){
                case GAME_OVER_SCREEN: {
                    Display.gameOverScreenLaTeXTextPanels.add(panel);
                    Display.gameOverScreenLaTeXTextTexts.add(text);
                    Display.gameOverScreenLaTeXTextFonts.add(computerModernFont.deriveFont((float) fontSize));
                    Display.gameOverScreenLaTeXTextPanelNames.add(nameOfBounds);
                    break;
                }
            }

        } catch (FontFormatException | IOException exception){
            new ErrorWindow(exception.toString());
        }
    }
}
