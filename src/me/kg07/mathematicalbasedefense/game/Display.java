package me.kg07.mathematicalbasedefense.game;

/**
 * Display Class for Mathematical Base Defense
 * @author  mistertfy64
 * @version 0.0.1-alpha
 * @since   2020-11-15 or something i cant remember
 *
 */


import me.kg07.mathematicalbasedefense.game.core.*;
import me.kg07.mathematicalbasedefense.game.userinterface.*;


import me.kg07.mathematicalbasedefense.game.userinterface.Image;
import net.coobird.thumbnailator.Thumbnails;
import org.scilab.forge.jlatexmath.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

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
    BufferedImage singleplayerButtonImage;
    BufferedImage multiplayerButtonImage;
    BufferedImage settingsButtonImage;
    BufferedImage quitButtonImage;
    BufferedImage schoolLogoImage;
    BufferedImage nationalSoftwareContestLogoImage;
    BufferedImage creditsButtonImage;
    BufferedImage sendButtonImage;
    BufferedImage baseImage;
    BufferedImage retryButtonImage;
    BufferedImage returnToTitleScreenButtonImage;


    //global
    public static ArrayList<NotificationText> aliveNotificationTexts = new ArrayList<NotificationText>();
    public static ArrayList<JPanel> aliveNotificationTextPanels = new ArrayList<JPanel>();
    public static ArrayList<String> aliveNotificationTextPanelNames = new ArrayList<String>();





    //array list panels
    //title screen text
    public static ArrayList<JPanel> mainMenuTextPanels = new ArrayList<JPanel>();
    public static ArrayList<String> mainMenuTextTexts = new ArrayList<String>();
    public static ArrayList<Font> mainMenuTextFonts = new ArrayList<Font>();
    public static ArrayList<String> mainMenuTextPanelNames = new ArrayList<String>();
    public static ArrayList<ArrayList<Integer>> mainMenuTextMetrics = new ArrayList<ArrayList<Integer>>();
    //title screen image buttons
    public static ArrayList<JPanel> mainMenuImageButtonPanels = new ArrayList<JPanel>();
    public static ArrayList<BufferedImage> mainMenuImageButtonImages = new ArrayList<BufferedImage>();
    public static ArrayList<String> mainMenuImageButtonPanelNames = new ArrayList<String>();


    //singleplayer screen

    //singleplayer screen images
    public static ArrayList<JPanel> singleplayerImagePanels = new ArrayList<JPanel>();
    public static ArrayList<BufferedImage> singleplayerImageImages = new ArrayList<BufferedImage>();
    public static ArrayList<String> singleplayerImagePanelNames = new ArrayList<String>();

    //singleplayer screen image buttons
    public static ArrayList<JPanel> singleplayerImageButtonPanels = new ArrayList<JPanel>();
    public static ArrayList<BufferedImage> singleplayerImageButtonImages = new ArrayList<BufferedImage>();
    public static ArrayList<String> singleplayerImageButtonPanelNames = new ArrayList<String>();

    //tiles
    public static ArrayList<JPanel> singleplayerEmptyPanelBounds = new ArrayList<JPanel>();
    public static ArrayList<String> singleplayerEmptyPanelBoundNames = new ArrayList<String>();


    //game over: text
    public static ArrayList<JPanel> gameOverTextPanels = new ArrayList<JPanel>();
    public static ArrayList<String> gameOverTextTexts = new ArrayList<String>();
    public static ArrayList<Font> gameOverTextFonts = new ArrayList<Font>();
    public static ArrayList<String> gameOverTextPanelNames = new ArrayList<String>();
    public static ArrayList<ArrayList<Integer>> gameOverTextMetrics = new ArrayList<ArrayList<Integer>>();
    //game over: image buttons
    public static ArrayList<BufferedImage> gameOverImageButtonImages = new ArrayList<BufferedImage>();
    public static ArrayList<JPanel> gameOverImageButtonPanels = new ArrayList<JPanel>();
    public static ArrayList<String> gameOverImageButtonPanelNames = new ArrayList<String>();






    //other variables
    public static boolean showStatsPanel = false;


    private Canvas canvas;

    public Color backgroundColor = new Color(238, 238, 238);

    public boolean fullscreen = false;


    //global stuff
    //state
    public enum State {
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
    }
    
    public enum Language {
        ENGLISH,
        THAI,
    }


    public static State currentState = State.MAIN_MENU_SCREEN;
    public static Screen currentScreenShown = Screen.MAIN_MENU_SCREEN;
    public static Language currentLanguage = Language.ENGLISH;

    public JFrame displayWindow = new JFrame();

    public GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    public GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();

    public int windowWidth, windowHeight;

    //english fonts init
    Font computerModernFont = null;

    //Display Object is righttttttttttt hereeeeeeeeeeeeeeee!
    //This is called on the first frame
    public Display(int resolutionWidth, int resolutionHeight) {

        try {
            // sorry for doing this lol

            computerModernFont = Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/fonts/computermodern.ttf")));
            graphicsEnvironment.registerFont(computerModernFont);


            currentState = State.MAIN_MENU_SCREEN;

            displayWindow = new JFrame();


            displayWindow.setTitle("Mathematical Base Defense v0.0.1-alpha");
            displayWindow.setIconImage(ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/logo.png"))));
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
            if (fullscreen) {
                setToFullScreen(displayWindow, graphicsDevice);
            } else {
                setSize(resolutionWidth, resolutionHeight);
            }

            //add stuff to display
            displayWindow.add(canvas);
            displayWindow.pack();

            canvas.createBufferStrategy(3);

            //show display
            displayWindow.setLocationRelativeTo(null);
            displayWindow.setVisible(true);

            BufferStrategy bufferStrategy = canvas.getBufferStrategy();
            initializeUserInterfaceComponents(bufferStrategy);



        } catch (Exception exception) {
            displayWindow.dispose();
            new ErrorWindow();
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        int xCoordinateOfClickedPosition = mouseEvent.getX();
        int yCoordinateOfClickedPosition = mouseEvent.getY();
        LogMessage.logMessage("Mouse Clicked at " + "(" + xCoordinateOfClickedPosition + ", " + yCoordinateOfClickedPosition + ")", LogMessage.MessageToLogType.INFO);
        switch (currentState) {
            case MAIN_MENU_SCREEN: {
                for (int i = 0; i < mainMenuImageButtonPanels.size(); i++) {
                    if (checkIfPointIsInBounds(mainMenuImageButtonPanels.get(i), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)) {
                        switch (mainMenuImageButtonPanelNames.get(i)) {
                            case "singleplayerButtonBounds": {
                                currentState = State.SINGLEPLAYER_SCREEN;
                                Game.startSingleplayerGame();
                                break;
                            }
                            case "multiplayerButtonBounds": {
                                currentState = State.MULTIPLAYER_SCREEN;
                                break;
                            }
                            case "settingsButtonBounds": {
                                currentState = State.SETTINGS_SCREEN;
                                break;
                            }
                            case "quitButtonBounds": {
                                displayWindow.dispose();
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
                        if (checkIfPointIsInBounds(singleplayerEmptyPanelBounds.get(singleplayerEmptyPanelBoundNames.indexOf("tile" + tileLetters.charAt(r) + (c + 1) + "Bounds")), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)) {
                            if (Game.singleplayerTilesSelectionState[r][c]) {
                                //selected == true
                                Game.singleplayerTilesSelectionState[r][c] = false;
                                Game.removeTermFromProblem("tile" + tileLetters.charAt(c) + (r + 1));
                            } else {
                                //selected == false
                                Game.singleplayerTilesSelectionState[r][c] = true;
                                Game.addTermToProblem("tile" + tileLetters.charAt(c) + (r + 1));
                            }
                        }
                    }
                }
                if (checkIfPointIsInBounds(singleplayerImageButtonPanels.get(singleplayerImageButtonPanelNames.indexOf("sendButtonBounds")), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)) {
                    LogMessage.logMessage("Sending problem " + Game.problem, LogMessage.MessageToLogType.INFO);
                    Evaluator.sendProblem(Game.problem);
                    break;
                }
            }
            case GAME_OVER_SCREEN: {
                for (int i = 0; i < gameOverImageButtonImages.size(); i++) {
                    if (checkIfPointIsInBounds(gameOverImageButtonPanels.get(i), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)) {
                        switch (gameOverImageButtonPanelNames.get(i)){
                            case "retryButtonBounds" : {
                                Game.startSingleplayerGame();
                                break;
                            }
                            case "returnToTitleScreenButtonBounds" : {
                                Game.changeScreen(Screen.MAIN_MENU_SCREEN);
                                break;
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
        LogMessage.logMessage("Pressed and released key " + e.getKeyCode(), LogMessage.MessageToLogType.INFO);
        switch (e.getKeyCode()) {
            //Esc --> Sets baseHealth to 0, triggering a Game Over.
            case KeyEvent.VK_ESCAPE: {
                if (currentState == State.SINGLEPLAYER_SCREEN) {
                    Game.baseHealth = 0;
                }
                break;
            }
            //F9 --> Toggles Insane Mode
            case KeyEvent.VK_F9:{
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
            case KeyEvent.VK_F10 : {
                showStatsPanel = !showStatsPanel;
                break;
            }
            //F11 --> Toggles fullscreen
            case KeyEvent.VK_F11: {
                setToFullScreen(displayWindow, graphicsDevice);
                break;
            }
            default: {
                //do nothing
            }
        }
    }

    public void setToFullScreen(JFrame displayToMakeFullScreen, GraphicsDevice graphicsDevice) {
        if (fullscreen){
            graphicsDevice.setFullScreenWindow(null);
            fullscreen = false;
        } else {
            graphicsDevice.setFullScreenWindow(displayToMakeFullScreen);
            fullscreen = true;
        }
    }

    /**
     * Renders the frame.
     * @param game dfdiuua9sidfiouadsiufohdasiufhaiuhdfaiudhasdfiujhsduihaf
     */
    public void render(Game game) {

        Point cursorLocation = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(cursorLocation, displayWindow);
        int cursorXPos = (int) cursorLocation.getX();
        int cursorYPos = (int) cursorLocation.getY();

        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        //global screen (all screens)
        Graphics2D canvasGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        Graphics2D imageGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        Graphics2D imageButtonGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        Graphics2D englishTextGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        Graphics2D tooltipGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();

        //single player screen
        Graphics2D singleplayerTileGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        Graphics2D singleplayerTileTermGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        Graphics2D singleplayerUserInterfaceGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        Graphics2D statsPanelGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        Graphics2D enemyGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();


        //set quality for stuff
        englishTextGraphics2D.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);


        //get canvas
        canvasGraphics2D.setColor(backgroundColor);
        canvasGraphics2D.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //actually rendering stuff
        switch (currentState) {
            case TITLE_SCREEN: {
                break;
            }
            case MAIN_MENU_SCREEN: {
                //text
                for (int i = 0; i < mainMenuTextPanels.size(); i++) {
                    renderText(mainMenuTextTexts.get(i), mainMenuTextMetrics.get(i).get(0), mainMenuTextMetrics.get(i).get(1), true, false, 1, 1, mainMenuTextPanels.get(i), mainMenuTextFonts.get(i), Color.BLACK, englishTextGraphics2D);
                }
                //image buttons
                for (int i = 0; i < mainMenuImageButtonPanels.size(); i++) {
                    try {
                        renderImage(mainMenuImageButtonImages.get(i), mainMenuImageButtonPanels.get(i).getX(), mainMenuImageButtonPanels.get(i).getY(), null,false, false,  1, 1, mainMenuImageButtonPanels.get(i), imageGraphics2D);
                    } catch (Exception exception) {
                        displayWindow.dispose();
                        exception.printStackTrace();
                        new ErrorWindow();
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


                //userinterface
                //expression/equation display panel
                singleplayerUserInterfaceGraphics2D.drawRect((int) (90 * windowWidth / 1920), (int) (370 * windowHeight / 1080), (int) (1740 * windowWidth / 1920), (int) (80 * windowHeight / 1080));
                //text on problem panel
                renderLaTeX(Game.problemInLaTeX, (int) (90 * windowWidth / 1920), (int) (385 * windowWidth / 1920), (int) (72 * windowHeight / 1080), true, false, true, singleplayerEmptyPanelBounds.get(singleplayerEmptyPanelBoundNames.indexOf("problemPanelBounds")), Color.BLACK, englishTextGraphics2D);

                //other text

                renderText("Score: " + Game.score, (int) 1220 * windowWidth / 1920, (int) 500 * windowWidth / 1920, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
                renderText("Enemies Killed: " + Game.enemiesKilled, (int) 1220 * windowWidth / 1920, (int) 540 * windowWidth / 1920, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
                renderText("Base Health: " + Game.baseHealth, (int) 1220 * windowWidth / 1920, (int) 580 * windowWidth / 1920, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
                renderText("Time Spent: " + Game.timeSpentInGameInMilliseconds + "ms", (int) 1220 * windowWidth / 1920, (int) 620 * windowWidth / 1920, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
                renderText("Enemy Spawn Rate: " + Game.getEnemySpawnChance(Game.timeSpentInGameInMilliseconds) + "%/16ms", (int) 1220 * windowWidth / 1920, (int) 660 * windowWidth / 1920, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);

                //base
                renderImage(baseImage, singleplayerImagePanels.get(singleplayerImagePanelNames.indexOf("baseBounds")).getX(), singleplayerImagePanels.get(singleplayerImagePanelNames.indexOf("baseBounds")).getY(), null, false, false,  1, 1, mainMenuTextPanels.get(singleplayerImagePanelNames.indexOf("baseBounds")), imageGraphics2D);

                //side panels
                singleplayerUserInterfaceGraphics2D.drawRect((int) (90 * windowWidth / 1920), (int) (460 * windowHeight / 1080), (int) (620 * windowWidth / 1920), (int) (570 * windowHeight / 1080));
                singleplayerUserInterfaceGraphics2D.drawRect((int) (1210 * windowWidth / 1920), (int) (460 * windowHeight / 1080), (int) (620 * windowWidth / 1920), (int) (570 * windowHeight / 1080));

                //send button
                renderImage(sendButtonImage, (int) 750 * windowWidth / 1920, (int) 920 * windowHeight / 1080, null, false, false, 1, 1, singleplayerImagePanels.get(singleplayerImageButtonPanelNames.indexOf("sendButtonBounds")), imageGraphics2D);
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
                            renderLaTeX(Game.singleplayerTileTerms[c][r], (int) (760 + (60 * r)) * windowWidth / 1920, (int) (450 + (60 * c)) * windowHeight / 1080, 48 * windowWidth / 1920f, false, false, false, singleplayerEmptyPanelBounds.get(singleplayerEmptyPanelBoundNames.indexOf("tile" + tileLetters.charAt(c) + (r + 1) + "Bounds")), Color.WHITE, singleplayerTileTermGraphics2D);
                        } else {
                            //selected == false
                            singleplayerTileGraphics2D.setColor(Color.BLACK);
                            singleplayerTileTermGraphics2D.setColor(Color.BLACK);
                            singleplayerTileGraphics2D.drawRect((int) ((750 + (60 * r)) * windowWidth / 1920), (int) ((460 + (60 * c)) * windowHeight / 1080), (int) (60 * windowWidth / 1920), (int) (60 * windowHeight / 1080));
                            renderLaTeX(Game.singleplayerTileTerms[c][r], (int) (760 + (60 * r)) * windowWidth / 1920, (int) ((450 + (60 * c)) * windowHeight / 1080), 48 * windowWidth / 1920f, false, false, false, singleplayerEmptyPanelBounds.get(singleplayerEmptyPanelBoundNames.indexOf("tile" + tileLetters.charAt(c) + (r + 1) + "Bounds")), Color.BLACK, singleplayerTileTermGraphics2D);
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
                    renderLaTeX(variables.charAt(i) + " = " + valueToRender, (int) 1220 * windowWidth / 1920, (int) (690 + (40 * i)) * windowWidth / 1920, 32f * windowWidth/1920, false, false, false, null, Color.BLACK, englishTextGraphics2D);
                }
                //enemies
                for (int i = 0; i < Game.aliveEnemies.size(); i++) {
                    //set properties
                    enemyGraphics2D.setColor(Game.aliveEnemies.get(i).getColor());
                    //render enemy
                    enemyGraphics2D.fillRect(Game.aliveEnemies.get(i).getXPos(), Game.aliveEnemies.get(i).getYPos(), Game.aliveEnemies.get(i).getWidth(), Game.aliveEnemies.get(i).getHeight());
                    //render stuff on top of enemy
                    renderLaTeX(String.valueOf(Game.aliveEnemies.get(i).getRequestedValue()), Game.aliveEnemies.get(i).getXPos() + Game.aliveEnemies.get(i).getWidth() / 2, Game.aliveEnemies.get(i).getYPos() - 75 * windowWidth/1920, 20f * windowWidth/1920, false, false, false, null, Game.aliveEnemies.get(i).getColor(), enemyGraphics2D);
                }
                break;
            }
            case GAME_OVER_SCREEN: {
                //text
                renderText("Game Over!", 0, 0, true, true, 1, 1, gameOverTextPanels.get(gameOverTextPanelNames.indexOf("gameOverTextBounds")), getComputerModernFontOfSpecifiedSize(96), Color.RED, englishTextGraphics2D);
                //image
                for (int i = 0; i < gameOverImageButtonImages.size(); i++){
                    renderImage(gameOverImageButtonImages.get(i), gameOverImageButtonPanels.get(i).getX(), gameOverImageButtonPanels.get(i).getY(), null, false, false,  1, 1, gameOverImageButtonPanels.get(i), imageButtonGraphics2D);
                }
            }
        }

        //getting mouse position and doing stuff
        switch (currentState) {
            case TITLE_SCREEN: {
                break;
            }
            case MAIN_MENU_SCREEN: {
                for (int i = 0; i < mainMenuImageButtonPanels.size(); i++) {
                    if (checkIfPointIsInBounds(mainMenuImageButtonPanels.get(i), cursorXPos, cursorYPos)) {
                        tooltipGraphics2D.setColor(Color.BLACK);
                        tooltipGraphics2D.fillRect((int) (windowWidth * 40 / 1920), (int) (windowHeight * 0.625), (int) (windowWidth * 1840 / 1920), (int) (windowHeight * 0.25));
                        JPanel tooltipTextPanel = new JPanel();
                        tooltipTextPanel.setBounds((int) (windowWidth * 40 / 1920), (int) (windowHeight * 0.625), (int) (windowWidth * 1840 / 1920), (int) (windowHeight * 0.25));
                        switch (mainMenuImageButtonPanelNames.get(i)) {
                            case "singleplayerButtonBounds": {
                                String tooltipText = "Play Mathematical Base Defense by yourself.";
                                englishTextGraphics2D.setFont(getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f));
                                englishTextGraphics2D.setColor(Color.WHITE);
                                renderText("Play Mathematical Base Defense by yourself", getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(tooltipTextPanel, tooltipText, getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f), 1, 1, 'x'), getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(tooltipTextPanel, tooltipText, getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f), 1, 1, 'y'), true, true, 1, 1, tooltipTextPanel, getComputerModernFontOfSpecifiedSize(24), Color.WHITE, englishTextGraphics2D);
                                break;
                            }
                            case "multiplayerButtonBounds": {
                                String tooltipText = "[COMING SOON] Play Mathematical Base Defense with others.";
                                englishTextGraphics2D.setFont(getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f));
                                englishTextGraphics2D.setColor(Color.WHITE);
                                renderText("COMING SOON LOLOLOL", getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(tooltipTextPanel, tooltipText, getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f), 1, 1, 'x'), getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(tooltipTextPanel, tooltipText, getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f), 1, 1, 'y'), true, true, 1, 1, tooltipTextPanel, getComputerModernFontOfSpecifiedSize(24), Color.WHITE, englishTextGraphics2D);
                                break;
                            }
                            case "settingsButtonBounds": {
                                String tooltipText = "Adjust settings for Mathematical Base Defense to your liking.";
                                englishTextGraphics2D.setFont(getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f));
                                englishTextGraphics2D.setColor(Color.WHITE);
                                renderText("Adjust settings for Mathematical Base Defense to your liking.", getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(tooltipTextPanel, tooltipText, getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f), 1, 1, 'x'), getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(tooltipTextPanel, tooltipText, getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f), 1, (int) 1, 'y'), true, true, 1, 1, tooltipTextPanel, getComputerModernFontOfSpecifiedSize(24), Color.WHITE, englishTextGraphics2D);
                                break;
                            }
                            case "quitButtonBounds": {
                                String tooltipText = "Quit Mathematical Base Defense.";
                                englishTextGraphics2D.setFont(getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f));
                                englishTextGraphics2D.setColor(Color.WHITE);
                                renderText("Quit Mathematical Base Defense.", getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(tooltipTextPanel, tooltipText, getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f), 1, 1, 'x'), getOptimalCoordinateToDrawCenteredTextInSpecifiedBounds(tooltipTextPanel, tooltipText, getComputerModernFontOfSpecifiedSize(24*windowWidth/1920f), 1, (int) 1, 'y'), true, true, 1, 1, tooltipTextPanel, getComputerModernFontOfSpecifiedSize(24), Color.WHITE, englishTextGraphics2D);
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case SINGLEPLAYER_SCREEN: {
            }
        }


        //render global stuff

        //stats counter
        if (showStatsPanel){
            statsPanelGraphics2D.fillRect((int) 1600*windowWidth/1920, (int) 100*windowHeight/1080, (int) 320*windowWidth/1920, (int) 100*windowHeight/1080);
            renderText("FPS: " + Loop.framesPerSecondToDisplay, (int) 1605*windowWidth/1920, (int) 120*windowHeight/1080, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(20), Color.WHITE, englishTextGraphics2D);
            renderText("UPS: " + Loop.updatesPerSecondToDisplay, (int) 1605*windowWidth/1920, (int) 145*windowHeight/1080, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(20), Color.WHITE, englishTextGraphics2D);
            renderText("Memory Usage: " + Loop.usedMemoryInMegabytes + "MB/" + Loop.totalMemoryInMegabytes + "MB", (int) 1605*windowWidth/1920, (int) 170*windowHeight/1080, false, false, 1, 1, null, getComputerModernFontOfSpecifiedSize(20), Color.WHITE, englishTextGraphics2D);
        }

        //notification text
        for (int i = 0; i < aliveNotificationTexts.size(); i++){
            aliveNotificationTexts.get(i).setAge(aliveNotificationTexts.get(i).getAge()+1);
            if (aliveNotificationTexts.get(i).getAge() <= 20) {
                renderText(aliveNotificationTexts.get(i).getText(), 200*windowWidth/1920, (int) aliveNotificationTexts.get(i).getAge()*2*windowHeight/1080, true, false, 1, 1, aliveNotificationTextPanels.get(i), getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
            } else if (aliveNotificationTexts.get(i).getAge() <= 200){
                renderText(aliveNotificationTexts.get(i).getText(), 200*windowWidth/1920, (int) 40*windowHeight/1080, true, false, 1, 1, aliveNotificationTextPanels.get(i), getComputerModernFontOfSpecifiedSize(32), Color.BLACK, englishTextGraphics2D);
            } else {
                aliveNotificationTexts.remove(i);
            }
        }



        //dispose graphics2D stuff
        canvasGraphics2D.dispose();
        imageButtonGraphics2D.dispose();
        tooltipGraphics2D.dispose();
        englishTextGraphics2D.dispose();
        statsPanelGraphics2D.dispose();

        //singleplayer screen
        singleplayerTileGraphics2D.dispose();
        singleplayerTileTermGraphics2D.dispose();
        enemyGraphics2D.dispose();







        bufferStrategy.show();
    }





    /*
     *
     * Other Methods are here.
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
     *
     *
     *
     *
     *
     */

    /**
     * Initializes the user interface components. (Called at launch)
     * @param bufferStrategy ?????
     */
    private void initializeUserInterfaceComponents(BufferStrategy bufferStrategy) {

        Graphics2D temporaryEnglishTextGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        String fileNameEnding = "-en";

        try {

            //images that AREN'T affected by selected language
            schoolLogoImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/school_logo.png")));
            nationalSoftwareContestLogoImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/national_software_contest_logo.png")));
            baseImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/base.png")));

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

            singleplayerButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/singleplayer_button" + fileNameEnding + ".png")));
            multiplayerButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/multiplayer_button" + fileNameEnding + ".png")));
            settingsButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/settings_button" + fileNameEnding + ".png")));
            quitButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/quit_button" + fileNameEnding + ".png")));
            creditsButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/credits_button" + fileNameEnding + ".png")));
            sendButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/send_button" + fileNameEnding + ".png")));
            retryButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/retry_button" + fileNameEnding + ".png")));
            returnToTitleScreenButtonImage = ImageIO.read(new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/game/assets/images/return_to_title_screen_button" + fileNameEnding + ".png")));

        } catch (Exception exception) {
            displayWindow.dispose();
            new ErrorWindow();
        }


        //start screen


        //title screen: text
        temporaryEnglishTextGraphics2D.setFont(getComputerModernFontOfSpecifiedSize(96));
        Text titleText = new Text("Mathematical Base Defense", 0, (int) (windowWidth * 0.05), temporaryEnglishTextGraphics2D.getFontMetrics().stringWidth("Mathematical Base Defense"), (int) (96 * windowWidth / 1920), 0, 0, windowWidth, (int) (windowHeight * 0.15), getComputerModernFontOfSpecifiedSize(96), (int) (96 * windowWidth / 1920), "mainMenuText", "mainMenuTextBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        //title screen: imageButtons
        ImageButton singleplayerButton = new ImageButton(singleplayerButtonImage, (int) (windowWidth * 40 / 1920), (int) (windowHeight * 0.5), (singleplayerButtonImage.getWidth() * windowWidth / 1920), (singleplayerButtonImage.getHeight() * windowHeight / 1080),  "singleplayerButton", "singleplayerButtonBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton multiplayerButton = new ImageButton(multiplayerButtonImage, (int) (windowWidth * 520 / 1920), (int) (windowHeight * 0.5), (multiplayerButtonImage.getWidth() * windowWidth / 1920), (multiplayerButtonImage.getHeight() * windowHeight / 1080),  "multiplayerButton", "multiplayerButtonBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton settingsButton = new ImageButton(settingsButtonImage, (int) (windowWidth * 1000 / 1920), (int) (windowHeight * 0.5), (settingsButtonImage.getWidth() * windowWidth / 1920), (settingsButtonImage.getHeight() * windowHeight / 1080),  "settingsButton", "settingsButtonBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton quitButton = new ImageButton(quitButtonImage, (int) (windowWidth * 1480 / 1920), (int) (windowHeight * 0.5), (quitButtonImage.getWidth() * windowWidth / 1920), (quitButtonImage.getHeight() * windowHeight / 1080),  "quitButton", "quitButtonBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        //title screen: images
        ImageButton schoolLogo = new ImageButton(schoolLogoImage, (int) (windowWidth * 0.005), (int) (windowHeight * 0.9), (schoolLogoImage.getWidth() * windowWidth / 1920), (schoolLogoImage.getHeight() * windowHeight / 1080),  "schoolLogo", "schoolLogoBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);
        ImageButton nationalSoftwareContestLogo = new ImageButton(nationalSoftwareContestLogoImage, (int) (windowWidth * 0.04), (int) (windowHeight * 0.9), (nationalSoftwareContestLogoImage.getWidth() * windowWidth / 1920), (nationalSoftwareContestLogoImage.getHeight() * windowHeight / 1080),  "nationalSoftwareContestLogo", "nationalSoftwareContestLogoBounds", Display.Screen.MAIN_MENU_SCREEN, bufferStrategy);


        //singleplayer: imagebuttons
        ImageButton sendButton = new ImageButton( sendButtonImage,(int) windowWidth * 750 / 1920, (int) windowHeight * 920 / 1080, sendButtonImage.getWidth() * windowWidth / 1920, sendButtonImage.getHeight() * windowHeight / 1080, "sendButton", "sendButtonBounds", Display.Screen.SINGLEPLAYER_SCREEN, bufferStrategy);
        //singleplayer: images
        Image base = new Image(baseImage,(int) 100 * windowWidth / 1920, (int) 80 * windowHeight / 1080, (int) baseImage.getWidth() * windowWidth / 1920, (int) baseImage.getHeight() * windowHeight / 1080,  "base", "baseBounds", Display.Screen.SINGLEPLAYER_SCREEN, bufferStrategy);
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
        //game over screen: imagebuttons
        ImageButton retryButton = new ImageButton(retryButtonImage, (int) windowWidth*520/1920, (int) windowHeight*800/1080, retryButtonImage.getWidth() * windowWidth/1920, retryButtonImage.getHeight()  * windowWidth/1080, "retryButton", "retryButtonBounds", Screen.GAME_OVER_SCREEN, bufferStrategy);
        ImageButton returnToTitleScreenButton = new ImageButton(returnToTitleScreenButtonImage, (int) windowWidth*1000/1920, (int) windowHeight*800/1080, retryButtonImage.getWidth() * windowWidth/1920, retryButtonImage.getHeight()  * windowWidth/1080, "returnToTitleScreenButton", "returnToTitleScreenButtonBounds", Screen.GAME_OVER_SCREEN, bufferStrategy);
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
        icon.paintIcon(null, graphics2DInstance, (int) xPos, (int) yPos+(48*windowWidth/1920));
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
}














































