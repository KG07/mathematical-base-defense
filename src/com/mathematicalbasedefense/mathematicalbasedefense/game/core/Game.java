package com.mathematicalbasedefense.mathematicalbasedefense.game.core;

import com.mathematicalbasedefense.mathematicalbasedefense.game.Display;
;
import com.mathematicalbasedefense.mathematicalbasedefense.game.entities.Enemy;
import org.json.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//backend stuff here



public class Game {


    //tiles
    public static String[][] singleplayerTileTerms = new String[7][7];
    public static boolean[][] singleplayerTilesSelectionState = new boolean[7][7];
    public static String[] singleplayerTileTermsQueue = new String[50];

    //equation
    public static String problem = "";
    public static String problemInLaTeX = "";
    public static ArrayList<String> problemAsArrayList = new ArrayList<String>();
    public static ArrayList<String> problemInLaTeXAsArrayList = new ArrayList<String>();
    public static ArrayList<String> tileNamesOfTermsOfProblem = new ArrayList<String>();

    //enemies
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public static ArrayList<Enemy> aliveEnemies = new ArrayList<Enemy>();


    //display
    private Display display;
    public static boolean fullscreenEnabled = false;

    //tile variables
    public static float valueOfVariableA = 0;
    public static float valueOfVariableB = 0;
    public static float valueOfVariableC = 0;
    public static float valueOfVariableD = 0;
    public static float valueOfVariableN = 0;
    public static float valueOfVariableX = 0;
    public static float valueOfVariableY = 0;
    public static float valueOfVariableZ = 0;

    //game stats
    public static double score = 0;
    public static double recentScoreIncrease = 0;
    public static long enemiesKilled = 0;
    public static long baseHealth = 10;
    public static long singleplayerGameStartTime = 0;
    public static long timeSpentInGameInMilliseconds = 0;
    public static long finalTimeSpentInGameInMilliseconds = 0;


    public enum TermType {
        NUMBER,
        SYMBOL,
        VARIABLE,
        CONSTANT,
    }

    //other variables
    public static long timeElapsed = 0;
    public static int windowWidth, windowHeight;
    public int enemiesSpawned;
    public static boolean insaneModeActivated = false;

    //declare fonts


    public enum EnemyColor {
        RANDOM,
        RED,
        GREEN,
        BLUE,
        YELLOW,
        ORANGE,
        BLACK,
    }

    public static EnemyColor selectedEnemyColor = EnemyColor.RANDOM;

