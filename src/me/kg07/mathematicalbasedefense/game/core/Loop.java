package me.kg07.mathematicalbasedefense.game.core;


/**
 * Display Class for MathematicalBaseDefense
 * @author  mistertfy64
 * @version 0.0.1-alpha
 * @since   2020-11-15 or something i cant remember
 *
 */


public class Loop implements Runnable {

    private Game game;
    private boolean isRunning;
    private final double updateRate = 1.0d/60.0d;
    private long nextStatTime;
    public static long framesPerSecond, updatesPerSecond;
    public static long framesElapsedSinceLaunch, updatesElapsedSinceLaunch;

    public static long framesPerSecondToDisplay, updatesPerSecondToDisplay;

    public static long freeMemory, usedMemory, totalMemory, freeMemoryInMegabytes, usedMemoryInMegabytes, totalMemoryInMegabytes;

    public long currentTimeMillis;
    public Loop(Game game){
        this.game = game;
    }



    @Override
    public void run(){
        isRunning = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while (isRunning){
            currentTime = System.currentTimeMillis();
            currentTimeMillis = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            while (accumulator > updateRate){
                update();
                accumulator -= updateRate;
            }
            render();
            printAndResetStats();
        }
    }

    private void printAndResetStats(){
        if (System.currentTimeMillis() > nextStatTime){
            LogMessage.logMessage("FPS: " + framesPerSecond + " UPS: " + updatesPerSecond + " M. Usage: " + usedMemoryInMegabytes + "MB/" + totalMemoryInMegabytes + "MB", LogMessage.MessageToLogType.INFO);
            framesPerSecondToDisplay = framesPerSecond;
            updatesPerSecondToDisplay = updatesPerSecond;
            framesPerSecond = 0;
            updatesPerSecond = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void render(){
        game.render();
        framesElapsedSinceLaunch++;
        framesPerSecond++;
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
