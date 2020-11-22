package me.kg07.mathematicalbasedefense.game;


/*
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
    private double framesPerSecond, updatesPerSecond;

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
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator = ++lastRenderTimeInSeconds;
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
            System.out.println("FPS: " + framesPerSecond + " UPS: " + updatesPerSecond);
            framesPerSecond = 0;
            updatesPerSecond = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void render(){
        game.render();
        framesPerSecond++;
    }

    private void update(){
        game.update();
        updatesPerSecond++;
    }

    public double getFramesPerSecond() {
        return framesPerSecond;
    }

    public double getUpdatesPerSecond(){
        return updatesPerSecond;
    }
}