    public Game(int width, int height) {

        try {
            // sorry for doing this lol
            //declare display
            display = new Display(width, height);
            windowWidth = width;
            windowHeight = height;
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


    public static void initializeVariables() {
        //initialize variables here
        Arrays.fill(Display.settingsScreenButtonsXOffset, -400);
        getVariablesFromSettingsJSONFile();

    }


    //Called once every 0.016s (16ms)
    public void update() {
        updateMainMenuScreen();
        updateSingleplayerScreen();
    }

    public void updateMainMenuScreen() {
        //Main Menu Screen
        boolean[] cursorIsHoveringOverMainMenuButton = new boolean[6];
        cursorIsHoveringOverMainMenuButton[0] = Display.cursorIsHoveringOnSingleplayerButton;
        cursorIsHoveringOverMainMenuButton[1] = Display.cursorIsHoveringOnMultiplayerButton;
        cursorIsHoveringOverMainMenuButton[2] = Display.cursorIsHoveringOnShopButton;
        cursorIsHoveringOverMainMenuButton[3] = Display.cursorIsHoveringOnOptionsButton;
        cursorIsHoveringOverMainMenuButton[4] = Display.cursorIsHoveringOnCreditsButton;
        cursorIsHoveringOverMainMenuButton[5] = Display.cursorIsHoveringOnQuitButton;


        for (int i = 0; i < cursorIsHoveringOverMainMenuButton.length; i++) {
            if (cursorIsHoveringOverMainMenuButton[i]) {
                if (Display.mainMenuScreenButtonsXOffset[i] < 40) {
                    Display.mainMenuScreenButtonsXOffset[i] += 4;
                }
            } else {
                if (Display.mainMenuScreenButtonsXOffset[i] > 0) {
                    Display.mainMenuScreenButtonsXOffset[i] -= 4;
                }
            }
        }


        //Settings screen
        boolean[] cursorIsHoveringOverSettingsButton = new boolean[3];
        cursorIsHoveringOverSettingsButton[0] = Display.cursorIsHoveringOnSettingsVideoSectionButton;
        cursorIsHoveringOverSettingsButton[1] = Display.cursorIsHoveringOnSettingsAudioSectionButton;
        cursorIsHoveringOverSettingsButton[2] = Display.cursorIsHoveringOnSettingsBackButton;

        for (int i = 0; i < cursorIsHoveringOverSettingsButton.length; i++) {
            if (cursorIsHoveringOverSettingsButton[i]) {
                if (Display.settingsScreenButtonsXOffset[i] < 40) {
                    Display.settingsScreenButtonsXOffset[i] += 4;
                }
            } else {
                if (Display.settingsScreenButtonsXOffset[i] > 0) {
                    Display.settingsScreenButtonsXOffset[i] -= 4;
                }
            }
        }


        //Credits screen
        if (Display.cursorIsHoveringOnCreditsBackButton) {
            if (Display.creditsScreenBackButtonXOffset < 40) {
                Display.creditsScreenBackButtonXOffset += 4;
            }
        } else {
            if (Display.creditsScreenBackButtonXOffset > 0) {
                Display.creditsScreenBackButtonXOffset -= 4;
            }
        }
    }


    public void updateSingleplayerScreen() {
        //Singleplayer Screen
        Random random = new Random();
        timeSpentInGameInMilliseconds = System.currentTimeMillis() - singleplayerGameStartTime;
        if (Display.currentScreenShown == Display.Screen.SINGLEPLAYER_SCREEN) {
            if (Math.random() <= getEnemySpawnChance(timeSpentInGameInMilliseconds)) {
                enemiesSpawned++;
                Enemy enemy = new Enemy((int) 1820 * windowWidth / 1920, (int) 215 * windowHeight / 1080, (int) 150 * windowWidth / 1920, (int) 150 * windowHeight / 1080, random.nextInt(361), 10 * windowWidth / 1920, TermGenerator.generateEnemyTerm(), 100, 1, 1, 1, getEnemyColor(), "enemyNumber" + (enemiesSpawned + 1));
                enemies.add(enemy);
                aliveEnemies.add(enemy);
            }
        }

        //Remove any offstage enemies
        for (int i = 0; i < aliveEnemies.size(); i++) {
            if (aliveEnemies.get(i).getXPos() <= 100) {
                baseHealth--;
                aliveEnemies.remove(i);
            }
        }

        //Moves enemies
        for (int i = 0; i < aliveEnemies.size(); i++) {
            Game.aliveEnemies.get(i).move((int) (random.nextInt(5) * windowWidth / 1920));
        }

        if (baseHealth <= 0 && Display.currentState == Display.State.SINGLEPLAYER_SCREEN_PLAYING && Display.currentScreenShown == Display.Screen.SINGLEPLAYER_SCREEN) {
            Display.currentState = Display.State.GAME_OVER_SCREEN;
            Display.currentScreenShown = Display.Screen.GAME_OVER_SCREEN;
            finalTimeSpentInGameInMilliseconds = timeSpentInGameInMilliseconds;
            Display.singleplayerScreenBloodPanels.clear();
            Display.singleplayerScreenBloodAge.clear();
            Display.singleplayerScreenBloodRotation.clear();
            Display.singleplayerScreenBloodColor.clear();

            writeScoreToLocalScoresJSONFile(score);

        }



        //Move blood
        /*
        for (int b = 0; b < Display.singleplayerScreenBloodPanels.size(); b++){
            Display.singleplayerScreenBloodPanels.get(b).setBounds(Display.singleplayerScreenBloodPanels.get(b).getX() + 5 * windowWidth/1920, Display.singleplayerScreenBloodPanels.get(b).getY() + 5 * windowWidth/1920, Display.singleplayerScreenBloodPanels.get(b).getWidth(), Display.singleplayerScreenBloodPanels.get(b).getHeight());
            Display.singleplayerScreenBloodAge.set(b, Display.singleplayerScreenBloodAge.get(b) + 1);
        }

        for (int b = 0; b < Display.singleplayerScreenBloodPanels.size(); b++){
            if (Display.singleplayerScreenBloodAge.get(b) >= 60){
                Display.singleplayerScreenBloodPanels.remove(b);
                Display.singleplayerScreenBloodAge.remove(b);
                Display.singleplayerScreenBloodRotation.remove(b);
                Display.singleplayerScreenBloodColor.remove(b);
                break;
            }
        }
        */
    }

    public void render() {
        if (Loop.gameAllowedToRender) {
            display.render(this);
        }
    }


    public static void startSingleplayerGame() {

        //change state
        Display.currentState = Display.State.SINGLEPLAYER_SCREEN_PLAYING;


        //reset stuff
        score = 0;
        recentScoreIncrease = 0;
        enemiesKilled = 0;
        baseHealth = 10;
        singleplayerGameStartTime = System.currentTimeMillis();
        aliveEnemies.clear();

        //clear problem from previous game
        problemAsArrayList.clear();
        problemInLaTeXAsArrayList.clear();
        problem = "";
        problemInLaTeX = "";
        tileNamesOfTermsOfProblem.clear();

        //random variable
        Random random = new Random();


        //tile variable values
        valueOfVariableA = 0;
        valueOfVariableB = 0;
        valueOfVariableC = 0;
        valueOfVariableD = 0;
        valueOfVariableN = 0;
        valueOfVariableX = 0;
        valueOfVariableY = 0;
        valueOfVariableZ = 0;

        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 7; c++) {
                singleplayerTilesSelectionState[r][c] = false;
            }
        }

        //pick random terms for each tile
        for (int r = 0; r < 7; r++) {
            for (int c = 0; c < 7; c++) {
                singleplayerTileTerms[r][c] = pickRandomTerm();
            }
        }
        for (int i = 0; i < 10; i++) {
            singleplayerTileTermsQueue[i] = pickRandomTerm();
        }


    }


