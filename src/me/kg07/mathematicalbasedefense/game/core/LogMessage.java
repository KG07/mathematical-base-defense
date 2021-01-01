package me.kg07.mathematicalbasedefense.game.core;

import java.time.LocalTime;

public class LogMessage {

    public enum MessageToLogType {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
    }


    public static void logMessage(String message, MessageToLogType messageToLogType){
        switch (messageToLogType){
            case DEBUG: {
                System.out.println("[" + LocalTime.now() + " DEBUG] " + message);
                break;
            }
            case INFO: {
                System.out.println("[" + LocalTime.now() + " INFO] " + message);
                break;
            }
            case WARNING: {
                System.out.println("[" + LocalTime.now() + " WARNING] " + message);
                break;
            }
            case ERROR: {
                System.out.println("[" + LocalTime.now() + " ERROR] " + message);
                break;
            }
        }

    }
}
