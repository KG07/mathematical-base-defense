package com.mathematicalbasedefense.mathematicalbasedefense.game.userinterface.animations;

import java.awt.*;

public class Fade {
    /**
     *
     * @param timeToFadeIn
     * @param timeToStay
     * @param timeToFadeOut
     * @param step
     * @param color
     */

    public static float fadeEffectCurrentOpacity = 0.0f;
    public static boolean fadeEffectActive = false;

    public static void fadeIn(long delayPerStep, float step, int repetitions, float startOpacity, Color color){
        fadeEffectActive = true;
        fadeEffectCurrentOpacity = startOpacity;
        for (int i = 0; i < repetitions; i++){
            if (fadeEffectCurrentOpacity > 1f){
                fadeEffectCurrentOpacity = 1f;
            } else if (fadeEffectCurrentOpacity < 0f){
                fadeEffectCurrentOpacity = 0f;
            }
            try {
                Thread.sleep(delayPerStep);
                fadeEffectCurrentOpacity += step;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            if (fadeEffectCurrentOpacity > 1f){
                fadeEffectCurrentOpacity = 1f;
            } else if (fadeEffectCurrentOpacity < 0f){
                fadeEffectCurrentOpacity = 0f;
            }
        }
        fadeEffectActive = false;
    }

    public static void fadeOut(long delayPerStep, float step, int repetitions, float startOpacity, Color color){
        fadeEffectActive = true;
        fadeEffectCurrentOpacity = startOpacity;
        for (int i = 0; i < repetitions; i++){
            if (fadeEffectCurrentOpacity > 1f){
                fadeEffectCurrentOpacity = 1f;
            } else if (fadeEffectCurrentOpacity < 0f){
                fadeEffectCurrentOpacity = 0f;
            }
            try {
                Thread.sleep(delayPerStep);
                fadeEffectCurrentOpacity -= step;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            if (fadeEffectCurrentOpacity > 1f){
                fadeEffectCurrentOpacity = 1f;
            } else if (fadeEffectCurrentOpacity < 0f){
                fadeEffectCurrentOpacity = 0f;
            }
        }
        fadeEffectActive = false;
    }
}
