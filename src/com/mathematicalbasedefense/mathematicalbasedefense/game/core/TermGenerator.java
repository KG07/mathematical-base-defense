package com.mathematicalbasedefense.mathematicalbasedefense.game.core;

import java.util.Random;

public class TermGenerator {

    public void generateTileTerm() {

    }


    public static String generateEnemyTerm() {
        String[] letters = {"a", "b", "c", "d", "n", "x", "y", "z"};
        Random random = new Random();
        double roll = Math.random();
        if (roll > 0.9) {
            return String.valueOf(random.nextInt(101)) + letters[random.nextInt(8)];
        } else if (roll > 0.8) {
            int subroll = random.nextInt(8);
            switch (subroll) {
                case 0: {
                    return String.valueOf(random.nextInt(101) + Game.valueOfVariableA);
                }
                case 1: {
                    return String.valueOf(random.nextInt(101) + Game.valueOfVariableB);
                }
                case 2: {
                    return String.valueOf(random.nextInt(101) + Game.valueOfVariableC);
                }
                case 3: {
                    return String.valueOf(random.nextInt(101) + Game.valueOfVariableD);
                }
                case 4: {
                    return String.valueOf(random.nextInt(101) + Game.valueOfVariableN);
                }
                case 5: {
                    return String.valueOf(random.nextInt(101) + Game.valueOfVariableX);
                }
                case 6: {
                    return String.valueOf(random.nextInt(101) + Game.valueOfVariableY);
                }
                case 7: {
                    return String.valueOf(random.nextInt(101) + Game.valueOfVariableZ);
                }
            }
        } else {
            return String.valueOf(random.nextInt(101));
        }
        return String.valueOf(random.nextInt(101));
    }


}