    public static String pickRandomTerm() {
        Random random = new Random();
        int selectedTermID = random.nextInt(22);
        String variableTerms = "abcnxyz";
        if (selectedTermID < 10) { //0-9 --> return selected number as string
            return String.valueOf(selectedTermID);
        } else if (selectedTermID < 17) { //10 - 16 --> return a letter
            return Character.toString(variableTerms.charAt(selectedTermID - 10));
        } else if (selectedTermID < 22) { //17-21 --> return an math sign
            switch (selectedTermID) {
                case 17: {
                    return "\\plus";
                }
                case 18: {
                    return "\\minus";
                }
                case 19: {
                    return "\\times";
                }
                case 20: {
                    return "\\div";
                }
                case 21: {
                    return "\\equals";
                }
            }
        } else {
            LogMessage.logMessage("Unknown Selected Term ID: " + selectedTermID, LogMessage.MessageType.ERROR);
            throw new IllegalArgumentException("Unknown Selected Term ID: " + selectedTermID);
        }
        return "Error! Contact Developers!";
    }

    /**
     * Adds a term to the end of the problem.
     *
     * @param tileName The tile's name of the term.
     */
    public static void addTermToProblem(String tileName) {
        //part 1: get tile name and term in tile
        String tileNameLetters = "ABCDEFG";
        String termSelected = "";
        //y, x from top and from left
        /*
         * \ A B C D E F G
         * 1
         * 2
         * 3
         * 4
         * 5
         * 6
         * 7
         *
         */
        int[] tileLocation = new int[2];
        char letterOfTileName = tileName.charAt(4);
        for (int i = 0; i < 7; i++) {
            if (tileNameLetters.charAt(i) == letterOfTileName) {
                tileLocation[1] = i;
                break;
            }
        }
        tileLocation[0] = (Integer.parseInt(String.valueOf(tileName.charAt(5))) - 1);
        termSelected = singleplayerTileTerms[tileLocation[0]][tileLocation[1]];

        problemInLaTeXAsArrayList.add(termSelected);

        problemInLaTeX = "";
        for (int i = 0; i < problemInLaTeXAsArrayList.size(); i++) {
            problemInLaTeX = problemInLaTeX + " " + problemInLaTeXAsArrayList.get(i);
        }


        switch (termSelected) {
            case "\\plus": {
                termSelected = "+";
                break;
            }
            case "\\minus": {
                termSelected = "-";
                break;
            }
            case "\\times": {
                termSelected = "*";
                break;
            }
            case "\\div": {
                termSelected = "/";
                break;
            }
            case "\\equals": {
                termSelected = "=";
            }
            default: {
                //do nothing
            }
        }

        problemAsArrayList.add(termSelected);

        problem = "";
        for (int i = 0; i < problemAsArrayList.size(); i++) {
            problem = problem + problemAsArrayList.get(i);
        }

        //part 2: add term to problem and add tile name

        tileNamesOfTermsOfProblem.add(tileName);


        //part extra: print to console
        LogMessage.logMessage("Selected tile " + tileName + " which contains the term " + termSelected + ".", LogMessage.MessageType.INFO);


    }

