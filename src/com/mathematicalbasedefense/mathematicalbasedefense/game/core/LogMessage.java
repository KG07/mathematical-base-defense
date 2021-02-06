package com.mathematicalbasedefense.mathematicalbasedefense.game.core;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LogMessage {

    public enum MessageType {
        DEBUG,
        INFO,
        WARNING,
        ERROR,
    }


    public static void logMessage(String message, MessageType messageToLogType){
        switch (messageToLogType){
            case DEBUG: {
                System.out.println("[" + LocalDateTime.now() + " DEBUG] " + message);
                break;
            }
            case INFO: {
                System.out.println("[" + LocalDateTime.now() + " INFO] " + message);
                break;
            }
            case WARNING: {
                System.out.println("[" + LocalDateTime.now() + " WARNING] " + message);
                break;
            }
            case ERROR: {
                System.out.println("[" + LocalDateTime.now() + " ERROR] " + message);
                try {
                    FileWriter fileWriter = new FileWriter(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\logs.txt", true);
                    fileWriter.write("[" + LocalDateTime.now() + " ERROR] " + message + "\n");
                    fileWriter.close();
                } catch (Exception exception){
                    exception.printStackTrace();
                }
                break;
            }
        }

    }
}
