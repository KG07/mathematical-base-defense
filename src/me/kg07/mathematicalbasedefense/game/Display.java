package me.kg07.mathematicalbasedefense.game;

/*
 * Display Class for MathematicalBaseDefense
 * @author  mistertfy64
 * @version 0.0.1-alpha
 * @since   2020-11-15 or something i cant remember
 *
 */

import me.kg07.mathematicalbasedefense.game.userinterface.ImageButton;
import me.kg07.mathematicalbasedefense.launcher.LauncherWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Date;

//i dont like mdes and phuttipong lol

public class Display extends JFrame implements MouseListener {

    /*
     *
     * Register images here!
     *
     */

    //images
    //title screen
    BufferedImage singlePlayerButtonImage;
    BufferedImage multiPlayerButtonImage;
    BufferedImage optionsButtonImage;
    BufferedImage quitButtonImage;
    BufferedImage schoolLogoImage;
    BufferedImage nationalSoftwareContestLogoImage;


    //array list panels
    //title screen
    public static ArrayList<JPanel> titlePanels = new ArrayList<JPanel>();
    public static ArrayList<Image> titlePanelImages = new ArrayList<Image>();
    public static ArrayList<String> titlePanelNames = new ArrayList<String>();


    private Canvas canvas;

    public Color backgroundColor = new Color(238, 238, 238);


    //state
    public enum State {
        TITLE_SCREEN,
        SINGLEPLAYER_SCREEN,
        MULTIPLAYER_SCREEN,
        OPTIONS_SCREEN,
    }
    public static State currentState = State.TITLE_SCREEN;


    public JFrame displayWindow = new JFrame();

    GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();


    private final int mainMonitorWidth = graphicsDevice.getDisplayMode().getWidth();
    private final int mainMonitorHeight = graphicsDevice.getDisplayMode().getHeight();
    public int windowWidth, windowHeight;


    //declare fonts
    Font computerModernFont = null;
    Font computerModernFont12Pixels = null;
    Font computerModernFont16Pixels = null;
    Font computerModernFont20Pixels = null;
    Font computerModernFont24Pixels = null;
    Font computerModernFont32Pixels = null;
    Font computerModernFont48Pixels = null;
    Font computerModernFont72Pixels = null;

