package com.mathematicalbasedefense.mathematicalbasedefense.game.core;

import com.mathematicalbasedefense.mathematicalbasedefense.game.Display;
import com.mathematicalbasedefense.mathematicalbasedefense.game.entities.Blood;
import com.udojava.evalex.*;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Random;


public class Evaluator {

    /**
     * Sends the problem problem.
     *
     * @param problemToSend The problem to send.
     */
    public static void sendProblem(String problemToSend) {

        String problemToEvaluate = "";
        String[] problemWithIndividualTerms = new String[problemToSend.length()];

        //turn characters into elements
        for (int i = 0; i < problemToSend.length(); i++) {
            problemWithIndividualTerms[i] = String.valueOf(problemToSend.charAt(i));
        }


        //check if problem is invalid or a variable assignment problem
        if (problemToSend.contains("=")) {
            int numberOfEqualSigns = 0;
            for (int i = 0; i < problemWithIndividualTerms.length; i++) {
                if (problemWithIndividualTerms[i].equals("=")) {
                    numberOfEqualSigns++;
                }
            }
            //assign number to variable if equal sign occurrences == 1
            if (numberOfEqualSigns == 1) {


                int equalSignIndex = problemToSend.indexOf("=");

                String expressionOnLeftSide = problemToSend.substring(0, equalSignIndex);
                String expressionOnRightSide = problemToSend.substring(equalSignIndex + 1, problemToSend.length());

                String[] expressionOnLeftSideWithIndividualTerms = new String[equalSignIndex];
                String[] expressionOnRightSideWithIndividualTerms = new String[problemToSend.length() - equalSignIndex - 1];

                //set expressions to evaluate to ""
                String expressionOnLeftSideToEvaluate = "";
                String expressionOnRightSideToEvaluate = "";

                //set stuff into string array
                //left side

                System.out.println(expressionOnLeftSide.length());

                for (int i = 0; i < expressionOnLeftSide.length(); i++) {
                    expressionOnLeftSideWithIndividualTerms[i] = problemToSend.substring(i, i + 1);
                }

                //right side
                for (int i = equalSignIndex + 1, j = -1; i < problemToSend.length(); i++) {
                    j++;
                    expressionOnRightSideWithIndividualTerms[j] = problemToSend.substring(i, i + 1);
                }

                //left
                for (int i = 0; i < expressionOnLeftSideWithIndividualTerms.length; i++) {
                    if (i < expressionOnLeftSideWithIndividualTerms.length - 1) {
                        if ((getTermType(expressionOnLeftSideWithIndividualTerms[i]) == Game.TermType.NUMBER || getTermType(expressionOnLeftSideWithIndividualTerms[i]) == Game.TermType.VARIABLE) && getTermType(expressionOnLeftSideWithIndividualTerms[i + 1]) == Game.TermType.VARIABLE) {
                            expressionOnLeftSideToEvaluate += expressionOnLeftSideWithIndividualTerms[i] + "*";
                        } else {
                            expressionOnLeftSideToEvaluate += expressionOnLeftSideWithIndividualTerms[i];
                        }
                    } else {
                        expressionOnLeftSideToEvaluate += expressionOnLeftSideWithIndividualTerms[i];
                    }
                }

                //right
                for (int i = 0; i < expressionOnRightSideWithIndividualTerms.length; i++) {
                    if (i < expressionOnRightSideWithIndividualTerms.length - 1) {
                        if ((getTermType(expressionOnRightSideWithIndividualTerms[i]) == Game.TermType.NUMBER || getTermType(expressionOnRightSideWithIndividualTerms[i + 1]) == Game.TermType.VARIABLE) && getTermType(expressionOnRightSideWithIndividualTerms[i + 1]) == Game.TermType.VARIABLE) {
                            expressionOnRightSideToEvaluate += expressionOnRightSideWithIndividualTerms[i] + "*";
                        } else {
                            expressionOnRightSideToEvaluate += expressionOnRightSideWithIndividualTerms[i];
                        }
                    } else {
                        expressionOnRightSideToEvaluate += expressionOnRightSideWithIndividualTerms[i];
                    }
                }

                //if there is only 1 term on either side, and that term is a variable --> assign value of the other side to the variable.
                //but if both sides are the same, skip.

                if (!expressionOnLeftSide.equals(expressionOnRightSide)) {
                    if (expressionOnLeftSide.length() == 1 && getTermType(expressionOnLeftSide.substring(0, 1)) == Game.TermType.VARIABLE && !expressionOnRightSide.contains(expressionOnLeftSide.substring(0, 1))) {

                        Expression problem = new Expression(expressionOnRightSideToEvaluate).with("a", BigDecimal.valueOf(Game.valueOfVariableA)).and("b", BigDecimal.valueOf(Game.valueOfVariableB)).and("c", BigDecimal.valueOf(Game.valueOfVariableC)).and("d", BigDecimal.valueOf(Game.valueOfVariableD)).and("x", BigDecimal.valueOf(Game.valueOfVariableX)).and("y", BigDecimal.valueOf(Game.valueOfVariableY)).and("z", BigDecimal.valueOf(Game.valueOfVariableZ));
                        System.out.println(problem);
                        BigDecimal result = problem.eval();
                        float resultAsFloat = Float.parseFloat(String.valueOf(result));
                        assignValueToVariable(resultAsFloat, expressionOnLeftSide);

                        //replace all selected tiles
                        for (int r = 0; r < 7; r++) {
                            for (int c = 0; c < 7; c++) {
                                if (Game.singleplayerTilesSelectionState[c][r]) {
                                    Game.singleplayerTileTerms[c][r] = Game.pickRandomTerm();
                                    Game.singleplayerTilesSelectionState[c][r] = false;
                                }
                            }
                        }

                        //clear problem box and other variables
                        Game.problem = "";
                        Game.problemInLaTeX = "";
                        Game.problemAsArrayList.clear();
                        Game.problemInLaTeXAsArrayList.clear();
                        Game.tileNamesOfTermsOfProblem.clear();

                        return;


                    } else if (expressionOnRightSide.length() == 1 && (getTermType(expressionOnRightSide.substring(0, 1)) == Game.TermType.VARIABLE) && !expressionOnLeftSide.contains(expressionOnRightSide.substring(0, 1))) {
                        Expression problem = new Expression(expressionOnLeftSideToEvaluate).with("a", BigDecimal.valueOf(Game.valueOfVariableA)).and("b", BigDecimal.valueOf(Game.valueOfVariableB)).and("c", BigDecimal.valueOf(Game.valueOfVariableC)).and("d", BigDecimal.valueOf(Game.valueOfVariableD)).and("x", BigDecimal.valueOf(Game.valueOfVariableX)).and("y", BigDecimal.valueOf(Game.valueOfVariableY)).and("z", BigDecimal.valueOf(Game.valueOfVariableZ));
                        BigDecimal result = problem.eval();
                        float resultAsFloat = Float.parseFloat(String.valueOf(result));
                        assignValueToVariable(resultAsFloat, expressionOnRightSide);

                        //replace all selected tiles
                        for (int r = 0; r < 7; r++) {
                            for (int c = 0; c < 7; c++) {
                                if (Game.singleplayerTilesSelectionState[c][r]) {
                                    Game.singleplayerTileTerms[c][r] = Game.pickRandomTerm();
                                    Game.singleplayerTilesSelectionState[c][r] = false;
                                }
                            }
                        }

                        //clear problem box and other variables
                        Game.problem = "";
                        Game.problemInLaTeX = "";
                        Game.problemAsArrayList.clear();
                        Game.problemInLaTeXAsArrayList.clear();
                        Game.tileNamesOfTermsOfProblem.clear();


                        return;
                    }
                }
                return;


            }
            //dont check if equal signs occurrences >= 2
            if (numberOfEqualSigns >= 2) {
                return;
            }
        }

        problemToEvaluate = "";
        //"unsimplify" equation so EvalEx doesn't throw an error
        for (int i = 0; i < problemWithIndividualTerms.length; i++) {
            if (i < problemWithIndividualTerms.length - 1) {
                if ((getTermType(problemWithIndividualTerms[i]) == Game.TermType.NUMBER || getTermType(problemWithIndividualTerms[i]) == Game.TermType.VARIABLE)&& getTermType(problemWithIndividualTerms[i + 1]) == Game.TermType.VARIABLE) {
                    problemToEvaluate += problemWithIndividualTerms[i] + "*";
                } else {
                    problemToEvaluate += problemWithIndividualTerms[i];
                }
            } else {
                problemToEvaluate += problemWithIndividualTerms[i];
            }
        }


        Expression problem = new Expression(problemToEvaluate).with("a", BigDecimal.valueOf(Game.valueOfVariableA)).and("b", BigDecimal.valueOf(Game.valueOfVariableB)).and("c", BigDecimal.valueOf(Game.valueOfVariableC)).and("d", BigDecimal.valueOf(Game.valueOfVariableD)).and("n", BigDecimal.valueOf(Game.valueOfVariableN)).and("x", BigDecimal.valueOf(Game.valueOfVariableX)).and("y", BigDecimal.valueOf(Game.valueOfVariableY)).and("z", BigDecimal.valueOf(Game.valueOfVariableZ));
        BigDecimal result = problem.eval();
        float resultAsFloat = result.floatValue();
        LogMessage.logMessage("The problem to evaluate is " + problemToEvaluate + " and the result is " + resultAsFloat, LogMessage.MessageType.INFO);

        //check if at least one enemy matches the problem to evaluate, if so, kill the oldest enemy.
        boolean answerAccepted = false;
        for (int i = 0; i < Game.aliveEnemies.size(); i++) {
            if (resultAsFloat == calculateEnemyRequestedValue(Game.aliveEnemies.get(i).getRequestedValue())) {

                //increase stats
                Game.enemiesKilled++;
                Game.score += Math.round(Game.aliveEnemies.get(i).getValueInPoints() * getTermLengthScoreMultiplier(problemToEvaluate.length()) * getPositionScoreMultiplier(Game.aliveEnemies.get(i).getXPos()) * 1e3d) / 1e3d;
                Game.recentScoreIncrease = Math.round(Game.aliveEnemies.get(i).getValueInPoints() * getTermLengthScoreMultiplier(problemToEvaluate.length()) * getPositionScoreMultiplier(Game.aliveEnemies.get(i).getXPos()) * 1e3d) / 1e3d;

                //play sound
                Display.playSound(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\assets\\sounds\\enemy_kill.wav");

                //add blood
                /*
                Random random = new Random();
                for (int b = 0; b < random.nextInt(6); b++){
                    Blood blood = new Blood((Game.aliveEnemies.get(i).getXPos() + Game.aliveEnemies.get(i).getWidth()) / 2, (Game.aliveEnemies.get(i).getXPos() + Game.aliveEnemies.get(i).getHeight()) / 2, 150 * Display.windowWidth/1920, 150 * Display.windowHeight/1080, Game.aliveEnemies.get(i).getColor());
                }

                 */

                //kill enemy
                Game.aliveEnemies.remove(i);


                //make transition
                Display.gradualFillThenFadeInProblemBox(Color.GREEN);


                //replace all selected tiles
                for (int r = 0; r < 7; r++) {
                    for (int c = 0; c < 7; c++) {
                        if (Game.singleplayerTilesSelectionState[c][r]) {
                            Game.singleplayerTileTerms[c][r] = Game.pickRandomTerm();
                            Game.singleplayerTilesSelectionState[c][r] = false;
                        }
                    }
                }

                //clear problem box and other variables
                Game.problem = "";
                Game.problemInLaTeX = "";
                Game.problemAsArrayList.clear();
                Game.problemInLaTeXAsArrayList.clear();
                Game.tileNamesOfTermsOfProblem.clear();

                answerAccepted = true;

                break;
            }

        }
        if (!answerAccepted) {
            Display.gradualFillThenFadeInProblemBox(Color.RED);
        }
    }

    /**
     * Gets the "term type" of the term.
     *
     * @param term The term.
     * @return "number" if the term is a number, "symbol" if the term is a arithmetic symbol. "variable" if the term is a letter (variable).
     */
    private static Game.TermType getTermType(String term) {
        switch (term) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                return Game.TermType.NUMBER;
            case "+":
            case "-":
            case "*":
            case "/":
                return Game.TermType.SYMBOL;
            case "a":
            case "b":
            case "c":
            case "d":
            case "n":
            case "x":
            case "y":
            case "z":
                return Game.TermType.VARIABLE;
            default:
                LogMessage.logMessage("Unknown term type: " + term, LogMessage.MessageType.ERROR);
                throw new IllegalArgumentException("Unknown term type: " + term);
        }
    }

