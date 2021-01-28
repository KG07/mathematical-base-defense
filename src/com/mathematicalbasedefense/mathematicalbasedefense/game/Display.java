package com.mathematicalbasedefense.mathematicalbasedefense.game;

/**
 * Display Class for Mathematical Base Defense
 * @author  mistertfy64
 * @version 0.1.0
 * @since   2020-11-15 or something i cant remember
 *
 */


import com.mathematicalbasedefense.mathematicalbasedefense.game.networking.Authentication;
import com.mathematicalbasedefense.mathematicalbasedefense.game.userinterface.animations.Fade;
import com.mathematicalbasedefense.mathematicalbasedefense.game.core.*;
import com.mathematicalbasedefense.mathematicalbasedefense.game.userinterface.*;
import com.mathematicalbasedefense.mathematicalbasedefense.game.userinterface.Image;

import net.coobird.thumbnailator.Thumbnails;
import org.scilab.forge.jlatexmath.*;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.util.ArrayList;

import static com.mathematicalbasedefense.mathematicalbasedefense.game.core.Game.*;
import static java.awt.RenderingHints.*;

//i dont like mdes and phuttipong lol

public class Display extends JFrame implements KeyListener, MouseListener {

    /*
     *
     * Register images here!
     *
     */

    //images
    //title screen
    BufferedImage mathematicalBaseDefenseTitleLogoImage;

    //main menu screen
    BufferedImage singleplayerButtonImage;
    BufferedImage multiplayerButtonImage;
    BufferedImage settingsButtonImage;
    BufferedImage quitButtonImage;
    BufferedImage schoolLogoImage;
    BufferedImage shopButtonImage;
    BufferedImage nationalSoftwareContestLogoImage;
    BufferedImage creditsButtonImage;
    BufferedImage mathematicalBaseDefenseLogoImage;


    /*
        Gameplay screens
     */

    //singleplayer screen
    BufferedImage sendButtonImage;
    BufferedImage baseImage;
    BufferedImage enemiesKilledIconImage;
    BufferedImage baseHealthIconImage;
    BufferedImage timeSpentInGameInMillisecondsIconImage;


    //game over screen
    BufferedImage retryButtonImage;
    BufferedImage returnToTitleScreenButtonImage;


    /*
        Other screens
     */

    //shop


    //settings
    BufferedImage settingsAudioSectionButtonImage;
    BufferedImage settingsVideoSectionButtonImage;
    BufferedImage settingsOnlineSectionButtonImage;
    BufferedImage settingsBackButtonImage;
    BufferedImage settingsLeftArrowButtonImage;
    BufferedImage settingsRightArrowButtonImage;
    BufferedImage settingsSelectLoginCodeButtonImage;
    BufferedImage settingsSelectUsernameButtonImage;



    //credits
    BufferedImage creditsBackButtonImage;
    BufferedImage creditsInformationImage;


    //global
    public static ArrayList<NotificationText> aliveNotificationTexts = new ArrayList<NotificationText>();
    public static ArrayList<JPanel> aliveNotificationTextPanels = new ArrayList<JPanel>();
    public static ArrayList<String> aliveNotificationTextPanelNames = new ArrayList<String>();
    public static ArrayList<Integer> aliveNotificationTextPanelOffsets = new ArrayList<Integer>();

    //title text
    //title screen text
    public static ArrayList<JPanel> mainMenuScreenTextPanels = new ArrayList<JPanel>();
    public static ArrayList<String> mainMenuScreenTextTexts = new ArrayList<String>();
    public static ArrayList<Font> mainMenuScreenTextFonts = new ArrayList<Font>();
    public static ArrayList<String> mainMenuScreenTextPanelNames = new ArrayList<String>();
    public static ArrayList<ArrayList<Integer>> mainMenuScreenTextMetrics = new ArrayList<ArrayList<Integer>>();
    //title screen image buttons
    public static ArrayList<JPanel> mainMenuScreenImageButtonPanels = new ArrayList<JPanel>();
    public static ArrayList<BufferedImage> mainMenuScreenImageButtonImages = new ArrayList<BufferedImage>();
    public static ArrayList<String> mainMenuScreenImageButtonPanelNames = new ArrayList<String>();
    //title screen images
    public static ArrayList<JPanel> mainMenuScreenImagePanels = new ArrayList<JPanel>();
    public static ArrayList<BufferedImage> mainMenuScreenImageImages = new ArrayList<BufferedImage>();
    public static ArrayList<String> mainMenuScreenImagePanelNames = new ArrayList<String>();

    //singleplayer screen
    //singleplayer screen images
    public static ArrayList<JPanel> singleplayerScreenImagePanels = new ArrayList<JPanel>();
    public static ArrayList<BufferedImage> singleplayerScreenImageImages = new ArrayList<BufferedImage>();
    public static ArrayList<String> singleplayerScreenImagePanelNames = new ArrayList<String>();
    //singleplayer screen image buttons
    public static ArrayList<JPanel> singleplayerScreenImageButtonPanels = new ArrayList<JPanel>();
    public static ArrayList<BufferedImage> singleplayerScreenImageButtonImages = new ArrayList<BufferedImage>();
    public static ArrayList<String> singleplayerScreenImageButtonPanelNames = new ArrayList<String>();
    //tiles
    public static ArrayList<JPanel> singleplayerScreenEmptyPanelBounds = new ArrayList<JPanel>();
    public static ArrayList<String> singleplayerScreenEmptyPanelBoundNames = new ArrayList<String>();
    //bl**d
    public static ArrayList<JPanel> singleplayerScreenBloodPanels = new ArrayList<JPanel>();
    public static ArrayList<Integer> singleplayerScreenBloodAge = new ArrayList<Integer>();
    public static ArrayList<Integer> singleplayerScreenBloodRotation = new ArrayList<Integer>();
    public static ArrayList<Color> singleplayerScreenBloodColor = new ArrayList<Color>();


    //game over screen
    //game over: text
    public static ArrayList<JPanel> gameOverScreenTextPanels = new ArrayList<JPanel>();
    public static ArrayList<String> gameOverScreenTextTexts = new ArrayList<String>();
    public static ArrayList<Font> gameOverScreenTextFonts = new ArrayList<Font>();
    public static ArrayList<String> gameOverScreenTextPanelNames = new ArrayList<String>();
    public static ArrayList<ArrayList<Integer>> gameOverScreenTextMetrics = new ArrayList<ArrayList<Integer>>();
    //game over: image buttons
    public static ArrayList<BufferedImage> gameOverScreenImageButtonImages = new ArrayList<BufferedImage>();
    public static ArrayList<JPanel> gameOverScreenImageButtonPanels = new ArrayList<JPanel>();
    public static ArrayList<String> gameOverScreenImageButtonPanelNames = new ArrayList<String>();
    //game over: LaTeX text
    public static ArrayList<JPanel> gameOverScreenLaTeXTextPanels = new ArrayList<JPanel>();
    public static ArrayList<String> gameOverScreenLaTeXTextTexts = new ArrayList<String>();
    public static ArrayList<Font> gameOverScreenLaTeXTextFonts = new ArrayList<Font>();
    public static ArrayList<String> gameOverScreenLaTeXTextPanelNames = new ArrayList<String>();
    //settings
    //settings screen image buttons
    public static ArrayList<JPanel> settingsScreenImageButtonPanels = new ArrayList<JPanel>();
    public static ArrayList<BufferedImage> settingsScreenImageButtonImages = new ArrayList<BufferedImage>();
    public static ArrayList<String> settingsScreenImageButtonPanelNames = new ArrayList<String>();
    //credits
    //credits screen image button
    public static ArrayList<JPanel> creditsScreenImageButtonPanels = new ArrayList<JPanel>();
    public static ArrayList<BufferedImage> creditsScreenImageButtonImages = new ArrayList<BufferedImage>();
    public static ArrayList<String> creditsScreenImageButtonPanelNames = new ArrayList<String>();
    //credits screen
    public static ArrayList<JPanel> creditsScreenImagePanels = new ArrayList<JPanel>();
    public static ArrayList<BufferedImage> creditsScreenImageImages = new ArrayList<BufferedImage>();
    public static ArrayList<String> creditsScreenImagePanelNames = new ArrayList<String>();;

    //other variables

    //whether to show stats panel
    public static boolean showStatsPanel = false;

    //color to fill (animation)
    static Color colorToGraduallyFillAndThenFadeInProblemBox;
    static int widthToFillColor;
    static float widthToFillColorOpacity;


    public boolean hoverDetectionActive = true;


    private Canvas canvas;

    public Color backgroundColor = new Color(238, 238, 238);

    public boolean fullscreen = false;

    //main menu screen
    public static boolean cursorIsHoveringOnSingleplayerButton = false;
    public static boolean cursorIsHoveringOnMultiplayerButton = false;
    public static boolean cursorIsHoveringOnShopButton = false;
    public static boolean cursorIsHoveringOnOptionsButton = false;
    public static boolean cursorIsHoveringOnCreditsButton = false;
    public static boolean cursorIsHoveringOnQuitButton = false;

    public static boolean cursorIsHoveringOnSettingsVideoSectionButton = false;
    public static boolean cursorIsHoveringOnSettingsAudioSectionButton = false;
    public static boolean cursorIsHoveringOnSettingsOnlineSectionButton = false;
    public static boolean cursorIsHoveringOnSettingsBackButton = false;

    public static boolean cursorIsHoveringOnCreditsBackButton = false;

    public static int[] mainMenuScreenButtonsXOffset = new int[8];
    public static int[] settingsScreenButtonsXOffset = new int[4];
    public static int creditsScreenBackButtonXOffset = -400;


    static Graphics2D problemPanelGraphics2D;
    Graphics2D canvasGraphics2D;
    Graphics2D imageGraphics2D;
    Graphics2D imageButtonGraphics2D;
    Graphics2D englishTextGraphics2D;
    Graphics2D tooltipGraphics2D;
    Graphics2D effectGraphics2D;

    //single player screen
    Graphics2D singleplayerTileGraphics2D;
    Graphics2D singleplayerTileTermGraphics2D;
    Graphics2D singleplayerUserInterfaceGraphics2D;
    Graphics2D statsPanelGraphics2D;
    Graphics2D enemyGraphics2D;
    Graphics2D LaTeXGraphics2D;
    Graphics2D bloodGraphics2D;




    BufferStrategy bufferStrategy;

    //global stuff
    //state
    public enum State {
        //start screen
        TITLE_SCREEN,
        //title screen
        MAIN_MENU_SCREEN,
        //singleplayer
        SINGLEPLAYER_SCREEN,
        SINGLEPLAYER_SCREEN_PLAYING,
        //game over screen
        GAME_OVER_SCREEN,


        MULTIPLAYER_SCREEN,
        SETTINGS_SCREEN,
        CREDITS_SCREEN,
    }

    public enum Screen {
        //start screen
        TITLE_SCREEN,
        //title screen
        MAIN_MENU_SCREEN,
        //singleplayer
        SINGLEPLAYER_SCREEN,
        //game over screen
        GAME_OVER_SCREEN,


