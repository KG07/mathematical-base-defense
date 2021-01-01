package me.kg07.mathematicalbasedefense.game.core;

import com.udojava.evalex.*;

import java.math.BigDecimal;

import static me.kg07.mathematicalbasedefense.game.core.LogMessage.logMessage;

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
                    expressionOnLeftSideWithIndividualTerms[i] = problemToSend.substring(i, i+1);
                }

                //right side
                for (int i = equalSignIndex + 1, j = -1; i < problemToSend.length(); i++) {
                    j++;
                    expressionOnRightSideWithIndividualTerms[j] = problemToSend.substring(i, i+1);
                }

                //left
                for (int i = 0; i < expressionOnLeftSideWithIndividualTerms.length; i++) {
                    if (i < expressionOnLeftSideWithIndividualTerms.length - 1) {
                        if ((getTermType(expressionOnLeftSideWithIndividualTerms[i]) == Game.TermType.NUMBER || getTermType(expressionOnLeftSideWithIndividualTerms[i]) == Game.TermType.VARIABLE) && getTermType(expressionOnLeftSideWithIndividualTerms[i+1]) == Game.TermType.VARIABLE) {
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
                    System.out.println("i = " + i);
                    System.out.println(expressionOnRightSide.length() + " = l");
                    if (i < expressionOnRightSideWithIndividualTerms.length - 1) {
                        if ((getTermType(expressionOnRightSideWithIndividualTerms[i]) == Game.TermType.NUMBER || getTermType(expressionOnRightSideWithIndividualTerms[i + 1]) == Game.TermType.VARIABLE) && getTermType(expressionOnRightSideWithIndividualTerms[i+1]) == Game.TermType.VARIABLE) {
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
                        BigDecimal result = problem.eval();
                        long resultAsLong = Long.parseLong(String.valueOf(result));
                        assignValueToVariable(resultAsLong, expressionOnLeftSide);

                        //replace all selected tiles
                        for (int r = 0; r < 7; r++){
                            for (int c = 0; c < 7; c++){
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
                        float resultAsLong = Float.parseFloat(String.valueOf(result));
                        assignValueToVariable(resultAsLong, expressionOnRightSide);

                        //replace all selected tiles
                        for (int r = 0; r < 7; r++){
                            for (int c = 0; c < 7; c++){
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
                if ((getTermType(problemWithIndividualTerms[i]) == Game.TermType.NUMBER && getTermType(problemWithIndividualTerms[i + 1]) == Game.TermType.VARIABLE)) {
                    problemToEvaluate += problemWithIndividualTerms[i] + "*";
                } else {
                    problemToEvaluate += problemWithIndividualTerms[i];
                }
            } else {
                problemToEvaluate += problemWithIndividualTerms[i];
            }
        }


        Expression problem = new Expression(problemToEvaluate).with("a", BigDecimal.valueOf(Game.valueOfVariableA)).and("b", BigDecimal.valueOf(Game.valueOfVariableB)).and("c", BigDecimal.valueOf(Game.valueOfVariableC)).and("d", BigDecimal.valueOf(Game.valueOfVariableD)).and("x", BigDecimal.valueOf(Game.valueOfVariableX)).and("y", BigDecimal.valueOf(Game.valueOfVariableY)).and("z", BigDecimal.valueOf(Game.valueOfVariableZ));
        BigDecimal result = problem.eval();
        float resultAsFloat = result.floatValue();
        logMessage("The problem to evaluate is " + problemToEvaluate + " and the result is " + resultAsFloat, LogMessage.MessageToLogType.INFO);

        //check if at least one enemy matches the problem to evaluate, if so, kill the oldest enemy.
        for (int i = 0; i < Game.aliveEnemies.size(); i++){
            if (resultAsFloat == (float) Game.aliveEnemies.get(i).getRequestedValue()){

                //increase stats
                Game.enemiesKilled++;
                Game.score += Game.aliveEnemies.get(i).getValueInPoints();

                //kill enemy
                Game.aliveEnemies.remove(i);

                //replace all selected tiles
                for (int r = 0; r < 7; r++){
                    for (int c = 0; c < 7; c++){
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



                break;
            }
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
            case "x":
            case "y":
            case "z":
                return Game.TermType.VARIABLE;
            default:
                throw new IllegalArgumentException("Unknown term type: " + term);
        }
    }

    /**
     * Assigns a value to a variable.
     * @param value      The value
     * @param variable   The variable
     */
    private static void assignValueToVariable(float value, String variable){
        switch (variable){
            case "a":{
                Game.valueOfVariableA = value;
                break;
            }
            case "b":{
                Game.valueOfVariableB = value;
                break;
            }
            case "c":{
                Game.valueOfVariableC = value;
                break;
            }
            case "d":{
                Game.valueOfVariableD = value;
                break;
            }
            case "n":{
                Game.valueOfVariableN = value;
                break;
            }
            case "x":{
                Game.valueOfVariableX = value;
                break;
            }
            case "y":{
                Game.valueOfVariableY = value;
                break;
            }
            case "z":{
                Game.valueOfVariableZ = value;
                break;
            }
        }
        logMessage("Assigned value " + value + " to variable " + variable + ".", LogMessage.MessageToLogType.INFO);
    }
}