    /**
     * Assigns a value to a variable.
     *
     * @param value    The value
     * @param variable The variable
     */
    private static void assignValueToVariable(float value, String variable) {
        switch (variable) {
            case "a": {
                Game.valueOfVariableA = value;
                break;
            }
            case "b": {
                Game.valueOfVariableB = value;
                break;
            }
            case "c": {
                Game.valueOfVariableC = value;
                break;
            }
            case "d": {
                Game.valueOfVariableD = value;
                break;
            }
            case "n": {
                Game.valueOfVariableN = value;
                break;
            }
            case "x": {
                Game.valueOfVariableX = value;
                break;
            }
            case "y": {
                Game.valueOfVariableY = value;
                break;
            }
            case "z": {
                Game.valueOfVariableZ = value;
                break;
            }
        }
        LogMessage.logMessage("Assigned value " + value + " to variable " + variable + ".", LogMessage.MessageType.INFO);
    }

    /**
     * Gets the score multiplier according to the length of the problem.
     *
     * @param numberOfTerms The problem's length.
     * @return 1 if 1 <= numberOfTerms <= 5, 1.1 if 6 <= numberOfTerms <= 9, 1.2 if 10 <= numberOfTerms <= 12, 1.3 if 13 <= numberOfTerms <= 14, 1.4+((numberOfTerms-15)/10) if 15 <= numberOfTerms <= 40, 4+(numberOfTerms-41) if 41 <= numberOfTerms
     */
    private static double getTermLengthScoreMultiplier(int numberOfTerms) {
        if (1 <= numberOfTerms && numberOfTerms <= 5) {
            return 1d;
        } else if (6 <= numberOfTerms && numberOfTerms <= 9) {
            return 1.1d;
        } else if (10 <= numberOfTerms && numberOfTerms <= 12) {
            return 1.2d;
        } else if (13 <= numberOfTerms && numberOfTerms <= 14) {
            return 1.3d;
        } else if (15 <= numberOfTerms && numberOfTerms <= 40) {
            return (double) (1.4 + ((numberOfTerms - 15) / 10));
        } else {
            return (double) 4 + (numberOfTerms - 41);
        }
    }

