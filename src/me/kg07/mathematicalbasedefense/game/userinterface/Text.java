package me.kg07.mathematicalbasedefense.game.userinterface;

import me.kg07.mathematicalbasedefense.game.Display;
import me.kg07.mathematicalbasedefense.game.ErrorWindow;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Text {

    //declare fonts
    Font computerModernFont = null;
    Font computerModernFont12Pixels = null;
    Font computerModernFont16Pixels = null;
    Font computerModernFont20Pixels = null;
    Font computerModernFont24Pixels = null;
    Font computerModernFont32Pixels = null;
    Font computerModernFont48Pixels = null;
    Font computerModernFont72Pixels = null;

    public Text(String text, Font font, String name, String nameOfBounds) {
        try {
            // sorry for doing this lol
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            computerModernFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/me/kg07/mathematicalbasedefense/game/assets/fonts/computermodern.ttf")).deriveFont(12f);
            computerModernFont12Pixels = computerModernFont.deriveFont(12f);
            computerModernFont16Pixels = computerModernFont.deriveFont(16f);
            computerModernFont20Pixels = computerModernFont.deriveFont(20f);
            computerModernFont24Pixels = computerModernFont.deriveFont(24f);
            computerModernFont32Pixels = computerModernFont.deriveFont(32f);
            computerModernFont48Pixels = computerModernFont.deriveFont(48f);
            computerModernFont72Pixels = computerModernFont.deriveFont(72f);

            ge.registerFont(computerModernFont);
            ge.registerFont(computerModernFont12Pixels);
            ge.registerFont(computerModernFont16Pixels);
            ge.registerFont(computerModernFont20Pixels);
            ge.registerFont(computerModernFont24Pixels);
            ge.registerFont(computerModernFont32Pixels);
            ge.registerFont(computerModernFont48Pixels);
            ge.registerFont(computerModernFont72Pixels);


        } catch (FontFormatException | IOException exception){
            new ErrorWindow();
        }
    }
}
