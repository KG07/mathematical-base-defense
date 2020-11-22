package me.kg07.mathematicalbasedefense.game;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Game {


    //display
    private Display display;

    //declare fonts


    public Game(int width, int height, boolean fullscreen){

        try {
            // sorry for doing this lol
            //declare display
            display = new Display(width, height, fullscreen);

        } catch (Exception exception) {
            JFrame errorWindow = new JFrame();
            JLabel label = new JLabel();
            errorWindow.setSize(500, 100);
            errorWindow.setResizable(false);
            errorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            label.setText("An error has occurred while trying to launch the game.");
            errorWindow.add(label);
            errorWindow.setVisible(true);
            exception.printStackTrace();
        }

    }

    public void update(){

    }

    public void render(){
        display.render(this);
    }

}