    /**
     * Gets the score multiplier according to the position of the enemy when killed.
     *
     * @param xPosition The X Position.
     * @return whatever im not going to write this out you can tell by the code down there
     */
    private static double getPositionScoreMultiplier(int xPosition) {
        if (1557 <= xPosition) {
            return 1.5d;
        } else if (1384 <= xPosition) {
            return 1.4d;
        } else if (1211 <= xPosition) {
            return 1.3d;
        } else if (1038 <= xPosition) {
            return 1.2d;
        } else if (865 <= xPosition) {
            return 1.1d;
        } else {
            return 1d;
        }
    }


    /**
     * Calculates the requested value for enemies with
     * @param requestedValue
     * @return
     */
    public static float calculateEnemyRequestedValue(String requestedValue) {
        String[] variables = {"a", "b", "c", "d", "n", "x", "y", "z"};
        int[] variableOccurrences = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < requestedValue.length(); i++) {
            for (int j = 0; j < variables.length; j++) {
                if (Character.toString(requestedValue.charAt(i)).equals((variables[j]))) {
                    variableOccurrences[j]++;
                }
            }
        }
        String coefficientAsString = "";
        int coefficient;
        for (int i = 0; i < requestedValue.length(); i++){
            if (Character.isDigit(requestedValue.charAt(i))){
                coefficientAsString = coefficientAsString + Character.toString(requestedValue.charAt(i));
            } else {
                break;
            }
        }
        coefficient = Integer.parseInt(coefficientAsString);
        return (float) (coefficient * (Math.pow(Game.valueOfVariableA, variableOccurrences[0])) * (Math.pow(Game.valueOfVariableB, variableOccurrences[1])) *  (Math.pow(Game.valueOfVariableC, variableOccurrences[2])) * (Math.pow(Game.valueOfVariableD, variableOccurrences[3])) * (Math.pow(Game.valueOfVariableN, variableOccurrences[4])) * (Math.pow(Game.valueOfVariableX, variableOccurrences[5])) * (Math.pow(Game.valueOfVariableY, variableOccurrences[6])) * (Math.pow(Game.valueOfVariableZ, variableOccurrences[7])));
    }

    public static int[] getVariableOccurrences(String problemToEvaluate){
        String[] variables = {"a", "b", "c", "d", "n", "x", "y", "z"};
        int[] variableOccurrences = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < problemToEvaluate.length(); i++) {
            for (int j = 0; j < variables.length; j++) {
                if (Character.toString(problemToEvaluate.charAt(i)).equals((variables[j]))) {
                    variableOccurrences[j]++;
                }
            }
        }
        return variableOccurrences;
    }
}