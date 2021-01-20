package com.mathematicalbasedefense.mathematicalbasedefense.game.userinterface;

import com.mathematicalbasedefense.mathematicalbasedefense.game.core.Game;
import com.mathematicalbasedefense.mathematicalbasedefense.game.Display;

import javax.swing.*;
import java.awt.*;

import static com.mathematicalbasedefense.mathematicalbasedefense.game.Display.windowWidth;

public class NotificationText {

    String text, nameOfBounds;
    Font font;
    int age;
    JPanel panel;

    public NotificationText(String text, Font font, int age, String nameOfBounds){

        this.text = text;
        this.font = font;
        this.age = age;
        this.nameOfBounds = nameOfBounds;
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, Game.windowWidth, 40*Game.windowHeight/1080);
        Display.aliveNotificationTextPanels.add(panel);
        Display.aliveNotificationTextPanelNames.add(nameOfBounds);
        Display.aliveNotificationTextPanelOffsets.add(0);
        for (int i = 0; i < 40; i++) {
            try {
                Thread.sleep(3L);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            for (int j = 0; j < Display.aliveNotificationTextPanels.size()-1; j++){
                if (Display.aliveNotificationTextPanels.size() > 0 && Display.aliveNotificationTextPanelOffsets.size() > 0) {
                    if (Display.aliveNotificationTextPanelOffsets.size()-1 > j) {
                        Display.aliveNotificationTextPanelOffsets.set(j, Display.aliveNotificationTextPanelOffsets.get(j) + 1);
                    }
                }
            }
        }
    }


    public String getText() {
        return text;
    }

    public Font getFont() {
        return font;
    }

    public int getAge() {
        return age;
    }

    public JPanel getBounds(){
        return panel;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
