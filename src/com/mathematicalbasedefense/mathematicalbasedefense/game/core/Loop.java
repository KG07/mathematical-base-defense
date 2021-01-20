package com.mathematicalbasedefense.mathematicalbasedefense.game.core;


/**
 * Display Class for MathematicalBaseDefense
 * @author  mistertfy64
 * @version 0.0.1-alpha
 * @since   2020-11-15 or something i cant remember
 *
 */


public class Loop implements Runnable {

    private Game game;
    public static boolean isRunning;
    public static boolean gameAllowedToRender;

    private final double UPDATE_RATE = 1.0d/60.0d;
    private final double TICK_RATE = 1.0/1000.0d;
    private double nextStatTime;
    public static long framesPerSecond, updatesPerSecond;
    public static long framesElapsedSinceLaunch, updatesElapsedSinceLaunch;

    public static long framesPerSecondToDisplay, updatesPerSecondToDisplay;

    public static long freeMemory, usedMemory, totalMemory, freeMemoryInMegabytes, usedMemoryInMegabytes, totalMemoryInMegabytes;

    public double currentTimeInMilliseconds;
    public Loop(Game game){
        this.game = game;
    }



    @Override
    public void run(){
        isRunning = true;
        double accumulator = 0;
        double currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while (isRunning){
            currentTime = System.currentTimeMillis();
            currentTimeInMilliseconds = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            while (accumulator > UPDATE_RATE){
                update();
                accumulator -= UPDATE_RATE;
            }
            render();
            printAndResetStats();
        }
    }

    private void printAndResetStats(){
        if (System.currentTimeMillis() > nextStatTime){
            LogMessage.logMessage("FPS: " + framesPerSecond + " UPS: " + updatesPerSecond + " Memory Usage: " + usedMemoryInMegabytes + "MB/" + totalMemoryInMegabytes + "MB", LogMessage.MessageType.INFO);
            framesPerSecondToDisplay = framesPerSecond;
            updatesPerSecondToDisplay = updatesPerSecond;
            framesPerSecond = 0;
            updatesPerSecond = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void render(){
        if (gameAllowedToRender) {
            game.render();
            framesElapsedSinceLaunch++;
            framesPerSecond++;
        }
    }

    private void update(){
        game.update();

        freeMemory = Runtime.getRuntime().freeMemory();
        usedMemory = Runtime.getRuntime().totalMemory() - freeMemory;
        totalMemory = Runtime.getRuntime().totalMemory();

        freeMemoryInMegabytes = freeMemory / 1048576;
        usedMemoryInMegabytes = usedMemory / 1048576;
        totalMemoryInMegabytes = totalMemory / 1048576;

        updatesElapsedSinceLaunch++;
        updatesPerSecond++;
    }

    public static double getFramesPerSecond() {
        return framesPerSecond;
    }

    public double getUpdatesPerSecond(){
        return updatesPerSecond;
    }
}