    /**
     * Removes a term from the problem.
     *
     * @param tileName The tile name of the term.
     */
    public static void removeTermFromProblem(String tileName) {
        //part 1: get tile name and term in tile
        String tileNameLetters = "ABCDEFG";
        String termSelected = "";
        //y, x from top and from left
        /*
         * \ A B C D E F G
         * 1
         * 2
         * 3
         * 4
         * 5
         * 6
         * 7
         *
         */
        int[] tileLocation = new int[2];
        char letterOfTileName = tileName.charAt(4);
        for (int i = 0; i < 6; i++) {
            if (tileNameLetters.charAt(i) == letterOfTileName) {
                tileLocation[1] = i;
                break;
            }
        }
        tileLocation[0] = (Integer.parseInt(String.valueOf(tileName.charAt(5))) - 1);

        if (tileNamesOfTermsOfProblem.contains(tileName)) {
            problemInLaTeXAsArrayList.remove(tileNamesOfTermsOfProblem.indexOf(tileName));
            problemAsArrayList.remove(tileNamesOfTermsOfProblem.indexOf(tileName));


            tileNamesOfTermsOfProblem.remove(tileName);
        }

        problemInLaTeX = "";
        for (int i = 0; i < problemInLaTeXAsArrayList.size(); i++) {
            problemInLaTeX = problemInLaTeX + " " + problemInLaTeXAsArrayList.get(i);
        }

        problem = "";
        for (int i = 0; i < problemAsArrayList.size(); i++) {

            problem = problem + problemAsArrayList.get(i);
        }

    }

