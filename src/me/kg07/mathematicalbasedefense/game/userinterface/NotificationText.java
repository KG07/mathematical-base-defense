package me.kg07.mathematicalbasedefense.game.userinterface;

import me.kg07.mathematicalbasedefense.game.Display;
import me.kg07.mathematicalbasedefense.game.core.Game;

import javax.swing.*;
import java.awt.*;

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
