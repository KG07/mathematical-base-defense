package me.kg07.mathematicalbasedefense.launcher;


/*
 * Launcher for MathematicalBaseDefense
 * @author  mistertfy64
 * @version 0.0.1-alpha
 * @since   2020-11-15 or something i cant remember
 *
 */


import me.kg07.mathematicalbasedefense.game.core.Game;
import me.kg07.mathematicalbasedefense.game.core.Loop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LauncherWindow implements ActionListener {

    //declare frame
    JFrame launcherWindow;
    //declare panels
    JPanel titlePanel;
    JPanel launchGameButtonPanel;
    JPanel versionPanel;
    JPanel resolutionSelectionBoxPanel;
    //declare labels
    JLabel titleLabel;
    JLabel launchGameButtonTextLabel;
    JLabel versionLabel;
    //declare combo boxes
    JComboBox resolutionSelectionBox;
    //declare buttons
    JButton launchGameButton;


    String[] resolutions = {"1024x576", "1152x648", "1280x720", "1366x768", "1600x900", "1920x1080"};

    int selectedResolutionWidth = 1920;
    int selectedResolutionHeight = 1080;
    boolean fullScreen = false;

    public LauncherWindow(){
        //declare fonts
        Font computerModernFont = null;
        Font computerModernFont12Pixels = null;
        Font computerModernFont16Pixels = null;
        Font computerModernFont20Pixels = null;
        Font computerModernFont24Pixels = null;
        Font computerModernFont32Pixels = null;
        Font computerModernFont48Pixels = null;
        try {
            // sorry for doing this lol
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            InputStream computerModernFontInputStream = new BufferedInputStream(new FileInputStream("src/me/kg07/mathematicalbasedefense/launcher/assets/computermodern.ttf"));
            computerModernFont = Font.createFont(Font.TRUETYPE_FONT, computerModernFontInputStream).deriveFont(12f);
            computerModernFont12Pixels = computerModernFont.deriveFont(12f);
            computerModernFont16Pixels = computerModernFont.deriveFont(16f);
            computerModernFont20Pixels = computerModernFont.deriveFont(20f);
            computerModernFont24Pixels = computerModernFont.deriveFont(24f);
            computerModernFont32Pixels = computerModernFont.deriveFont(32f);
            computerModernFont48Pixels = computerModernFont.deriveFont(48f);
            //register fonts
            ge.registerFont(computerModernFont);
            ge.registerFont(computerModernFont12Pixels);
            ge.registerFont(computerModernFont16Pixels);
            ge.registerFont(computerModernFont20Pixels);
            ge.registerFont(computerModernFont24Pixels);
            ge.registerFont(computerModernFont32Pixels);
            ge.registerFont(computerModernFont48Pixels);


            //declare variables









            //make window
            launcherWindow = new JFrame();
            //declare panels
            titlePanel = new JPanel();
            launchGameButtonPanel = new JPanel();
            versionPanel = new JPanel();
            resolutionSelectionBoxPanel = new JPanel();
            //declare labels
            titleLabel = new JLabel();
            launchGameButtonTextLabel = new JLabel();
            versionLabel = new JLabel();
            //declare combobox
            resolutionSelectionBox = new JComboBox(resolutions);
            //declare buttons
            launchGameButton = new JButton();


            //set frame properties
            launcherWindow.setSize(800, 600);
            launcherWindow.setTitle("Mathematical Base Defense Launcher");
            launcherWindow.setResizable(false);
            launcherWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //set label properties
            titleLabel.setFont(computerModernFont48Pixels);
            titleLabel.setText("Mathematical Base Defense");

            versionLabel.setFont(computerModernFont16Pixels);
            versionLabel.setText("Version 0.1.0-alpha");

            launchGameButtonTextLabel.setFont(computerModernFont20Pixels);
            launchGameButtonTextLabel.setText("Launch Mathematical Base Defense");
            launchGameButtonTextLabel.setHorizontalTextPosition(JLabel.CENTER);
            launchGameButtonTextLabel.setVerticalTextPosition(JLabel.CENTER);


            //set button properties
            launchGameButton.add(launchGameButtonTextLabel);

            //set combo box
            resolutionSelectionBox.setSelectedIndex(resolutions.length - 1);


            //add event listeners
            launchGameButton.addActionListener(this);
            resolutionSelectionBox.addActionListener(this);

            // set panel properties
            titlePanel.add(titleLabel);
            titlePanel.setBounds(100, 15, 600, 50);
            versionPanel.add(versionLabel);
            versionPanel.setBounds(250, 70, 300, 25);
            launchGameButtonPanel.add(titlePanel);
            launchGameButtonPanel.add(launchGameButton);
            launchGameButtonPanel.setBounds(200, 500, 400, 50);
            launchGameButtonPanel.setPreferredSize(new Dimension(600, 600));
            resolutionSelectionBoxPanel.add(resolutionSelectionBox);
            resolutionSelectionBoxPanel.setBounds(100, 500, 100, 35);


            //set stuff
            launcherWindow.add(titlePanel);
            launcherWindow.add(versionPanel);
            launcherWindow.add(launchGameButtonPanel);
            launcherWindow.add(resolutionSelectionBoxPanel);
            launcherWindow.setLayout(null);


            //idk what is this for
            changeStringToNewResolution("1920x1080");

            //show the frame
            launcherWindow.setVisible(true);

            
            
        } catch (IOException | FontFormatException e) {
            JFrame errorWindow = new JFrame();
            JLabel label = new JLabel();
            errorWindow.setSize(500, 100);
            errorWindow.setResizable(false);
            errorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            label.setText("An error has occurred while trying to launch the launcher.");
            errorWindow.add(label);
            errorWindow.setVisible(true);
            e.printStackTrace();
        }



        
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
            //launch game button
        if (actionEvent.getSource() == launchGameButton){
            launcherWindow.dispose();
            new Thread(new Loop(new Game(selectedResolutionWidth, selectedResolutionHeight))).start();

            //resolution selection box
        } else if (actionEvent.getSource() == resolutionSelectionBox){
            JComboBox resolutionSelectionBox = (JComboBox) actionEvent.getSource();
            String selectedResolution = (String) resolutionSelectionBox.getSelectedItem();
            changeStringToNewResolution(selectedResolution);
        }
    }


    //AT LEAST I TRIED SO SHUT UP
    //this sucks, am i right?
    private void changeStringToNewResolution(String selectedResolution){
        for (int i = 0; i < resolutions.length; i++){
            if (selectedResolution.equals(resolutions[i])){
                switch (i){
                    case 0: {
                        selectedResolutionWidth = 1024;
                        selectedResolutionHeight = 576;
                        break;
                    }
                    case 1: {
                        selectedResolutionWidth = 1152;
                        selectedResolutionHeight = 648;
                        break;
                    }
                    case 2: {
                        selectedResolutionWidth = 1280;
                        selectedResolutionHeight = 720;
                        break;
                    }
                    case 3: {
                        selectedResolutionWidth = 1366;
                        selectedResolutionHeight = 768;
                        break;
                    }
                    case 4: {
                        selectedResolutionWidth = 1600;
                        selectedResolutionHeight = 900;
                        break;
                    }
                    case 5: {
                        selectedResolutionWidth = 1920;
                        selectedResolutionHeight = 1080;
                        break;
                    }

                }
            }
        }
    }

}
