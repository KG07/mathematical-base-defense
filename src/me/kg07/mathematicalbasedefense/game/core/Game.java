package me.kg07.mathematicalbasedefense.game.core;

import me.kg07.mathematicalbasedefense.game.Display;
import me.kg07.mathematicalbasedefense.game.Enemy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
    public static float score = 0;
    public static long enemiesKilled = 0;
    public static long baseHealth = 100;
    public static long singleplayerGameStartTime = 0;
    public static long timeSpentInGameInMilliseconds = 0;

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


    public Game(int width, int height){

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

    //Called once every 0.016s (16ms)
    public void update(){
        Random random = new Random();
        timeSpentInGameInMilliseconds = System.currentTimeMillis() - singleplayerGameStartTime;
        if (Display.currentState == Display.State.SINGLEPLAYER_SCREEN) {
            if (Math.random() <= getEnemySpawnChance(timeSpentInGameInMilliseconds)) {
                enemiesSpawned++;
                Enemy enemy = new Enemy((int) 1820 * windowWidth / 1920, (int) 215 * windowHeight / 1080, (int) 150 * windowWidth / 1920, (int) 150 * windowHeight / 1080, random.nextInt(361), 10 * windowWidth / 1920, random.nextInt(100), 0, 1, 1, 1, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)), "enemyNumber" + (enemiesSpawned + 1));
                enemies.add(enemy);
                aliveEnemies.add(enemy);
            }
        }

        //Remove any offstage enemies
        for (int i = 0 ; i < aliveEnemies.size(); i++){
            if (aliveEnemies.get(i).getXPos() <= 100){
                baseHealth--;
                aliveEnemies.remove(i);
            }
        }

        //Set properties for enemies
        for (int i = 0; i < aliveEnemies.size(); i++){
            Game.aliveEnemies.get(i).valueInPoints = Game.aliveEnemies.get(i).getXPos();
        }

        //Moves enemies
        for (int i = 0; i < aliveEnemies.size(); i++){
            Game.aliveEnemies.get(i).move(random.nextInt(5));
        }

        //check if base health <= 0 show game over screen
        if (baseHealth <= 0 && Display.currentState == Display.State.SINGLEPLAYER_SCREEN){
            Display.currentState = Display.State.GAME_OVER_SCREEN;
        }
    }

    public void render(){
        display.render(this);
    }


    public static void startSingleplayerGame(){

        //reset stuff
        score = 0;
        enemiesKilled = 0;
        baseHealth = 100;
        singleplayerGameStartTime = System.currentTimeMillis();
        Display.currentState = Display.State.SINGLEPLAYER_SCREEN;
        aliveEnemies.clear();


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

        for (int r = 0; r < 7; r++){
            for (int c = 0; c < 7; c++){
                singleplayerTilesSelectionState[r][c] = false;
            }
        }

        //pick random terms for each tile
        for (int r = 0; r < 7; r++){
            for (int c = 0; c < 7; c++){
                singleplayerTileTerms[r][c] = pickRandomTerm();
            }
        }
        for (int i = 0; i < 10; i++){
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
            throw new IllegalArgumentException("Unknown Selected Term ID: " + selectedTermID);
        }
        return "Error! Contact Developers!";
    }

    /**
     * Adds a term to end of the problem.
     * @param tileName The tile's name of the term.
     */
    public static void addTermToProblem(String tileName){
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
        for (int i = 0; i < 7; i++){
            if (tileNameLetters.charAt(i) == letterOfTileName){
                tileLocation[1] = i;
                break;
            }
        }
        tileLocation[0] = (Integer.parseInt(String.valueOf(tileName.charAt(5))) - 1);
        termSelected = singleplayerTileTerms[tileLocation[0]][tileLocation[1]];

        problemInLaTeXAsArrayList.add(termSelected);

        problemInLaTeX = "";
        for (int i = 0; i < problemInLaTeXAsArrayList.size(); i++){
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
        for (int i = 0; i < problemAsArrayList.size(); i++){
            problem = problem + problemAsArrayList.get(i);
        }

        //part 2: add term to problem and add tile name

        tileNamesOfTermsOfProblem.add(tileName);


        //part extra: print to console
        LogMessage.logMessage("Selected tile " + tileName + " which contains the term " + termSelected + ".", LogMessage.MessageToLogType.INFO);


    }

    /**
     * Removes a term from the problem.
     * @param tileName The tile name of the term.
     */
    public static void removeTermFromProblem(String tileName){
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
        for (int i = 0; i < 6; i++){
            if (tileNameLetters.charAt(i) == letterOfTileName){
                tileLocation[1] = i;
                break;
            }
        }
        tileLocation[0] = (Integer.parseInt(String.valueOf(tileName.charAt(5))) - 1);

        if (tileNamesOfTermsOfProblem.contains(tileName)){
            problemInLaTeXAsArrayList.remove(tileNamesOfTermsOfProblem.indexOf(tileName));
            problemAsArrayList.remove(tileNamesOfTermsOfProblem.indexOf(tileName));




            tileNamesOfTermsOfProblem.remove(tileName);
        }

        problemInLaTeX = "";
        for (int i = 0; i < problemInLaTeXAsArrayList.size(); i++){
            problemInLaTeX = problemInLaTeX + " " + problemInLaTeXAsArrayList.get(i);
        }

        problem = "";
        for (int i = 0; i < problemAsArrayList.size(); i++){

            problem = problem + problemAsArrayList.get(i);
        }

    }

    /**
     * Determines the enemy spawn chance (per 1/60s)
     * @param timeSpentInGameInMilliseconds The time spent in game. (current run, in milliseconds)
     * @return                              0.005 * i if t <= 60000, (0.005 + t / 1e8) * i if 60000 < t <= 180000, (0.01 + t / 2.5e7) * i if 180000 < t where t is the time, and i is the intensifier.
     */
    public static float getEnemySpawnChance(long timeSpentInGameInMilliseconds){
        float intensifier = 1f;
        if (insaneModeActivated) { intensifier = 5f; } else { intensifier = 1f; };
        if (timeSpentInGameInMilliseconds <= 60000){
            return 0.005f * intensifier;
        } else if (timeSpentInGameInMilliseconds <= 180000){
            return (0.005f + timeSpentInGameInMilliseconds / 100000000f) * intensifier;
        } else {
            return (0.01f + timeSpentInGameInMilliseconds/25000000f) * intensifier;
        }
    }

    public static void changeScreen(Display.Screen screen){
        switch (screen){
            case MAIN_MENU_SCREEN: {
                Display.currentState = Display.State.MAIN_MENU_SCREEN;
                System.out.println(Display.currentState.toString());
                break;
            }
        }
    }

}