    public Display(int resolutionWidth, int resolutionHeight, boolean fullscreen) {

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


            displayWindow = new JFrame();


            displayWindow.setTitle("Mathematical Base Defense v0.0.1-alpha");
            displayWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            displayWindow.setResizable(false);

            windowWidth = resolutionWidth;
            windowHeight = resolutionHeight;

            canvas = new Canvas();
            canvas.setPreferredSize(new Dimension(resolutionWidth, resolutionHeight));
            canvas.addMouseListener(this);

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

            System.out.println("Display Creation Complete");

        } catch (Exception exception) {
            displayWindow.dispose();
            new ErrorWindow();
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent){
        int xCoordinateOfClickedPosition = mouseEvent.getX();
        int yCoordinateOfClickedPosition = mouseEvent.getY();
        System.out.println("Mouse Clicked at " + "(" + xCoordinateOfClickedPosition + ", " + yCoordinateOfClickedPosition + ")");
        switch (currentState){
            case TITLE_SCREEN: {
                    for (int i = 0; i < titlePanels.size(); i++){
                        if (checkIfPointIsInRectangle(titlePanels.get(i), xCoordinateOfClickedPosition, yCoordinateOfClickedPosition)){
                            switch (titlePanelNames.get(i)){
                                case "singlePlayerButtonBounds" : {
                                    currentState = State.SINGLEPLAYER_SCREEN;
                                    break;
                                }
                                case "multiPlayerButtonBounds" : {
                                    currentState = State.MULTIPLAYER_SCREEN;
                                    break;
                                }
                                case "optionsButtonBounds" : {
                                    currentState = State.OPTIONS_SCREEN;
                                    break;
                                }
                                case "quitButtonBounds" : {
                                    displayWindow.dispose();
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            case SINGLEPLAYER_SCREEN: {
                    break;
                }

        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

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



    public void setToFullScreen(JFrame displayToMakeFullScreen, GraphicsDevice graphicsDevice) {
        graphicsDevice.setFullScreenWindow(displayToMakeFullScreen);
    }

    public void render(Game game) {

        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics2D canvasGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();
        Graphics2D imageButtonGraphics2D = (Graphics2D) bufferStrategy.getDrawGraphics();


        //get canvas
        canvasGraphics2D.setColor(backgroundColor);
        canvasGraphics2D.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //actually rendering stuff
        switch (currentState){
            case TITLE_SCREEN: {
                for (int i = 0; i < titlePanels.size(); i++){
                    imageButtonGraphics2D.drawImage(titlePanelImages.get(i), titlePanels.get(i).getX(), titlePanels.get(i).getY(), titlePanels.get(i).getWidth(), titlePanels.get(i).getHeight(), null);
                }
                break;
            }
        }





        canvasGraphics2D.dispose();
        bufferStrategy.show();
    }










    /*
     * Finds the optimal x/y coordinate to draw centered text given the bounds, the text.
     *
     * @param graphics2D The graphics
     * @param text The text
     *
     * I mean isn't that obvious???
     *
     */
    private int getOptimalCoordinateToDrawCenteredText(Graphics2D graphics2D, JPanel bounds, String text, char coordinateToReturn) {
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        if (coordinateToReturn == 'x') {
            return bounds.getX() + (bounds.getWidth() - fontMetrics.stringWidth(text)) / 2;
        } else {
            return bounds.getY() + (bounds.getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        }
    }


    /*
     * Checks if a point is in a rectangle given the corners, and the coordinates of the point.
     *
     *
     * I mean isn't that obvious???
     *
     */
    private boolean checkIfPointIsInRectangle(JPanel panel, int px, int py){
        return px > panel.getX() && px < panel.getX() + panel.getWidth() && py > panel.getY() && py < panel.getY() + panel.getHeight();
    }

    //Run when the single player button is pressed on the title screen
    private void singlePlayerButtonPressed(){
        currentState = State.SINGLEPLAYER_SCREEN;
    }

    private void initializeUserInterfaceComponents(BufferStrategy bufferStrategy){

        try {

            //images that aren't affected by selected language
            schoolLogoImage = ImageIO.read(new File("src/me/kg07/mathematicalbasedefense/game/assets/images/schoollogo.png"));
            nationalSoftwareContestLogoImage = ImageIO.read(new File("src/me/kg07/mathematicalbasedefense/game/assets/images/nationalsoftwarecontestlogo.png"));

            singlePlayerButtonImage = ImageIO.read(new File("src/me/kg07/mathematicalbasedefense/game/assets/images/singleplayerbutton-en.png"));
            multiPlayerButtonImage = ImageIO.read(new File("src/me/kg07/mathematicalbasedefense/game/assets/images/multiplayerbutton-en.png"));
            optionsButtonImage = ImageIO.read(new File("src/me/kg07/mathematicalbasedefense/game/assets/images/optionsbutton-en.png"));
            quitButtonImage = ImageIO.read(new File("src/me/kg07/mathematicalbasedefense/game/assets/images/quitbutton-en.png"));

        } catch (Exception exception) {
            displayWindow.dispose();
            new ErrorWindow();
        }



        //title screen: text

        //title screen: buttons
        ImageButton singlePlayerButton = new ImageButton((int) (windowWidth * 0.34375), (int) (windowHeight * 0.25), (singlePlayerButtonImage.getWidth() * windowWidth / 1920), (singlePlayerButtonImage.getHeight() * windowHeight / 1080), singlePlayerButtonImage, "singlePlayerButton", "singlePlayerButtonBounds", "titleScreen", bufferStrategy);
        ImageButton multiPlayerButton = new ImageButton((int) (windowWidth * 0.34375), (int) (windowHeight * 0.4), (multiPlayerButtonImage.getWidth() * windowWidth / 1920), (multiPlayerButtonImage.getHeight() * windowHeight / 1080), multiPlayerButtonImage, "multiPlayerButton", "multiPlayerButtonBounds", "titleScreen", bufferStrategy);
        ImageButton optionsButton = new ImageButton((int) (windowWidth * 0.34375), (int) (windowHeight * 0.55), (optionsButtonImage.getWidth() * windowWidth / 1920), (optionsButtonImage.getHeight() * windowHeight / 1080), optionsButtonImage, "optionsButton", "optionsButtonBounds", "titleScreen", bufferStrategy);
        ImageButton quitButton = new ImageButton((int) (windowWidth * 0.34375), (int) (windowHeight * 0.7), (quitButtonImage.getWidth() * windowWidth / 1920), (quitButtonImage.getHeight() * windowHeight / 1080), quitButtonImage, "quitButton", "quitButtonBounds", "titleScreen", bufferStrategy);
        ImageButton schoolLogo = new ImageButton((int) (windowWidth * 0.005), (int) (windowHeight * 0.9), (schoolLogoImage.getWidth() * windowWidth / 1920), (schoolLogoImage.getHeight() * windowHeight / 1080), schoolLogoImage, "schoolLogo", "schoolLogoBounds", "titleScreen", bufferStrategy);
        ImageButton nationalSoftwareContestLogo = new ImageButton((int) (windowWidth * 0.04), (int) (windowHeight * 0.9), (nationalSoftwareContestLogoImage.getWidth() * windowWidth/ 1920), (nationalSoftwareContestLogoImage.getHeight() * windowHeight / 1080), nationalSoftwareContestLogoImage, "nationalSoftwareContestLogo", "nationalSoftwareContestLogoBounds", "titleScreen", bufferStrategy);
    }
}