    /**
     * Determines the enemy spawn chance (per 1/60s)
     *
     * @param timeSpentInGameInMilliseconds The time spent in game. (current run, in milliseconds)
     * @return 0.005 * i if t <= 60000, (0.005 + t / 1e8) * i if 60000 < t <= 180000, (0.01 + t / 2.5e7) * i if 180000 < t where t is the time, and i is the intensifier.
     */
    public static float getEnemySpawnChance(double timeSpentInGameInMilliseconds) {
        float intensifier = 1f;
        if (insaneModeActivated) {
            intensifier = 5f;
        }
        if (timeSpentInGameInMilliseconds <= 60000) {
            return 0.005f * intensifier;
        } else if (timeSpentInGameInMilliseconds <= 180000) {
            return (float) ((0.005f + timeSpentInGameInMilliseconds / 100000000f) * intensifier);
        } else {
            return (float) ((0.01f + timeSpentInGameInMilliseconds / 25000000f) * intensifier);
        }
    }

    public static void changeSelectedEnemyColor(int steps) {
        if (steps == 1) {
            switch (selectedEnemyColor) {
                case RANDOM: {
                    selectedEnemyColor = EnemyColor.RED;
                    break;
                }
                case RED: {
                    selectedEnemyColor = EnemyColor.GREEN;
                    break;
                }
                case GREEN: {
                    selectedEnemyColor = EnemyColor.BLUE;
                    break;
                }
                case BLUE: {
                    selectedEnemyColor = EnemyColor.YELLOW;
                    break;
                }
                case YELLOW: {
                    selectedEnemyColor = EnemyColor.ORANGE;
                    break;
                }
                case ORANGE: {
                    selectedEnemyColor = EnemyColor.BLACK;
                    break;
                }
                case BLACK: {
                    selectedEnemyColor = EnemyColor.RANDOM;
                    break;
                }

            }
        } else if (steps == -1) {
            switch (selectedEnemyColor) {
                case RANDOM: {
                    selectedEnemyColor = EnemyColor.BLACK;
                    break;
                }
                case RED: {
                    selectedEnemyColor = EnemyColor.RANDOM;
                    break;
                }
                case GREEN: {
                    selectedEnemyColor = EnemyColor.RED;
                    break;
                }
                case BLUE: {
                    selectedEnemyColor = EnemyColor.GREEN;
                    break;
                }
                case YELLOW: {
                    selectedEnemyColor = EnemyColor.BLUE;
                    break;
                }
                case ORANGE: {
                    selectedEnemyColor = EnemyColor.YELLOW;
                    break;
                }
                case BLACK: {
                    selectedEnemyColor = EnemyColor.ORANGE;
                    break;
                }

            }
        } else {
            LogMessage.logMessage("Unknown step count: " + steps, LogMessage.MessageType.ERROR);
            throw new IllegalArgumentException("Unknown step count: " + steps);
        }
    }


    public static void changeScreen(Display.Screen from, Display.Screen to) {
        switch (to) {
            case MAIN_MENU_SCREEN: {
                Display.currentState = Display.State.MAIN_MENU_SCREEN;
                Display.currentScreenShown = Display.Screen.MAIN_MENU_SCREEN;

                break;
            }
            case SINGLEPLAYER_SCREEN: {
                Display.currentScreenShown = Display.Screen.SINGLEPLAYER_SCREEN;
            }
        }
    }


    /**
     * Gets the enemy color based on the settings
     *
     * @return The color the player selected in the settings
     */
    public Color getEnemyColor() {
        Random random = new Random();
        switch (selectedEnemyColor) {
            case RANDOM: {
                return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            }
            case RED: {
                return Color.RED;
            }
            case GREEN: {
                return Color.GREEN;
            }
            case BLUE: {
                return Color.BLUE;
            }
            case YELLOW: {
                return Color.YELLOW;
            }
            case ORANGE: {
                return Color.ORANGE;
            }
            case BLACK: {
                return Color.BLACK;
            }
        }
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }


    /**
     * Writes current settings to the settings.json file.
     */
    public static void updateSettingsJSONFile(){
        //enemy color setting
        JSONObject settingsObject = new JSONObject();
        settingsObject.put("selectedEnemyColor", selectedEnemyColor.toString().toLowerCase());
        settingsObject.put("soundLevel", Display.currentSoundLevel.toString().toLowerCase());


        //all of the settings
        JSONObject settingsListObject = new JSONObject();
        settingsListObject.put("settings", settingsObject);




        //actually write to settings.json
        try (FileWriter fileWriter = new FileWriter(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\variables\\settings.json")) {
            fileWriter.write(settingsListObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }


    /**
     * Gets variables from the settings.json file
     */
    public static void getVariablesFromSettingsJSONFile(){

        try {

            JSONTokener settingsJSONFileTokener = new JSONTokener(new FileReader(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\variables\\settings.json"));

            JSONObject settingsJSONObject = new JSONObject(settingsJSONFileTokener);
            JSONObject settingsListJSONObject = settingsJSONObject.getJSONObject("settings");

            //enemy color
            selectedEnemyColor = changeStringToEnemyColor(settingsListJSONObject.get("selectedEnemyColor").toString());


            //sound level
            if (settingsListJSONObject.get("soundLevel").toString().equals("on")){
                Display.currentSoundLevel = Display.SoundLevel.ON;
            } else {
                Display.currentSoundLevel = Display.SoundLevel.OFF;
            }


        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

    public static EnemyColor changeStringToEnemyColor(String selectedEnemyColorAsString){
        String[] enemyColorsAsStrings = {"random", "red", "green", "blue", "yellow", "orange", "black"};
        EnemyColor[] enemyColors = {EnemyColor.RANDOM, EnemyColor.RED, EnemyColor.GREEN, EnemyColor.BLUE, EnemyColor.YELLOW, EnemyColor.ORANGE, EnemyColor.BLACK};
        for (int i = 0; i < enemyColorsAsStrings.length; i++){
            if (selectedEnemyColorAsString.equals(enemyColorsAsStrings[i])){
                return enemyColors[i];
            }
        }
        LogMessage.logMessage("Unknown enemy color from settings.json file: " + selectedEnemyColorAsString, LogMessage.MessageType.ERROR);
        throw new IllegalArgumentException("Unknown enemy color from settings.json file: " + selectedEnemyColorAsString);
    }


    public static void changeSelectedSoundVolume(int steps){
        if (Display.currentSoundLevel == Display.SoundLevel.ON){
            Display.currentSoundLevel = Display.SoundLevel.OFF;
        } else {
            Display.currentSoundLevel = Display.SoundLevel.ON;
        }
    }

    public static void writeScoreToLocalScoresJSONFile(double score){

        JSONObject scoreObject = null;
        try {
            scoreObject = new JSONObject(new JSONTokener(new FileReader(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\variables\\local_scores.json")));
        } catch (Exception exception) {
            LogMessage.logMessage("Can't read file! (" + getStackTraceAsString(exception) + ")", LogMessage.MessageType.ERROR);
            exception.printStackTrace();
        }


        JSONObject scoresToWriteToLocalScoresJSONFile = new JSONObject();


        //check to see so first time players dont get an error
        if (scoreObject.has("scores")) {

            JSONObject scoreListObject = scoreObject.getJSONObject("scores");
            JSONArray scoreKeys = scoreListObject.names();

            for (int i = 0; i < scoreKeys.length(); i++) {
                String key = scoreKeys.getString(i);
                double value = scoreListObject.getDouble(key);
                scoresToWriteToLocalScoresJSONFile.put(key, value);
            }
        }

        scoresToWriteToLocalScoresJSONFile.put(LocalDateTime.now().toString(), score);

        JSONObject listOfScoresToWriteToLocalScoresJSONFileObject = new JSONObject();
        listOfScoresToWriteToLocalScoresJSONFileObject.put("scores", scoresToWriteToLocalScoresJSONFile);

        //actually write to settings.json
        try (FileWriter fileWriter = new FileWriter(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\variables\\local_scores.json")) {
            fileWriter.write(listOfScoresToWriteToLocalScoresJSONFileObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public static String getStackTraceAsString(Exception exception){
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }


}