        MULTIPLAYER_SCREEN,
        SETTINGS_SCREEN,
        CREDITS_SCREEN,
    }

    public enum Subscreen {
        NONE,
        SETTINGS_VIDEO_SCREEN,
        SETTINGS_AUDIO_SCREEN,
        SETTINGS_ONLINE_SCREEN
    }

    public enum Language {
        ENGLISH,
        THAI,
    }

    public enum SoundLevel {
        ON,
        OFF,
    }

    public enum UserCredential {
        USERNAME,
        LOGIN_CODE,
    }



    public static State currentState = State.MAIN_MENU_SCREEN;
    public static Screen currentScreenShown = Screen.MAIN_MENU_SCREEN;
    public static Subscreen currentSubscreenShown = Subscreen.NONE;
    public static Language currentLanguage = Language.ENGLISH;
    public static SoundLevel currentSoundLevel = SoundLevel.ON;
    public static UserCredential userCredentialToWriteOn = UserCredential.USERNAME;

    public JFrame displayWindow = new JFrame();

    public GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

    public static int windowWidth, windowHeight;

    public static int realWindowWidth, realWindowHeight;

    //english fonts init
    Font computerModernFont = null;

    //Display Object is righttttttttttt hereeeeeeeeeeeeeeee!
    //This is called on the first frame
    public Display(int resolutionWidth, int resolutionHeight){
        try {
            // sorry for doing this lol

            computerModernFont = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/fonts/computermodern.ttf"))));
            graphicsEnvironment.registerFont(computerModernFont);


            currentState = State.MAIN_MENU_SCREEN;

            displayWindow = new JFrame();


            displayWindow.setTitle("Mathematical Base Defense 0.3.0");
            displayWindow.setIconImage(ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/logo.png")))));
            displayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            displayWindow.setResizable(false);
            displayWindow.setAlwaysOnTop(true);

            windowWidth = resolutionWidth;
            windowHeight = resolutionHeight;

            canvas = new Canvas();
            canvas.setPreferredSize(new Dimension(resolutionWidth, resolutionHeight));
            canvas.addMouseListener(this);
            canvas.addKeyListener(this);


            //set display's properties

            //If fullscreen = true, resolutionWidth and resolutionHeight are ignored.
            if (Game.fullscreenEnabled) {
                setScreenSizeAccordingToFullScreenVariable(displayWindow, graphicsDevice);
            } else {
                setSize(resolutionWidth, resolutionHeight);
            }


            realWindowWidth = windowWidth;
            realWindowHeight = windowHeight;

            //add stuff to display
            displayWindow.add(canvas);
            displayWindow.pack();

            canvas.createBufferStrategy(3);
            bufferStrategy = canvas.getBufferStrategy();
            //show display
            displayWindow.setLocationRelativeTo(null);
            displayWindow.setVisible(true);


            initializeUserInterfaceComponents(bufferStrategy);


        } catch (Exception exception) {
            displayWindow.dispose();
            new ErrorWindow(exception.toString());
            exception.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int xCoordinateOfClickedPosition = mouseEvent.getX();
        int yCoordinateOfClickedPosition = mouseEvent.getY();
        LogMessage.logMessage("Mouse Clicked at " + "(" + xCoordinateOfClickedPosition + ", " + yCoordinateOfClickedPosition + ")", LogMessage.MessageType.INFO);
        switch (currentScreenShown) {
            case MAIN_MENU_SCREEN: {
                for (int i = 0; i < mainMenuScreenImageButtonPanels.size(); i++) {
                    if (checkIfPointIsInBounds(mainMenuScreenImageButtonPanels.get(i), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)) {
                        switch (mainMenuScreenImageButtonPanelNames.get(i)) {
                            case "singleplayerButtonBounds": {
                                Fade.fadeIn(10L, 0.01f, 98, 0.00f, Color.BLACK);
                                Fade.fadeEffectCurrentOpacity = 1f;
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException interruptedException) {
                                    interruptedException.printStackTrace();
                                }
                                currentState = State.SINGLEPLAYER_SCREEN;
                                currentScreenShown = Screen.SINGLEPLAYER_SCREEN;
                                Game.startSingleplayerGame();
                                Fade.fadeOut(10L, 0.01f, 98, 1.00f, Color.BLACK);
                                Fade.fadeEffectCurrentOpacity = 0f;
                                break;
                            }
                            case "multiplayerButtonBounds": {
                                NotificationText nt = new NotificationText("Multiplayer will be added in the next update!", getComputerModernFontOfSpecifiedSize(32), 0, "ntBounds");
                                aliveNotificationTexts.add(nt);
                                break;
                            }
                            case "shopButtonBounds": {
                                NotificationText nt = new NotificationText("The shop will be added in the next update!", getComputerModernFontOfSpecifiedSize(32), 0, "ntBounds");
                                aliveNotificationTexts.add(nt);
                                break;
                            }
                            case "settingsButtonBounds": {
                                changeMainMenuScreenButtons(Screen.MAIN_MENU_SCREEN, Screen.SETTINGS_SCREEN);
                                Display.currentSubscreenShown = Subscreen.SETTINGS_VIDEO_SCREEN;
                                break;
                            }
                            case "creditsButtonBounds": {
                                changeMainMenuScreenButtons(Screen.MAIN_MENU_SCREEN, Screen.CREDITS_SCREEN);
                                break;
                            }
                            case "quitButtonBounds": {
                                System.exit(0);
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case SINGLEPLAYER_SCREEN: {
                String tileLetters = "ABCDEFG";
                for (int r = 0; r < 7; r++) {
                    for (int c = 0; c < 7; c++) {
                        if (checkIfPointIsInBounds(singleplayerScreenEmptyPanelBounds.get(singleplayerScreenEmptyPanelBoundNames.indexOf("tile" + tileLetters.charAt(r) + (c + 1) + "Bounds")), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)) {
                            if (Game.singleplayerTilesSelectionState[r][c]) {
                                //selected == true
                                Game.singleplayerTilesSelectionState[r][c] = false;
                                Game.removeTermFromProblem("tile" + tileLetters.charAt(c) + (r + 1));
                                break;
                            } else {
                                //selected == false
                                Game.singleplayerTilesSelectionState[r][c] = true;
                                Game.addTermToProblem("tile" + tileLetters.charAt(c) + (r + 1));
                                break;
                            }
                        }
                    }
                }
                if (checkIfPointIsInBounds(singleplayerScreenImageButtonPanels.get(singleplayerScreenImageButtonPanelNames.indexOf("sendButtonBounds")), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)) {
                    LogMessage.logMessage("Sending problem " + Game.problem, LogMessage.MessageType.INFO);
                    Evaluator.sendProblem(Game.problem);
                    break;
                }
                break;
            }
            case GAME_OVER_SCREEN: {
                for (int i = 0; i < gameOverScreenImageButtonImages.size(); i++) {
                    if (checkIfPointIsInBounds(gameOverScreenImageButtonPanels.get(i), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)) {
                        switch (gameOverScreenImageButtonPanelNames.get(i)) {
                            case "retryButtonBounds": {
                                Game.startSingleplayerGame();
                                currentState = State.SINGLEPLAYER_SCREEN_PLAYING;
                                currentScreenShown = Screen.SINGLEPLAYER_SCREEN;
                                break;
                            }
                            case "returnToTitleScreenButtonBounds": {
                                changeScreen(Screen.GAME_OVER_SCREEN, Screen.MAIN_MENU_SCREEN);
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case SETTINGS_SCREEN: {
                for (int i = 0; i < settingsScreenImageButtonPanels.size(); i++) {
                    if (checkIfPointIsInBounds(settingsScreenImageButtonPanels.get(i), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)) {
                        switch (settingsScreenImageButtonPanelNames.get(i)) {
                            case "settingsVideoSectionButtonBounds": {
                                Display.currentSubscreenShown = Subscreen.SETTINGS_VIDEO_SCREEN;
                                break;
                            }
                            case "settingsAudioSectionButtonBounds": {
                                Display.currentSubscreenShown = Subscreen.SETTINGS_AUDIO_SCREEN;
                                break;
                            }
                            case "settingsOnlineSectionButtonBounds" : {
                                Display.currentSubscreenShown = Subscreen.SETTINGS_ONLINE_SCREEN;
                                break;
                            }
                            case "settingsBackButtonBounds": {
                                Game.updateSettingsJSONFile();
                                changeMainMenuScreenButtons(Screen.SETTINGS_SCREEN, Screen.MAIN_MENU_SCREEN);
                                Game.getVariablesFromSettingsJSONFile();
                                break;
                            }

                        }
                        switch (currentSubscreenShown){
                            case SETTINGS_VIDEO_SCREEN: {
                                switch (settingsScreenImageButtonPanelNames.get(i)) {
                                    case "settingsLeftArrowForEnemyColorSelectionButtonBounds": {
                                        Game.changeSelectedEnemyColor(-1);
                                        break;
                                    }
                                    case "settingsRightArrowForEnemyColorSelectionButtonBounds": {
                                        Game.changeSelectedEnemyColor(1);
                                        break;
                                    }
                                }
                                break;
                            }
                            case SETTINGS_AUDIO_SCREEN : {
                                switch (settingsScreenImageButtonPanelNames.get(i)) {
                                    case "settingsLeftArrowForSoundVolumeSelectionButtonBounds": {
                                        Game.changeSelectedSoundVolume(-1);
                                        break;
                                    }
                                    case "settingsRightArrowForSoundVolumeSelectionButtonBounds": {
                                        Game.changeSelectedSoundVolume(1);
                                        break;
                                    }
                                }
                                break;
                            }
                            case SETTINGS_ONLINE_SCREEN: {
                                switch (settingsScreenImageButtonPanelNames.get(i)){
                                    case "settingsSelectLoginCodeButtonBounds" : {
                                        userCredentialToWriteOn = UserCredential.LOGIN_CODE;

                                        break;
                                    }
                                    case "settingsSelectUsernameButtonBounds" : {
                                        userCredentialToWriteOn = UserCredential.USERNAME;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                }
                break;
            }
            case CREDITS_SCREEN: {
                for (int i = 0; i < creditsScreenImageButtonPanels.size(); i++) {
                    if (checkIfPointIsInBounds(creditsScreenImageButtonPanels.get(i), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)) {
                        switch (creditsScreenImageButtonPanelNames.get(i)) {
                            case "creditsBackButtonBounds": {
                                changeMainMenuScreenButtons(Screen.CREDITS_SCREEN, Screen.MAIN_MENU_SCREEN);
                            }
                        }
                    }
                }
                break;
            }
        }
    }


    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        LogMessage.logMessage("Pressed and released key " + e.getKeyCode(), LogMessage.MessageType.INFO);
        if (currentScreenShown != Screen.SETTINGS_SCREEN) {
            switch (e.getKeyCode()) {
                //Esc --> Sets baseHealth to 0, triggering a Game Over.
                case KeyEvent.VK_ESCAPE: {
                    if (currentState == State.SINGLEPLAYER_SCREEN_PLAYING) {
                        baseHealth = 0;
                    }
                    break;
                }
                //F9 --> Toggles Insane Mode
                case KeyEvent.VK_F9: {
                    NotificationText nt;
                    if (Game.insaneModeActivated) {
                        nt = new NotificationText("Insane Mode Deactivated...", getComputerModernFontOfSpecifiedSize(32), 0, "ntBounds");
                    } else {
                        nt = new NotificationText("Insane Mode Activated!", getComputerModernFontOfSpecifiedSize(32), 0, "ntBounds");
                    }
                    aliveNotificationTexts.add(nt);
                    Game.insaneModeActivated = !Game.insaneModeActivated;
                    break;
                }
                //F10 --> Toggles Stats Panel Visibility
                case KeyEvent.VK_F10: {
                    showStatsPanel = !showStatsPanel;
                    break;
                }
                //F11 --> Toggles fullscreen
                case KeyEvent.VK_F11: {
                    if (currentScreenShown != Screen.SINGLEPLAYER_SCREEN) {
                        toggleFullScreen();
                    } else {
                        NotificationText nt = new NotificationText("You may not toggle fullscreen during gameplay!", getComputerModernFontOfSpecifiedSize(32), 0, "ntBounds");
                        aliveNotificationTexts.add(nt);
                    }

                    break;
                }
            }
        } else {
            //current screen shown = setting screen
            if (e.getKeyChar() == (char) 8) { //check if key char is backspace
                switch (userCredentialToWriteOn){
                    case USERNAME: {
                        if (Game.usernameToLoginWith != null && Game.usernameToLoginWith.length() > 0) {
                            Game.usernameToLoginWith = Game.usernameToLoginWith.substring(0, Game.usernameToLoginWith.length() - 1);
                            break;
                        }
                    }
                    case LOGIN_CODE: {
                        if (Game.loginCodeToLoginWith != null && Game.loginCodeToLoginWith.length() > 0) {
                            Game.loginCodeToLoginWith = Game.loginCodeToLoginWith.substring(0, Game.loginCodeToLoginWith.length() - 1);
                            break;
                        }
                    }
                }

            }  else {
                switch (userCredentialToWriteOn){
                    case USERNAME: {
                        Game.usernameToLoginWith += e.getKeyChar();
                        break;
                    }
                    case LOGIN_CODE: {
                        Game.loginCodeToLoginWith += e.getKeyChar();
                        break;
                    }
                }

            }
        }
    }

    public void toggleFullScreen() {
        Loop.gameAllowedToRender = false;
        clearAllUserInterfaceComponentArrayLists();
        initializeUserInterfaceComponents(canvas.getBufferStrategy());
        fullscreen = !fullscreen;
        setScreenSizeAccordingToFullScreenVariable(displayWindow, graphicsDevice);
    }

    public void setScreenSizeAccordingToFullScreenVariable(JFrame displayToMakeFullScreen, GraphicsDevice graphicsDevice){
        if (fullscreen) {
            graphicsDevice.setFullScreenWindow(null);
        } else {
            graphicsDevice.setFullScreenWindow(displayToMakeFullScreen);
        }
    }

    /**
     * Renders the frame.
     *
     * @param game dfdiuua9sidfiouadsiufohdasiufhaiuhdfaiudhasdfiujhsduihaf
     */
    public void render(Game game) {

        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        //global screen (all screens)
        canvasGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        imageGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        imageButtonGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        englishTextGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        tooltipGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        effectGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();


        //single player screen
        singleplayerTileGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        singleplayerTileTermGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        singleplayerUserInterfaceGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        statsPanelGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        enemyGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        problemPanelGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        LaTeXGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        bloodGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        
        //set quality for stuff
        englishTextGraphics2D.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);


        //get canvas
        canvasGraphics2D.setColor(backgroundColor);
        canvasGraphics2D.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());


        //other variables
        FontMetrics englishTextFontMetrics = LaTeXGraphics2D.getFontMetrics();
        FontMetrics LaTeXTextFontMetrics = LaTeXGraphics2D.getFontMetrics();



        //actually rendering stuff
        switch (currentScreenShown) {
            case TITLE_SCREEN: {
                break;
            }
            case MAIN_MENU_SCREEN: {
                //text
                renderImage(mainMenuScreenImageImages.get(0), mainMenuScreenImagePanels.get(0).getX(), mainMenuScreenImagePanels.get(0).getY(), null, false, false, 1, 1, mainMenuScreenImagePanels.get(0), imageGraphics2D);
                //image buttons
                for (int i = 0; i < mainMenuScreenImageButtonPanels.size(); i++) {
                    if (i <= 6) {
                        renderImage(mainMenuScreenImageButtonImages.get(i), mainMenuScreenImageButtonPanels.get(i).getX() + mainMenuScreenButtonsXOffset[i], mainMenuScreenImageButtonPanels.get(i).getY(), null, false, false, 1, 1, mainMenuScreenImageButtonPanels.get(i), imageButtonGraphics2D);
                    } else {
                        renderImage(mainMenuScreenImageButtonImages.get(i), mainMenuScreenImageButtonPanels.get(i).getX(), mainMenuScreenImageButtonPanels.get(i).getY(), null, false, false, 1, 1, mainMenuScreenImageButtonPanels.get(i), imageButtonGraphics2D);
                    }
                }
                break;
            }
            case SINGLEPLAYER_SCREEN: {
                //set stuff
                singleplayerTileGraphics2D.setColor(Color.BLACK);
                singleplayerTileGraphics2D.setFont(getComputerModernFontOfSpecifiedSize(72));
                singleplayerTileTermGraphics2D.setFont(getComputerModernFontOfSpecifiedSize(72));
                englishTextGraphics2D.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);


                //blood
                /*
                for (int b = 0; b < singleplayerScreenBloodPanels.size(); b++){
                    bloodGraphics2D.setColor(singleplayerScreenBloodColor.get(b));
                    bloodGraphics2D.fillRect(singleplayerScreenBloodPanels.get(b).getX(), singleplayerScreenBloodPanels.get(b).getY(), singleplayerScreenBloodPanels.get(b).getWidth(),singleplayerScreenBloodPanels.get(b).getHeight());
                }
                 */


                //userinterface
                //expression/equation display panel
                singleplayerUserInterfaceGraphics2D.drawRect((int) (90 * windowWidth / 1920), (int) (370 * windowHeight / 1080), (int) (1740 * windowWidth / 1920), (int) (80 * windowHeight / 1080));
                //text on problem panel
                renderLaTeXInProblemBox(Game.problemInLaTeX, (int) (90 * windowWidth / 1920), (int) (385 * windowWidth / 1920), (int) (72 * windowHeight / 1080), true, false, singleplayerScreenEmptyPanelBounds.get(singleplayerScreenEmptyPanelBoundNames.indexOf("problemPanelBounds")), Color.BLACK, englishTextGraphics2D);
                //color on problem panel
                if (colorToGraduallyFillAndThenFadeInProblemBox != null) {
                    problemPanelGraphics2D.setColor(colorToGraduallyFillAndThenFadeInProblemBox);
                    int type = AlphaComposite.SRC_OVER;
                    float alphaCompositeOpacity = widthToFillColorOpacity;

                    //prevent overflowing to prevent exception
                    if (alphaCompositeOpacity > 1f) {
                        alphaCompositeOpacity = 1f;
                    }
                    if (alphaCompositeOpacity < 0f) {
                        alphaCompositeOpacity = 0f;
                    }

                    AlphaComposite problemPanelGraphicsAlphaComposite = AlphaComposite.getInstance(type, alphaCompositeOpacity);
                    problemPanelGraphics2D.setComposite(problemPanelGraphicsAlphaComposite);
                    problemPanelGraphics2D.fillRect(91*windowWidth/1920, 371*windowHeight/1080, widthToFillColor, 79*windowHeight/1080);
                }

                //game statistics
                renderImage(enemiesKilledIconImage, (int) 1224 * windowWidth / 1920, (int) 512 * windowWidth / 1920, null, false, false, 1, 1, null, imageGraphics2D);
                renderImage(baseHealthIconImage,(int) 1224 * windowWidth / 1920, (int) 552 * windowWidth / 1920, null, false, false, 1, 1, null, imageGraphics2D);
                renderImage(timeSpentInGameInMillisecondsIconImage, (int) 1224 * windowWidth / 1920, (int) 592 * windowWidth / 1920, null, false, false, 1, 1, null, imageGraphics2D);

                if (Game.recentScoreIncrease > 0) {
                    renderLaTeX("S = " + Double.toString(Game.score) + "   (+" + Game.recentScoreIncrease + ")", (int) 1224 * windowWidth / 1920, (int) 468 * windowWidth / 1920,  (int) (32 * windowWidth/1920), false, false, false, null, Color.BLACK, LaTeXGraphics2D);
                } else {
                    renderLaTeX("S = " + Double.toString(Game.score), (int) 1224 * windowWidth / 1920, (int) 468 * windowWidth / 1920,  (int) (32 * windowWidth/1920), false, false, false, null, Color.BLACK, LaTeXGraphics2D);
                }
                renderText(Long.toString(Game.enemiesKilled), (int) 1260 * windowWidth / 1920, (int) 540 * windowWidth / 1920, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
                renderText(Long.toString(baseHealth), (int) 1260 * windowWidth / 1920, (int) 580 * windowWidth / 1920, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
                renderText(Game.timeSpentInGameInMilliseconds + "ms", (int) 1260 * windowWidth / 1920, (int) 620 * windowWidth / 1920, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);


                //images


                //base
                renderImage(baseImage, singleplayerScreenImagePanels.get(singleplayerScreenImagePanelNames.indexOf("baseBounds")).getX(), singleplayerScreenImagePanels.get(singleplayerScreenImagePanelNames.indexOf("baseBounds")).getY(), null, false, false, 1, 1, mainMenuScreenTextPanels.get(singleplayerScreenImagePanelNames.indexOf("baseBounds")), imageGraphics2D);

                //side panels
                singleplayerUserInterfaceGraphics2D.drawRect((int) (90 * windowWidth / 1920), (int) (460 * windowHeight / 1080), (int) (620 * windowWidth / 1920), (int) (570 * windowHeight / 1080));
                singleplayerUserInterfaceGraphics2D.drawRect((int) (1210 * windowWidth / 1920), (int) (460 * windowHeight / 1080), (int) (620 * windowWidth / 1920), (int) (570 * windowHeight / 1080));

                //send button
                renderImage(sendButtonImage, (int) 750 * windowWidth / 1920, (int) 920 * windowHeight / 1080, null, false, false, 1, 1, singleplayerScreenImagePanels.get(singleplayerScreenImageButtonPanelNames.indexOf("sendButtonBounds")), imageGraphics2D);
                singleplayerUserInterfaceGraphics2D.drawRect((int) 750 * windowWidth / 1920, (int) 920 * windowHeight / 1080, 420 * windowWidth / 1920, 110 * windowHeight / 1080);


                //tiles

                String tileLetters = "ABCDEFG";

                for (int r = 0; r < 7; r++) {
                    for (int c = 0; c < 7; c++) {
                        if (Game.singleplayerTilesSelectionState[c][r]) {
                            //selected == true
                            singleplayerTileGraphics2D.setColor(new Color(32, 32, 32));
                            singleplayerTileGraphics2D.fillRect((int) ((750 + (60 * r)) * windowWidth / 1920), (int) ((460 + (60 * c)) * windowHeight / 1080), (int) (60 * windowWidth / 1920), (int) (60 * windowHeight / 1080));
                            singleplayerTileTermGraphics2D.setColor(Color.WHITE);
                            singleplayerTileGraphics2D.setColor(Color.BLACK);
                            singleplayerTileGraphics2D.drawRect((int) ((750 + (60 * r)) * windowWidth / 1920), (int) ((460 + (60 * c)) * windowHeight / 1080), (int) (60 * windowWidth / 1920), (int) (60 * windowHeight / 1080));
                            renderLaTeX(Game.singleplayerTileTerms[c][r], (int) (760 + (60 * r)) * windowWidth / 1920, (int) (450 + (60 * c)) * windowHeight / 1080, 48 * windowWidth / 1920f, false, false, false, singleplayerScreenEmptyPanelBounds.get(singleplayerScreenEmptyPanelBoundNames.indexOf("tile" + tileLetters.charAt(c) + (r + 1) + "Bounds")), Color.WHITE, singleplayerTileTermGraphics2D);
                        } else {
                            //selected == false
                            singleplayerTileGraphics2D.setColor(Color.BLACK);
                            singleplayerTileTermGraphics2D.setColor(Color.BLACK);
                            singleplayerTileGraphics2D.drawRect((int) ((750 + (60 * r)) * windowWidth / 1920), (int) ((460 + (60 * c)) * windowHeight / 1080), (int) (60 * windowWidth / 1920), (int) (60 * windowHeight / 1080));
                            renderLaTeX(Game.singleplayerTileTerms[c][r], (int) (760 + (60 * r)) * windowWidth / 1920, (int) ((450 + (60 * c)) * windowHeight / 1080), 48 * windowWidth / 1920f, false, false, false, singleplayerScreenEmptyPanelBounds.get(singleplayerScreenEmptyPanelBoundNames.indexOf("tile" + tileLetters.charAt(c) + (r + 1) + "Bounds")), Color.BLACK, singleplayerTileTermGraphics2D);
                        }
                    }
                }

                //variable values

                String variables = "abcdnxyz";
                for (int i = 0; i < 8; i++) {
                    String valueToRender = "";
                    switch (i) {
                        case 0: {
                            valueToRender = String.valueOf(Game.valueOfVariableA);
                            break;
                        }
                        case 1: {
                            valueToRender = String.valueOf(Game.valueOfVariableB);
                            break;
                        }
                        case 2: {
                            valueToRender = String.valueOf(Game.valueOfVariableC);
                            break;
                        }
                        case 3: {
                            valueToRender = String.valueOf(Game.valueOfVariableD);
                            break;
                        }
                        case 4: {
                            valueToRender = String.valueOf(Game.valueOfVariableN);
                            break;
                        }
                        case 5: {
                            valueToRender = String.valueOf(Game.valueOfVariableX);
                            break;
                        }
                        case 6: {
                            valueToRender = String.valueOf(Game.valueOfVariableY);
                            break;
                        }
                        case 7: {
                            valueToRender = String.valueOf(Game.valueOfVariableZ);
                            break;
                        }


                    }
                    renderLaTeX(variables.charAt(i) + " = " + valueToRender, (int) 1220 * windowWidth / 1920, (int) (690 + (40 * i)) * windowWidth / 1920, 32f * windowWidth / 1920, false, false, false, null, Color.BLACK, englishTextGraphics2D);
                }
                //enemies
                for (int i = 0; i < Game.aliveEnemies.size(); i++) {
                    //set properties
                    enemyGraphics2D.setColor(Game.aliveEnemies.get(i).getColor());
                    //render enemy
                    enemyGraphics2D.fillRect(Game.aliveEnemies.get(i).getXPos(), Game.aliveEnemies.get(i).getYPos(), Game.aliveEnemies.get(i).getWidth(), Game.aliveEnemies.get(i).getHeight());
                    //render stuff on top of enemy
                    renderLaTeX(String.valueOf(Game.aliveEnemies.get(i).getRequestedValue()), (2 *Game.aliveEnemies.get(i).getXPos() + Game.aliveEnemies.get(i).getWidth() - englishTextFontMetrics.stringWidth(Game.aliveEnemies.get(i).getRequestedValue())) / 2, ((2 * Game.aliveEnemies.get(i).getYPos() + Game.aliveEnemies.get(i).getHeight())) / 2 - ((int) (Game.aliveEnemies.get(i).getHeight())), 20f * windowWidth / 1920, false, false, false, null, Game.aliveEnemies.get(i).getColor(), enemyGraphics2D);
                }
                break;
            }
            case GAME_OVER_SCREEN: {
                //text
                renderText("Game Over!", 0, 200 * windowWidth / 1920, true, false, 1, 1, gameOverScreenTextPanels.get(gameOverScreenTextPanelNames.indexOf("gameOverTextBounds")), getComputerModernFontOfSpecifiedSize(96), Color.RED, englishTextGraphics2D);
                renderLaTeX("S = ", (800 + LaTeXTextFontMetrics.stringWidth("S = ")) * windowWidth / 1920, 550 * windowWidth/1920, (int) (32 * windowWidth/1920), false, false, false, gameOverScreenLaTeXTextPanels.get(gameOverScreenLaTeXTextPanelNames.indexOf("gameOverScoreTextBounds")), Color.BLACK, LaTeXGraphics2D);
                renderLaTeX(String.valueOf(Game.score), (1120 - LaTeXTextFontMetrics.stringWidth(String.valueOf(Game.score))) * windowWidth / 1920, 550 * windowHeight/1080, 32, false, false, false, gameOverScreenLaTeXTextPanels.get(gameOverScreenLaTeXTextPanelNames.indexOf("gameOverScoreTextBounds")), Color.BLACK, LaTeXGraphics2D);
                renderText(String.valueOf(Game.enemiesKilled), (1120 - englishTextFontMetrics.stringWidth(String.valueOf(Game.enemiesKilled))) * windowWidth / 1920, 630 * windowHeight/1080, false, false, 1, 1, gameOverScreenTextPanels.get(gameOverScreenTextPanelNames.indexOf("gameOverTextBounds")), getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
                renderText(String.valueOf(Game.finalTimeSpentInGameInMilliseconds + "ms"), (1120 - englishTextFontMetrics.stringWidth(String.valueOf(Game.finalTimeSpentInGameInMilliseconds + "ms"))) * windowHeight/1080, 670 * windowWidth / 1920, false, false, 1, 1, gameOverScreenTextPanels.get(gameOverScreenTextPanelNames.indexOf("gameOverTextBounds")), getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
                //images
                renderImage(enemiesKilledIconImage, 800 * windowWidth / 1920, 594 * windowHeight/1080, null, false, false, 1, 1, null, imageGraphics2D);
                renderImage(timeSpentInGameInMillisecondsIconImage, 800 * windowWidth / 1920, 634 * windowHeight/1080, null, false, false, 1, 1, null, imageGraphics2D);
                //imagebuttons
                for (int i = 0; i < gameOverScreenImageButtonImages.size(); i++) {
                    renderImage(gameOverScreenImageButtonImages.get(i), gameOverScreenImageButtonPanels.get(i).getX(), gameOverScreenImageButtonPanels.get(i).getY(), null, false, false, 1, 1, gameOverScreenImageButtonPanels.get(i), imageButtonGraphics2D);
                }
                break;
            }
            case SETTINGS_SCREEN: {

                renderImage(mainMenuScreenImageImages.get(0), mainMenuScreenImagePanels.get(0).getX(), mainMenuScreenImagePanels.get(0).getY(), null, false, false, 1, 1, mainMenuScreenImagePanels.get(0), imageGraphics2D);

                switch (currentSubscreenShown){
                    case SETTINGS_VIDEO_SCREEN: {
                        renderVideoSettingsScreenText();
                        renderText("Enemy Color", (int) (800 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                        for (int i = 4; i < 6; i++){
                            renderImage(settingsScreenImageButtonImages.get(i), settingsScreenImageButtonPanels.get(i).getX(), settingsScreenImageButtonPanels.get(i).getY(), null, false, false, 1, 1, settingsScreenImageButtonPanels.get(i), imageButtonGraphics2D);
                        }
                        break;
                    }
                    case SETTINGS_AUDIO_SCREEN: {
                        renderAudioSettingsScreenText();
                        renderText("Sound", (int) (800 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                        for (int i = 6; i < 8; i++){
                            renderImage(settingsScreenImageButtonImages.get(i), settingsScreenImageButtonPanels.get(i).getX(), settingsScreenImageButtonPanels.get(i).getY(), null, false, false, 1, 1, settingsScreenImageButtonPanels.get(i), imageButtonGraphics2D);
                        }
                        break;
                    }
                    case SETTINGS_ONLINE_SCREEN: {
                        renderText("Username: ", (int) (800 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                        renderText(Game.usernameToLoginWith, (int) (1070 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                        renderText("Login Code: ", (int) (800 * windowWidth / 1920), (int) (280 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                        renderText(Game.loginCodeToLoginWith, (int) (1070 * windowWidth / 1920), (int) (280 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);

                        break;
                    }
                }


                for (int i = 0; i < 4; i++) {
                    renderImage(settingsScreenImageButtonImages.get(i), settingsScreenImageButtonPanels.get(i).getX() + settingsScreenButtonsXOffset[i], settingsScreenImageButtonPanels.get(i).getY(), null, false, false, 1, 1, settingsScreenImageButtonPanels.get(i), imageButtonGraphics2D);
                }
                break;
            }
            case CREDITS_SCREEN: {
                renderImage(mainMenuScreenImageImages.get(0), mainMenuScreenImagePanels.get(0).getX(), mainMenuScreenImagePanels.get(0).getY(), null, false, false, 1, 1, mainMenuScreenImagePanels.get(0), imageGraphics2D);
                //imagebuttons
                for (int i = 0; i < creditsScreenImageButtonPanels.size(); i++) {
                    renderImage(creditsScreenImageButtonImages.get(i), creditsScreenImageButtonPanels.get(i).getX() + creditsScreenBackButtonXOffset, creditsScreenImageButtonPanels.get(i).getY(), null, false, false, 1, 1, creditsScreenImageButtonPanels.get(i), imageButtonGraphics2D);
                }
                for (int i = 0; i < creditsScreenImagePanels.size(); i++){
                    renderImage(creditsScreenImageImages.get(i), creditsScreenImagePanels.get(i).getX(), creditsScreenImagePanels.get(i).getY(), null, false, false, 1, 1, creditsScreenImagePanels.get(i), imageGraphics2D);
                }
            }
        }

        //render global stuff

        //stats counter
        if (showStatsPanel) {
            statsPanelGraphics2D.fillRect((int) 1600 * windowWidth / 1920, (int) 100 * windowHeight / 1080, (int) 320 * windowWidth / 1920, (int) 100 * windowHeight / 1080);
            renderText("FPS: " + Loop.framesPerSecondToDisplay, (int) 1605 * windowWidth / 1920, (int) 120 * windowHeight / 1080, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(20), Color.WHITE, englishTextGraphics2D);
            renderText("UPS: " + Loop.updatesPerSecondToDisplay, (int) 1605 * windowWidth / 1920, (int) 145 * windowHeight / 1080, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(20), Color.WHITE, englishTextGraphics2D);
            renderText("Memory Usage: " + Loop.usedMemoryInMegabytes + "MB/" + Loop.totalMemoryInMegabytes + "MB", (int) 1605 * windowWidth / 1920, (int) 170 * windowHeight / 1080, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(20), Color.WHITE, englishTextGraphics2D);
        }

        //notification text
        for (int i = 0; i < aliveNotificationTexts.size(); i++) {
            aliveNotificationTexts.get(i).setAge(aliveNotificationTexts.get(i).getAge() + 1);
            if (aliveNotificationTexts.get(i).getAge() <= 20) {
                renderText(aliveNotificationTexts.get(i).getText(), 200 * windowWidth / 1920, (int) ((aliveNotificationTextPanelOffsets.get(i)) + aliveNotificationTexts.get(i).getAge() * 2) * windowHeight / 1080, true, false, 1, 1, aliveNotificationTextPanels.get(i), getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
            } else if (aliveNotificationTexts.get(i).getAge() <= 200) {
                renderText(aliveNotificationTexts.get(i).getText(), 200 * windowWidth / 1920, (int) (aliveNotificationTextPanelOffsets.get(i) + 40) * windowHeight / 1080, true, false, 1, 1, aliveNotificationTextPanels.get(i), getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
            } else {
                aliveNotificationTextPanelOffsets.remove(i);
                aliveNotificationTexts.remove(i);
            }
        }


        //fade effect
        AlphaComposite fadeEffectAlphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, Fade.fadeEffectCurrentOpacity);
        effectGraphics2D.setComposite(fadeEffectAlphaComposite);
        effectGraphics2D.setColor(Color.BLACK);
        effectGraphics2D.fillRect(0, 0, windowWidth, windowHeight);

        getMousePositionAndDoActions();
        disposeAllGraphics();
        showBufferStrategy();
    }


    public void getMousePositionAndDoActions() {

        if (MouseInfo.getPointerInfo().getLocation() != null) {
            Point cursorLocation = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(cursorLocation, displayWindow);
            int cursorXPos = (int) cursorLocation.getX();
            int cursorYPos = (int) cursorLocation.getY();

            //getting mouse position and doing stuff (mainly hovering buttons on main menu)
            switch (currentScreenShown) {
                case TITLE_SCREEN: {
                    break;
                }
                case MAIN_MENU_SCREEN: {
                    cursorIsHoveringOnSingleplayerButton = false;
                    cursorIsHoveringOnMultiplayerButton = false;
                    cursorIsHoveringOnShopButton = false;
                    cursorIsHoveringOnOptionsButton = false;
                    cursorIsHoveringOnCreditsButton = false;
                    cursorIsHoveringOnQuitButton = false;
                    for (int i = 0; i < mainMenuScreenImageButtonPanels.size(); i++) {
                        if (hoverDetectionActive) {
                            if (checkIfPointIsInBounds(mainMenuScreenImageButtonPanels.get(i), cursorXPos, cursorYPos)) {
                                switch (mainMenuScreenImageButtonPanelNames.get(i)) {
                                    case "singleplayerButtonBounds": {
                                        cursorIsHoveringOnSingleplayerButton = true;
                                        break;
                                    }
                                    case "multiplayerButtonBounds": {
                                        cursorIsHoveringOnMultiplayerButton = true;
                                        break;
                                    }
                                    case "shopButtonBounds": {
                                        cursorIsHoveringOnShopButton = true;
                                        break;
                                    }
                                    case "settingsButtonBounds": {
                                        cursorIsHoveringOnOptionsButton = true;
                                        break;
                                    }
                                    case "creditsButtonBounds": {
                                        cursorIsHoveringOnCreditsButton = true;
                                        break;
                                    }
                                    case "quitButtonBounds": {
                                        cursorIsHoveringOnQuitButton = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case SINGLEPLAYER_SCREEN: {
                    break;
                }
                case SETTINGS_SCREEN: {
                    cursorIsHoveringOnSettingsVideoSectionButton = false;
                    cursorIsHoveringOnSettingsAudioSectionButton = false;
                    cursorIsHoveringOnSettingsOnlineSectionButton = false;
                    cursorIsHoveringOnSettingsBackButton = false;
                    for (int i = 0; i < settingsScreenImageButtonPanels.size(); i++) {
                        if (hoverDetectionActive) {
                            if (checkIfPointIsInBounds(settingsScreenImageButtonPanels.get(i), cursorXPos, cursorYPos)) {
                                switch (settingsScreenImageButtonPanelNames.get(i)) {
                                    case "settingsVideoSectionButtonBounds": {
                                        cursorIsHoveringOnSettingsVideoSectionButton = true;
                                        break;
                                    }
                                    case "settingsAudioSectionButtonBounds": {
                                        cursorIsHoveringOnSettingsAudioSectionButton = true;
                                        break;
                                    }
                                    case "settingsOnlineSectionButtonBounds": {
                                        cursorIsHoveringOnSettingsOnlineSectionButton = true;
                                        break;
                                    }
                                    case "settingsBackButtonBounds": {
                                        cursorIsHoveringOnSettingsBackButton = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    break;
                }
                case CREDITS_SCREEN: {
                    cursorIsHoveringOnCreditsBackButton = false;
                    for (int i = 0; i < creditsScreenImageButtonPanels.size(); i++) {
                        if (hoverDetectionActive) {
                            if (checkIfPointIsInBounds(creditsScreenImageButtonPanels.get(i), cursorXPos, cursorYPos)) {
                                switch (creditsScreenImageButtonPanelNames.get(i)) {
                                    case "creditsBackButtonBounds": {
                                        cursorIsHoveringOnCreditsBackButton = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public void disposeAllGraphics() {
        //dispose graphics2D stuff
        canvasGraphics2D.dispose();
        tooltipGraphics2D.dispose();
        englishTextGraphics2D.dispose();
        statsPanelGraphics2D.dispose();
        imageButtonGraphics2D.dispose();
        imageGraphics2D.dispose();
        effectGraphics2D.dispose();

        //singleplayer screen
        singleplayerTileGraphics2D.dispose();
        singleplayerTileTermGraphics2D.dispose();
        enemyGraphics2D.dispose();
        bloodGraphics2D.dispose();



    }

    public void showBufferStrategy(){
        bufferStrategy.show();
    }




    /*
     *
     * Display class Table of Contents of Methods
     *
     *
     * Section 0: Initializing Stuff
     *
     *
     * Section 1: Rendering Text
     *     Section 1a: Actually Rendering
     *     Section 1b: Getting optimal coordinates for rendering
     *     Section 1c: Getting booleans for rendering
     *     Section 1d: Getting font size according to window size
     *
     *     Section 1x: Rendering specific text
     *
     *
     * Section 2: Rendering Image/ImageButtons
     *     Section 2a: Rendering Images/ImageButtons
     *     Section 2b: ImageResizers
     *
     *
     * Section 3: Rendering Shapes
     *     Section 3a: Rendering Shapes
     *
     * Section 4: Animations
     *     Section 4a: Problem Box
     *
     * Section 5: Other Methods
     *
     *
     */

    /**
     * Initializes the user interface components. (Called at launch)
     * @param bufferStrategy ?????
     */
    private void initializeUserInterfaceComponents(BufferStrategy bufferStrategy) {

        Loop.gameAllowedToRender = false;



        if (fullscreen){
            //fullscreen == true
            GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            windowWidth = graphicsDevice.getDisplayMode().getWidth();
            windowHeight = graphicsDevice.getDisplayMode().getHeight();
        } else {
            //fullscreen == false
            windowWidth = realWindowWidth;
            windowHeight = realWindowHeight;
        }

        Graphics2D temporaryEnglishTextGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        String fileNameEnding = "-en";

        try {

            //images that AREN'T affected by selected language
            schoolLogoImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/school_logo.png"))));
            nationalSoftwareContestLogoImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/national_software_contest_logo.png"))));
            baseImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/base.png"))));
            settingsLeftArrowButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/settings_left_arrow_button.png"))));
            settingsRightArrowButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/settings_right_arrow_button.png"))));
            settingsSelectLoginCodeButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/settings_select_login_code_button.png"))));
            settingsSelectUsernameButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/settings_select_username_button.png"))));

            //images that ARE affected by selected language
            switch (currentLanguage) {
                case ENGLISH: {
                    fileNameEnding = "-en";
                    break;
                }
                case THAI: {
                    //this does nothing for now
                }
                default: {
                    //this also does nothing for now
                }
            }

            //title screen
            mathematicalBaseDefenseTitleLogoImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/title_screen_logo.png"))));

            //main menu screen
            mathematicalBaseDefenseLogoImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/mathematical_base_defense_logo.png"))));

            singleplayerButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/singleplayer_button" + fileNameEnding + ".png"))));
            multiplayerButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/multiplayer_button" + fileNameEnding + ".png"))));
            shopButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/shop_button" + fileNameEnding + ".png"))));
            settingsButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/settings_button" + fileNameEnding + ".png"))));
            quitButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/quit_button" + fileNameEnding + ".png"))));
            creditsButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/credits_button" + fileNameEnding + ".png"))));


            /*
                Gameplay screens
             */
            //singleplayer screen
            sendButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/send_button" + fileNameEnding + ".png"))));
            enemiesKilledIconImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/enemies_killed_icon.png"))));
            baseHealthIconImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/base_health_icon.png"))));
            timeSpentInGameInMillisecondsIconImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/time_spent_in_game_in_milliseconds_icon.png"))));


            //game over screen
            retryButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/retry_button" + fileNameEnding + ".png"))));
            returnToTitleScreenButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/return_to_title_screen_button" + fileNameEnding + ".png"))));



            /*
            Other screens
             */

            //settings screen
            settingsVideoSectionButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/settings_video_section_button" + fileNameEnding + ".png"))));
            settingsAudioSectionButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/settings_audio_section_button" + fileNameEnding + ".png"))));
            settingsOnlineSectionButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/settings_online_section_button" + fileNameEnding + ".png"))));
            settingsBackButtonImage  = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/settings_back_button" + fileNameEnding + ".png"))));



            //credits screen
            creditsBackButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/credits_back_button" + fileNameEnding + ".png"))));
            creditsInformationImage = ImageIO.read(new BufferedInputStream(new FileInputStream(new File(System.getenv("LOCALAPPDATA") + "/MathematicalBaseDefense/game/assets/images/credits_information" + fileNameEnding + ".png"))));

        } catch (Exception exception) {
            displayWindow.dispose();
            StringWriter errors = new StringWriter();
            exception.printStackTrace(new PrintWriter(errors));
            new ErrorWindow(errors.toString());
        }


        //title screen
        Image mathematicalBaseDefenseTitleLogo = new Image(mathematicalBaseDefenseTitleLogoImage, 0, 0, (int) (mathematicalBaseDefenseTitleLogoImage.getWidth() * windowWidth / 1920), (int) (mathematicalBaseDefenseTitleLogoImage.getHeight() * windowHeight / 1080), "mathematicalBaseDefenseTitleLogo", "mathematicalBaseDefenseTitleLogoBounds", Screen.TITLE_SCREEN, bufferStrategy);



        //mainmenu screen: text
        temporaryEnglishTextGraphics2D.setFont(getComputerModernFontOfSpecifiedSize(96));
        Text mainMenuText = new Text("Mathematical Base Defense", 0, (int) (windowWidth * 0.05), temporaryEnglishTextGraphics2D.getFontMetrics().stringWidth("Mathematical Base Defense"), (int) (96 * windowWidth / 1920), 0, 0, windowWidth, (int) (windowHeight * 0.15), getComputerModernFontOfSpecifiedSize(96), (int) (96 * windowWidth / 1920), "mainMenuScreenText", "mainMenuScreenTextBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        //main menu screen: imageButtons
        ImageButton singleplayerButton = new ImageButton(singleplayerButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 460 / 1080), (singleplayerButtonImage.getWidth() * windowWidth / 1920), (singleplayerButtonImage.getHeight() * windowHeight / 1080),  "singleplayerButton", "singleplayerButtonBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton multiplayerButton = new ImageButton(multiplayerButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 540 / 1080), (multiplayerButtonImage.getWidth() * windowWidth / 1920), (multiplayerButtonImage.getHeight() * windowHeight / 1080),  "multiplayerButton", "multiplayerButtonBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton shopButton = new ImageButton(shopButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 620 / 1080), (shopButtonImage.getWidth() * windowWidth / 1920), (shopButtonImage.getHeight() * windowHeight / 1080),  "shopButton", "shopButtonBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton settingsButton = new ImageButton(settingsButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 700 / 1080), (settingsButtonImage.getWidth() * windowWidth / 1920), (settingsButtonImage.getHeight() * windowHeight / 1080),  "settingsButton", "settingsButtonBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton creditsButton = new ImageButton(creditsButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 780 / 1080), (creditsButtonImage.getWidth() * windowWidth / 1920), (creditsButtonImage.getHeight() * windowHeight / 1080),  "creditsButton", "creditsButtonBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton quitButton = new ImageButton(quitButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 860 / 1080), (quitButtonImage.getWidth() * windowWidth / 1920), (quitButtonImage.getHeight() * windowHeight / 1080),  "quitButton", "quitButtonBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton schoolLogo = new ImageButton(schoolLogoImage, (int) (windowWidth * 0.005), (int) (windowHeight * 0.9), (schoolLogoImage.getWidth() * windowWidth / 1920), (schoolLogoImage.getHeight() * windowHeight / 1080),  "schoolLogo", "schoolLogoBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton nationalSoftwareContestLogo = new ImageButton(nationalSoftwareContestLogoImage, (int) (windowWidth * 0.04), (int) (windowHeight * 0.9), (nationalSoftwareContestLogoImage.getWidth() * windowWidth / 1920), (nationalSoftwareContestLogoImage.getHeight() * windowHeight / 1080),  "nationalSoftwareContestLogo", "nationalSoftwareContestLogoBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        //main menu screen: images
        Image mathematicalBaseDefenseLogo = new Image(mathematicalBaseDefenseLogoImage, (int) 60*windowWidth/1920, 60*windowHeight/1080, (int) (mathematicalBaseDefenseLogoImage.getWidth() * windowWidth/1920), (int) (mathematicalBaseDefenseLogoImage.getHeight()) * windowHeight/1920, "mathematicalBaseDefenseLogo", "mathematicalBaseDefenseLogoBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);



        /*
            Gameplay
         */
        //singleplayer: imagebuttons
        ImageButton sendButton = new ImageButton( sendButtonImage,(int) windowWidth * 750 / 1920, (int) windowHeight * 920 / 1080, sendButtonImage.getWidth() * windowWidth / 1920, sendButtonImage.getHeight() * windowHeight / 1080, "sendButton", "sendButtonBounds", Display.Screen.SINGLEPLAYER_SCREEN, bufferStrategy);
        //singleplayer: images
        Image base = new Image(baseImage,(int) 100 * windowWidth / 1920, (int) 80 * windowHeight / 1080, (int) baseImage.getWidth() * windowWidth / 1920, (int) baseImage.getHeight() * windowHeight / 1080,  "base", "baseBounds", Display.Screen.SINGLEPLAYER_SCREEN, bufferStrategy);

        Image enemiesKilledIcon = new Image(enemiesKilledIconImage, (int) 1224 * windowWidth/1920, (int) 512*windowWidth/1920, (int) enemiesKilledIconImage.getWidth() * windowWidth / 1920, (int) enemiesKilledIconImage.getWidth() * windowWidth / 1920, "enemiesKilledIcon", "enemiesKilledIconBounds", Screen.SINGLEPLAYER_SCREEN, bufferStrategy);
        Image baseHealthIcon = new Image(baseHealthIconImage, (int) 1224 * windowWidth/1920, (int) 552*windowWidth/1920, (int) baseHealthIconImage.getWidth() * windowWidth / 1920, (int) baseHealthIconImage.getWidth() * windowWidth / 1920, "baseHealthIcon", "baseHealthIconBounds", Screen.SINGLEPLAYER_SCREEN, bufferStrategy);
        Image timeSpentInGameInMillisecondsIcon = new Image(timeSpentInGameInMillisecondsIconImage, (int) 1224 * windowWidth/1920, (int) 592*windowWidth/1920, (int) timeSpentInGameInMillisecondsIconImage.getWidth() * windowWidth / 1920, (int) timeSpentInGameInMillisecondsIconImage.getWidth() * windowWidth / 1920, "timeSpentInGameInMillisecondsIcon", "timeSpentInGameInMillisecondsIconBounds", Screen.SINGLEPLAYER_SCREEN, bufferStrategy);

        //singleplayer screen: tiles
        String tileLetters = "ABCDEFG";
        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 7; c++) {
                EmptyPanel tilePanel = new EmptyPanel((int) (750 + (60 * r)) * windowWidth / 1920, (int) ((460 + (60 * c))) * windowHeight / 1080, (int) (60 * windowWidth / 1920), (int) (60 * windowHeight / 1080), "tile" + tileLetters.charAt(c) + (r + 1), "tile" + tileLetters.charAt(c) + (r + 1) + "Bounds", Screen.SINGLEPLAYER_SCREEN);
            }
        }
        //singleplayer screen: problem box
        EmptyPanel problemPanel = new EmptyPanel((int) (90 * windowWidth / 1920), (int) (410 * windowHeight / 1080), (int) (1740 * windowWidth / 1920), (int) (80 * windowHeight / 1920), "problemPanel", "problemPanelBounds", Screen.SINGLEPLAYER_SCREEN);
        //singleplayer screen: side panels


        //gameover screen: text
        Text gameOverText = new Text("Game Over!", (int) (windowWidth * 0.5), 0, temporaryEnglishTextGraphics2D.getFontMetrics().stringWidth("Game Over!"), (int) (96 * windowWidth / 1920), 0, 0, windowWidth, windowHeight, getComputerModernFontOfSpecifiedSize(96), 96, "gameOverText", "gameOverTextBounds", Screen.GAME_OVER_SCREEN, bufferStrategy);
        LaTeXText gameOverScoreText = new LaTeXText("Game Over!", 0, 800 * windowWidth / 1920, 0, 0, windowWidth, windowHeight, getComputerModernFontOfSpecifiedSize(32), 32, "gameOverScoreText", "gameOverScoreTextBounds", Screen.GAME_OVER_SCREEN, bufferStrategy);
        //game over screen: imagebuttons
        ImageButton retryButton = new ImageButton(retryButtonImage, (int) windowWidth*520/1920, (int) windowHeight*800/1080, retryButtonImage.getWidth() * windowWidth/1920, retryButtonImage.getHeight()  * windowWidth/1080, "retryButton", "retryButtonBounds", Screen.GAME_OVER_SCREEN, bufferStrategy);
        ImageButton returnToTitleScreenButton = new ImageButton(returnToTitleScreenButtonImage, (int) windowWidth*1000/1920, (int) windowHeight*800/1080, retryButtonImage.getWidth() * windowWidth/1920, retryButtonImage.getHeight()  * windowWidth/1080, "returnToTitleScreenButton", "returnToTitleScreenButtonBounds", Screen.GAME_OVER_SCREEN, bufferStrategy);


        JPanel scoreIconBounds = new JPanel();
        scoreIconBounds.setBounds(1224*windowWidth/1920, 472*windowHeight/1080, 32*windowWidth/1920, 32*windowHeight/1080);

        /*
           Other Screens
         */

        //settings screen
        ImageButton settingsVideoSectionButton = new ImageButton(settingsVideoSectionButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 460 / 1080), (settingsVideoSectionButtonImage.getWidth() * windowWidth / 1920), (settingsVideoSectionButtonImage.getHeight() * windowHeight / 1080),  "settingsVideoSectionButton", "settingsVideoSectionButtonBounds", Display.Screen.SETTINGS_SCREEN, bufferStrategy);
        ImageButton settingsAudioSectionButton = new ImageButton(settingsAudioSectionButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 540 / 1080), (settingsAudioSectionButtonImage.getWidth() * windowWidth / 1920), (settingsAudioSectionButtonImage.getHeight() * windowHeight / 1080),  "settingsAudioSectionButton", "settingsAudioSectionButtonBounds", Display.Screen.SETTINGS_SCREEN, bufferStrategy);
        ImageButton settingsOnlineSectionButton = new ImageButton(settingsOnlineSectionButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 620 / 1080), (settingsOnlineSectionButtonImage.getWidth() * windowWidth / 1920), (settingsOnlineSectionButtonImage.getHeight() * windowHeight / 1080),  "settingsOnlineSectionButton", "settingsOnlineSectionButtonBounds", Display.Screen.SETTINGS_SCREEN, bufferStrategy);
        ImageButton settingsBackButton = new ImageButton(settingsBackButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 860 / 1080), (settingsBackButtonImage.getWidth() * windowWidth / 1920), (settingsBackButtonImage.getHeight() * windowHeight / 1080),  "settingsBackButton", "settingsBackButtonBounds", Display.Screen.SETTINGS_SCREEN, bufferStrategy);
        
        //enemy color selection
        ImageButton settingsLeftArrowForEnemyColorSelectionButton = new ImageButton(settingsLeftArrowButtonImage, (int) windowWidth * 1300 / 1920, (int) windowHeight * 200 / 1920, (settingsLeftArrowButtonImage.getWidth() * windowWidth / 1920), (settingsLeftArrowButtonImage.getHeight() * windowHeight / 1080), "settingsLeftArrowForEnemyColorSelectionButton", "settingsLeftArrowForEnemyColorSelectionButtonBounds", Display.Screen.SETTINGS_SCREEN, bufferStrategy);
        ImageButton settingsRightArrowForEnemyColorSelectionButton = new ImageButton(settingsRightArrowButtonImage, (int) windowWidth * 1700 / 1920, (int) windowHeight * 200 / 1920, (settingsRightArrowButtonImage.getWidth() * windowWidth / 1920), (settingsRightArrowButtonImage.getHeight() * windowHeight / 1080), "settingsRightArrowForEnemyColorSelectionButton", "settingsRightArrowForEnemyColorSelectionButtonBounds", Display.Screen.SETTINGS_SCREEN, bufferStrategy);

        //sound on/off
        ImageButton settingsLeftArrowForSoundVolumeSelectionButton = new ImageButton(settingsLeftArrowButtonImage, (int) windowWidth * 1300 / 1920, (int) windowHeight * 200 / 1920, (settingsLeftArrowButtonImage.getWidth() * windowWidth / 1920), (settingsLeftArrowButtonImage.getHeight() * windowHeight / 1080), "settingsLeftArrowForSoundVolumeSelectionButton", "settingsLeftArrowForSoundVolumeSelectionButtonBounds", Display.Screen.SETTINGS_SCREEN, bufferStrategy);
        ImageButton settingsRightArrowForSoundVolumeSelectionButton = new ImageButton(settingsRightArrowButtonImage, (int) windowWidth * 1700 / 1920, (int) windowHeight * 200 / 1920, (settingsRightArrowButtonImage.getWidth() * windowWidth / 1920), (settingsRightArrowButtonImage.getHeight() * windowHeight / 1080), "settingsRightArrowForSoundVolumeSelectionButton", "settingsRightArrowForSoundVolumeSelectionButtonBounds", Display.Screen.SETTINGS_SCREEN, bufferStrategy);

        //online screen
        ImageButton settingsSelectLoginCodeButton = new ImageButton(settingsSelectLoginCodeButtonImage, (int) windowWidth * 1040 / 1920, (int) windowHeight * 280 / 1920, (settingsSelectLoginCodeButtonImage.getWidth() * windowWidth / 1920), (settingsSelectLoginCodeButtonImage.getHeight() * windowHeight / 1080), "ssettingsSelectLoginCodeButton", "settingsSelectLoginCodeButtonBounds", Display.Screen.SETTINGS_SCREEN, bufferStrategy);
        ImageButton settingsSelectUsernameButton = new ImageButton(settingsSelectUsernameButtonImage, (int) windowWidth * 1040 / 1920, (int) windowHeight * 160 / 1920, (settingsSelectUsernameButtonImage.getWidth() * windowWidth / 1920), (settingsSelectUsernameButtonImage.getHeight() * windowHeight / 1080), "ssettingsSelectUsernameButton", "settingsSelectUsernameButtonBounds", Display.Screen.SETTINGS_SCREEN, bufferStrategy);

        //credits screen
        ImageButton creditsBackButton = new ImageButton(creditsBackButtonImage, (int) (windowWidth * 60 / 1920), (int) (windowHeight * 860 / 1080), (creditsButtonImage.getWidth() * windowWidth / 1920), (creditsButtonImage.getHeight() * windowHeight / 1080),  "creditsBackButton", "creditsBackButtonBounds", Display.Screen.CREDITS_SCREEN, bufferStrategy);
        Image creditsInformation = new Image(creditsInformationImage, (int) (windowWidth * 720 / 1920), (int) (windowHeight * 60 / 1080), (creditsInformationImage.getWidth() * windowWidth / 1920), (creditsInformationImage.getHeight() * windowHeight / 1080),  "creditsInformationButton", "creditsInformationBounds", Display.Screen.CREDITS_SCREEN, bufferStrategy);

        Loop.gameAllowedToRender = true;


    }




    //Section 1 (Render methods) is here

    //Section 1a
    /**
     * Renders text.
     * @param text                The text to render.
     * @param xPos                The x-coordinate (or the left) of the text to render.
     * @param yPos                The y-coordinate (or the top) of the text to render.
     * @param centerHorizontally  Whether to render horizontally centered text (according to the bounds).
     * @param centerVertically    Whether to render vertically centered text (according to the bounds).
     * @param scaleX              The multiplier to multiply the length of the text by.
     * @param scaleY              The multiplier to multiply the height of the text by.
     * @param bounds              The bounds for the text to abide by.
     * @param font                The font to use in rendering.
     * @param graphics2DInstance  The Graphics2D instance to use in rendering.
     */
    public void renderText(String text, int xPos, int yPos, boolean centerHorizontally, boolean centerVertically, double scaleX, double scaleY, JPanel bounds, Font font, Color color, Graphics2D graphics2DInstance) {
        graphics2DInstance.setFont(font);
        graphics2DInstance.setColor(color);
        if (!centerHorizontally && !centerVertically) {
            graphics2DInstance.drawString(text, xPos, yPos);
        } else if (centerHorizontally && !centerVertically) {
            graphics2DInstance.drawString(text, getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(bounds, text, font, scaleX, scaleY, 'x'), yPos);
        } else if (!centerHorizontally && centerVertically) {
            graphics2DInstance.drawString(text, xPos, getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(bounds, text, font, scaleX, scaleY, 'y'));
        } else if (centerHorizontally && centerVertically) {
            graphics2DInstance.drawString(text, getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(bounds, text, font, scaleX, scaleY, 'x'), getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(bounds, text, font, scaleX, scaleY, 'y'));
        }
    }

    /**
     * Renders LaTeX.
     * @param text                The text to render.
     * @param size                The size (in pixels) of the LaTeX text.
     * @param xPos                The x-coordinate (or the left) of the text to render.
     * @param yPos                The y-coordinate (or the top) of the text to render.
     * @param centerHorizontally  Whether to render horizontally centered text (according to the bounds).
     * @param centerVertically    Whether to render vertically centered text (according to the bounds).
     * @param forceAutoAdjust     Whether to force adjust LaTeX text.
     * @param bounds              The multiplier to multiply the height of the text by.
     * @param color               The color (RGB) to use in rendering.
     * @param graphics2DInstance  The Graphics2D instance to use in rendering.
     */
    //Render LaTeX
    public void renderLaTeX(String text, int xPos, int yPos, float size, boolean centerHorizontally, boolean centerVertically, boolean forceAutoAdjust, JPanel bounds, Color color, Graphics2D graphics2DInstance){
        TeXFormula formula = new TeXFormula(text);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_TEXT, size);
        icon.setForeground(color);

        if (centerHorizontally) { xPos = getOptimalCoordinatesToDrawCenteredLaTeXInSpecifiedBounds(bounds, icon, 1, 1, 'x'); }
        if (centerVertically) { yPos = getOptimalCoordinatesToDrawCenteredLaTeXInSpecifiedBounds(bounds, icon, 1, 1, 'y'); }

        //these two are here because of latex rendering issues which causes the signs to not be centered

        if (forceAutoAdjust || text.contains("\\equals")){ xPos -= (int) (7*windowWidth/1920); yPos -= (int) (12*windowHeight/1080); }
        if ((forceAutoAdjust || text.contains("\\plus") || text.contains("\\minus") || text.contains("\\times") || text.contains("\\div")) && !text.contains("\\equals")){ xPos -= 7; }

        yPos = (int) (yPos - icon.getIconHeight() + (icon.getIconHeight() - icon.getTrueIconHeight()));
        icon.paintIcon(null, graphics2DInstance, (int) xPos, (int) yPos+((int) size));
    }

    /**
     * Renders LaTeX.
     * @param text                The text to render.
     * @param size                The size (in pixels) of the LaTeX text.
     * @param xPos                The x-coordinate (or the left) of the text to render.
     * @param yPos                The y-coordinate (or the top) of the text to render.
     * @param centerHorizontally  Whether to render horizontally centered text (according to the bounds).
     * @param centerVertically    Whether to render vertically centered text (according to the bounds).
     * @param bounds              The multiplier to multiply the height of the text by.
     * @param color               The color (RGB) to use in rendering.
     * @param graphics2DInstance  The Graphics2D instance to use in rendering.
     */
    public void renderLaTeXInProblemBox(String text, int xPos, int yPos, float size, boolean centerHorizontally, boolean centerVertically, JPanel bounds, Color color, Graphics2D graphics2DInstance) {
        TeXFormula formula = new TeXFormula(text);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_TEXT, size);
        icon.setForeground(color);

        if (centerHorizontally) {
            xPos = getOptimalCoordinatesToDrawCenteredLaTeXInSpecifiedBounds(bounds, icon, 1, 1, 'x');
        }
        if (centerVertically) {
            yPos = getOptimalCoordinatesToDrawCenteredLaTeXInSpecifiedBounds(bounds, icon, 1, 1, 'y');
        }

        //these two are here because of latex rendering issues which causes the signs to not be centered

        yPos = (int) ((yPos - icon.getIconHeight() + (icon.getIconHeight() - icon.getTrueIconHeight())));
        if (text.contains("\\equals")){ xPos -= (int) (7*windowWidth/1920); }
        if ((text.contains("\\plus") || text.contains("\\minus") || text.contains("\\times") || text.contains("\\div")) && !text.contains("\\equals")){ xPos -= (int) (7*windowWidth/1920); }
        icon.paintIcon(null, graphics2DInstance, (int) xPos, (int) yPos + (48 * windowWidth / 1920));
    }

    //Section 1b
    /**
     * Finds the optimal x/y coordinate to draw centered text given the bounds, the text.
     * @param bounds                The bounds
     * @param text                  The text
     * @param font                  The font
     * @param scaleX                djadijsadi
     * @param scaleY                ijsfaijofasijofjiossfa
     * @param coordinateToReturn    The coordinate (x or y) to return
     * @return                      The optimal x or y coordinate to render centered text using the bounds as the boundaries.
     * I mean isn't that obvious???
     *
     */
    private int getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(JPanel bounds, String text, Font font, double scaleX, double scaleY, char coordinateToReturn) {

        Graphics2D temporaryTextGraphics2D = (Graphics2D) canvas.getGraphics();
        temporaryTextGraphics2D.scale(scaleX, scaleY);
        temporaryTextGraphics2D.setFont(font);
        FontMetrics fontMetrics = temporaryTextGraphics2D.getFontMetrics();

        if (coordinateToReturn == 'x') {
            return (int) ((int) (bounds.getX() + (bounds.getWidth() - fontMetrics.stringWidth(text)) / 2));
        } else if (coordinateToReturn == 'y') {
            return (int) ((int) (bounds.getY() + (bounds.getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent()));
        } else {
            LogMessage.logMessage("Unknown coordinate to return: " + coordinateToReturn, LogMessage.MessageType.ERROR);
            throw new IllegalArgumentException("Unknown coordinate to return: " + coordinateToReturn);

        }
    }

    /**
     * Basically the same as getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds();
     * @param bounds                The bounds.
     * @param icon                  The text.
     * @param scaleX                asds
     * @param scaleY                dawd
     * @param coordinateToReturn    The coordinate (x or y) to return
     * @return                      The optimal x or y coordinate to render centered LaTeX using the bounds as the boundaries.
     */
    private int getOptimalCoordinatesToDrawCenteredLaTeXInSpecifiedBounds(JPanel bounds, Icon icon, double scaleX, double scaleY, char coordinateToReturn){
        Graphics2D temporaryLaTeXTextGraphics2D = (Graphics2D) canvas.getGraphics();
        temporaryLaTeXTextGraphics2D.scale(scaleX, scaleY);
        if (coordinateToReturn == 'x'){
            return (int) ((int) (bounds.getX() + (bounds.getWidth() - icon.getIconWidth()) / 2));
        } else if (coordinateToReturn == 'y') {
            return (int) ((int) (bounds.getY() + (bounds.getHeight() - icon.getIconWidth()) / 2));
        } else {
            LogMessage.logMessage("Unknown coordinate to return: " + coordinateToReturn, LogMessage.MessageType.ERROR);
            throw new IllegalArgumentException("Unknown coordinate to return: " + coordinateToReturn);
        }
    }



    //Section 1c
    /**
     * Checks if a point is in (or on) a rectangle (bounds) given the corners, and the coordinates of the point.
     * @param panel The panels (or bounds).
     * @param px    The x-coordinate of the point.
     * @param py    The y-coordinate of the point.
     * @return      "true" if the point is in or on the rectangle, "false" if otherwise.
     */
    private boolean checkIfPointIsInBounds(JPanel panel, int px, int py) {
        return px >= panel.getX() && px <= panel.getX() + panel.getWidth() && py >= panel.getY() && py <= panel.getY() + panel.getHeight();
    }


    //Section 1d

    /**
     * Creates a new CMU Serif font size and registers it.
     * @param size    The font size. The size parameter is the font size if the game is rendering at 1920x1080 resolution.
     * @return        The font with the specified font size.
     */
    public Font getComputerModernFontOfSpecifiedSize(float size) {
        graphicsEnvironment.registerFont(computerModernFont.deriveFont((float) (size * windowWidth / 1920)));
        return computerModernFont.deriveFont((float) (size * windowWidth / 1920));
    }

    //Section 1x

    /**
     * Renders selected settings text
     */
    public void renderVideoSettingsScreenText(){
        //Selected Enemy Colorf
        switch (selectedEnemyColor){
            case RANDOM: {
                renderText("Random", (int) (1450 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                break;
            }
            case RED: {
                renderText("Red", (int) (1500 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                break;
            }
            case GREEN: {
                renderText("Green", (int) (1470 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                break;
            }
            case BLUE: {
                renderText("Blue", (int) (1480 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                break;
            }
            case YELLOW: {
                renderText("Yellow", (int) (1450 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                break;
            }
            case ORANGE: {
                renderText("Orange", (int) (1450 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                break;
            }
            case BLACK: {
                renderText("Black", (int) (1470 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                break;
            }

        }
    }

    public void renderAudioSettingsScreenText(){
        switch (currentSoundLevel){
            case ON: {
                renderText("On", (int) (1500 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                break;
            }
            case OFF: {
                renderText("Off", (int) (1500 * windowWidth / 1920), (int) (160 * windowWidth / 1920), false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(48), Color.BLACK, englishTextGraphics2D);
                break;
            }
        }
    }


    //END OF SECTION 1


    //START OF SECTION 2

    //Section 2a

    /**
     * Renders an Image or an ImageButton. Huh? The devs are using drawImage? Ew.
     * This image rendering method is so much better, am I right?
     * @param image                 The image to render.
     * @param imageXPos             The x position of the image to render.
     * @param imageYPos             The y position of the image to render.
     * @param centerHorizontally    Whether to horizontally center the image in the bounds.
     * @param centerVertically      Whether to vertically center the image in the bounds.
     * @param imageObserver         wutz this mean?
     * @param scaleX                sda
     * @param scaleY                das
     * @param bounds                The bounds for the image.
     * @param graphics2DInstance    The Graphics2D instance.
     */
    private void renderImage(BufferedImage image, int imageXPos, int imageYPos, ImageObserver imageObserver, boolean centerHorizontally, boolean centerVertically,  double scaleX, double scaleY, JPanel bounds, Graphics2D graphics2DInstance) {
        try {
            graphics2DInstance.drawImage(Thumbnails.of(image).size((int) (image.getWidth() * windowWidth/1920 * scaleX), (int) (image.getHeight() * windowHeight/1080 * scaleY)).imageType(BufferedImage.TYPE_INT_ARGB).asBufferedImage(), imageXPos, imageYPos, imageObserver);
        } catch (Exception exception) {
            displayWindow.dispose();
        }
    }

    //Section 2b


    //Section 2c

    //END OF SECTION 2

    //START OF SECTION 3

    //Section 3a

    //Section 3b

    //END OF SECTION 3

    //START OF SECTION 4

    //Section 4a

    public static void gradualFillThenFadeInProblemBox(Color color){
        widthToFillColorOpacity = 0f;
        colorToGraduallyFillAndThenFadeInProblemBox = color;
        widthToFillColor = 1740 * windowWidth/1920;
            for (int i = 0; i < 101; i++){
                try {
                    Thread.sleep(1);
                    if (widthToFillColorOpacity <= 1f) {
                        widthToFillColorOpacity += 0.01f;
                    }
                } catch (InterruptedException interruptedException){
                    interruptedException.printStackTrace();
                }

            }
            for (int i = 0; i < 101; i++) {
                try {
                    Thread.sleep(1);
                    if (widthToFillColorOpacity >= 0.0f) {
                        widthToFillColorOpacity -= 0.01f;
                    }
                } catch (InterruptedException interruptedException){
                    interruptedException.printStackTrace();
                }

            }
        colorToGraduallyFillAndThenFadeInProblemBox = null;
        widthToFillColor = 0;
    }

    //END OF SECTION 4

    //START OF SECTION 5

    public void clearAllUserInterfaceComponentArrayLists(){
        aliveNotificationTexts.clear();
        aliveNotificationTextPanels.clear();
        aliveNotificationTextPanelNames.clear();
        //title text
        //title screen text
        mainMenuScreenTextPanels.clear();
        mainMenuScreenTextTexts.clear();
        mainMenuScreenTextFonts.clear();
        mainMenuScreenTextPanelNames.clear();
        mainMenuScreenTextMetrics.clear();
        //title screen image buttons
        mainMenuScreenImageButtonPanels.clear();
        mainMenuScreenImageButtonImages.clear();
        mainMenuScreenImageButtonPanelNames.clear();
        //title screen images
        mainMenuScreenImagePanels.clear();
        mainMenuScreenImageImages.clear();
        mainMenuScreenImagePanelNames.clear();
        //singleplayer screen
        //singleplayer screen images
        singleplayerScreenImagePanels.clear();
        singleplayerScreenImageImages.clear();
        singleplayerScreenImagePanelNames.clear();
        //singleplayer screen image buttons
        singleplayerScreenImageButtonPanels.clear();
        singleplayerScreenImageButtonImages.clear();
        singleplayerScreenImageButtonPanelNames.clear();
        //tiles
        singleplayerScreenEmptyPanelBounds.clear();
        singleplayerScreenEmptyPanelBoundNames.clear();
        //settings
        //settings screen image buttons
        settingsScreenImageButtonPanels.clear();
        settingsScreenImageButtonImages.clear();
        settingsScreenImageButtonPanelNames.clear();
        //game over screen
        //game over: text
        gameOverScreenTextPanels.clear();
        gameOverScreenTextTexts.clear();
        gameOverScreenTextFonts.clear();
        gameOverScreenTextPanelNames.clear();
        gameOverScreenTextMetrics.clear();
        //game over: image buttons
        gameOverScreenImageButtonImages.clear();
        gameOverScreenImageButtonPanels.clear();
        gameOverScreenImageButtonPanelNames.clear();
    }



    public void disposeDisplayWindow(){
        displayWindow.dispose();
    }

    /**
     * Transitions from the a set of buttons to an another set of buttons.
     * @param from  The screen to transition from.
     * @param to    The screen to transition to.
     */
    public void changeMainMenuScreenButtons(Display.Screen from, Display.Screen to) {
        hoverDetectionActive = false;
        switch (from) {
            case MAIN_MENU_SCREEN : {
                for (int j = 0; j < 200; j++) {
                    try {
                        Thread.sleep(2);
                        for (int k = 0; k < 6; k++) {
                            mainMenuScreenButtonsXOffset[k] = (int) (-(2 * (j+1)) * windowWidth / 1920);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                break;
            }
            case SETTINGS_SCREEN : {
                for (int j = 0; j < 200; j++) {
                    try {
                        Thread.sleep(2);
                        for (int k = 0; k < 4; k++) {
                            settingsScreenButtonsXOffset[k] = (int) (-(2 * (j+1)) * windowWidth / 1920);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                break;
            }
            case CREDITS_SCREEN : {
                for (int j = 0; j < 200; j++) {
                    try {
                        Thread.sleep(2);
                        creditsScreenBackButtonXOffset = (int) (-(2 * (j+1))) * windowWidth / 1920;
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                break;
            }
        }
        switch (to){
            case MAIN_MENU_SCREEN : {
                currentState = State.MAIN_MENU_SCREEN;
                currentScreenShown = Screen.MAIN_MENU_SCREEN;
                for (int j = 0; j < 200; j++) {
                    try {
                        Thread.sleep(2);
                        for (int k = 0; k < 6; k++) {
                            mainMenuScreenButtonsXOffset[k] = (int) (-400 + (2 * (j+1)) * windowWidth / 1920);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                break;
            }
            case SETTINGS_SCREEN : {
                currentState = State.SETTINGS_SCREEN;
                currentScreenShown = Screen.SETTINGS_SCREEN;
                for (int j = 0; j < 200; j++) {
                    try {
                        Thread.sleep(2);
                        for (int k = 0; k < 4; k++) {
                            settingsScreenButtonsXOffset[k] = (int) (-400 + (2 * (j+1))) * windowWidth / 1920;
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                break;
            }
            case CREDITS_SCREEN : {
                currentState = State.CREDITS_SCREEN;
                currentScreenShown = Screen.CREDITS_SCREEN;
                for (int j = 0; j < 200; j++) {
                    try {
                        Thread.sleep(2);
                        creditsScreenBackButtonXOffset = (int) (-400 + (2 * (j+1))) * windowWidth / 1920;
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                break;
            }
        }
        hoverDetectionActive = true;
    }


    /**
     * Plays a sound file from the path specified.
     * @param path The path.
     */

    public static void playSound(String path) {
        if (currentSoundLevel == SoundLevel.ON) {
            try {
                InputStream inputStream = new FileInputStream(path);
                AudioStream audioStream = new AudioStream(inputStream);
                AudioPlayer.player.start(audioStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}














































