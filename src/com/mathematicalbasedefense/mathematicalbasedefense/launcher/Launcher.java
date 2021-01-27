package com.mathematicalbasedefense.mathematicalbasedefense.launcher;

/*
 * Mathematical Base Defense
 *
 * This project is a submission to NSC 2021, which is ran by the National Science and Technology Development Agency (NSTDA).
 *
 * Stuff to acknowledge:
 * -> When declaring new UI Components, put width and height according to 1920x1080 screen resolution.
 *
 */

import com.mathematicalbasedefense.mathematicalbasedefense.game.core.ErrorWindow;

import java.io.*;
import java.util.ArrayList;

import com.mathematicalbasedefense.mathematicalbasedefense.game.core.LogMessage;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.imageio.ImageIO;

public class Launcher {
    public static void main(String[] args) {
        initializeFiles();

        if (!getVersionNumberFromMetadataJSONFile().equals("0.3.0")){
            updateFilesToLatestVersion();
        }



        writeVersionNumberToMetadataJSONFile("0.3.0");
        new LauncherWindow();
    }

    public static void initializeFiles(){

        //local appdata folder
        File localAppDataFolder = new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense");
        if(!localAppDataFolder.exists()) {
            new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense").mkdirs();
        }

        // game assets folder (in local appdata folder)
        File gameAssetsFolder = new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\assets");
        if(!gameAssetsFolder.exists()) {
            new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\assets").mkdirs();
        }

        // game variables folder (in local appdata folder)
        File gameVariablesFolder = new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\variables");
        if(!gameVariablesFolder.exists()) {
            new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\variables").mkdirs();
        }

        // game assets folder (in local appdata folder)
        File launcherAssetsFolder = new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\launcher\\assets");
        if(!launcherAssetsFolder.exists()) {
            new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\launcher\\assets").mkdirs();
        }


        ArrayList<String> namesOfLauncherFilesToCheck = new ArrayList<String>();
        ArrayList<String> namesOfGameFilesToCheck = new ArrayList<String>();


        

        try {
            for (int i = 0; i < namesOfLauncherFilesToCheck.size(); i++) {
                File fileToCheck = new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\" + namesOfLauncherFilesToCheck.get(i));
                LogMessage.logMessage("Checking for " + namesOfLauncherFilesToCheck.get(i) + " (" + (i+1) + "/" + namesOfLauncherFilesToCheck.size() + ")", LogMessage.MessageType.INFO);
                if(fileToCheck.exists()) {
                    LogMessage.logMessage("Launcher File " + namesOfLauncherFilesToCheck.get(i) + " exists!", LogMessage.MessageType.INFO);
                } else {
                    LogMessage.logMessage("Launcher File " + namesOfLauncherFilesToCheck.get(i) + " does not exist! Copying file " + namesOfLauncherFilesToCheck.get(i) + " from src...", LogMessage.MessageType.INFO);
                    copyAndPasteFile("src/com/mathematicalbasedefense/mathematicalbasedefense/" + namesOfLauncherFilesToCheck.get(i).replace("\\", "/"), System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\" + namesOfLauncherFilesToCheck.get(i));
                }
            }
        } catch (Exception exception){
            StringWriter errors = new StringWriter();
            exception.printStackTrace(new PrintWriter(errors));
            new ErrorWindow(errors.toString());
        }



        //game files
        //fonts
        namesOfGameFilesToCheck.add("game\\assets\\fonts\\computermodern.ttf");
        //images and imagebuttons
        namesOfGameFilesToCheck.add("game\\assets\\images\\base.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\base_health_icon.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\credits_back_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\credits_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\credits_information-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\enemies_killed_icon.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\logo.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\mathematical_base_defense_logo.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\multiplayer_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\national_software_contest_logo.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\quit_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\retry_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\return_to_title_screen_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\school_logo.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\send_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\settings_audio_section_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\settings_back_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\settings_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\settings_left_arrow_button.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\settings_online_section_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\settings_right_arrow_button.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\settings_video_section_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\shop_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\singleplayer_button-en.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\time_spent_in_game_in_milliseconds_icon.png");
        namesOfGameFilesToCheck.add("game\\assets\\images\\title_screen_logo.png");
        //variables
        namesOfGameFilesToCheck.add("game\\variables\\local_scores.json");
        namesOfGameFilesToCheck.add("game\\variables\\settings.json");
        //sounds
        namesOfGameFilesToCheck.add("game\\assets\\sounds\\enemy_kill.wav");
        //metadata file
        namesOfGameFilesToCheck.add("game\\metadata.json");
        //logs file
        namesOfGameFilesToCheck.add("game\\logs.txt");


        try {
            for (int i = 0; i < namesOfGameFilesToCheck.size(); i++) {
                File fileToCheck = new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\" + namesOfGameFilesToCheck.get(i));
                LogMessage.logMessage("Checking for " + namesOfGameFilesToCheck.get(i) + " (" + (i+1) + "/" + namesOfGameFilesToCheck.size() + ")", LogMessage.MessageType.INFO);
                if(fileToCheck.exists()) {
                    LogMessage.logMessage("Game File " + namesOfGameFilesToCheck.get(i) + " exists!", LogMessage.MessageType.INFO);
                } else {
                    LogMessage.logMessage("Game File " + namesOfGameFilesToCheck.get(i) + " does not exist! Copying file " + namesOfGameFilesToCheck.get(i) + " from src...", LogMessage.MessageType.INFO);
                    copyAndPasteFile("src/com/mathematicalbasedefense/mathematicalbasedefense/" + namesOfGameFilesToCheck.get(i).replace("\\", "/"), System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\" + namesOfGameFilesToCheck.get(i));
                }
            }
        } catch (Exception exception){
            StringWriter stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            new ErrorWindow(stringWriter.toString());
        }
    }


    public static void copyAndPasteFile(String from, String to) {
        File source = new File(from);
        File destination = new File(to);
        try {
            FileUtils.copyFile(source, destination);
        } catch (Exception exception) {
            LogMessage.logMessage("File " + from + " from src not found! (" + getStackTraceAsString(exception) + ") ", LogMessage.MessageType.ERROR);
            exception.printStackTrace();

        }
    }


    public static void writeVersionNumberToMetadataJSONFile(String versionNumber){
        JSONObject metadataObject = new JSONObject();
        metadataObject.put("version", versionNumber);


        //all of the metadata
        JSONObject metadataListObject = new JSONObject();
        metadataListObject.put("metadata", metadataObject);

        //actually write to settings.json
        try (FileWriter fileWriter = new FileWriter(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\metadata.json")) {
            fileWriter.write(metadataListObject.toString());
            fileWriter.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }




    }


    public static String getVersionNumberFromMetadataJSONFile(){
        JSONTokener settingsJSONFileTokener = null;
        try {
            settingsJSONFileTokener = new JSONTokener(new FileReader(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\game\\metadata.json"));
            JSONObject metadataJSONObject = new JSONObject(settingsJSONFileTokener);
            JSONObject metadataListJSONObject = metadataJSONObject.getJSONObject("metadata");
            return metadataListJSONObject.getString("version");
        } catch (Exception exception) {
            StringWriter stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.toString();
        }



        //enemy color
    }




    public static void updateFilesToLatestVersion(){
        LogMessage.logMessage("Versions do not match! Current version is 0.3.0 but metadata.json's version key is " + getVersionNumberFromMetadataJSONFile(), LogMessage.MessageType.INFO);
        //actually update files


        ArrayList<String> namesOfUpdatedFilesToCheck = new ArrayList<String>();
        namesOfUpdatedFilesToCheck.add("game\\assets\\images\\mathematical_base_defense_logo.png");




        try {
            for (int i = 0; i < namesOfUpdatedFilesToCheck.size(); i++) {
                File fileToCheck = new File(System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\" + namesOfUpdatedFilesToCheck.get(i));
                LogMessage.logMessage("Checking for " + namesOfUpdatedFilesToCheck.get(i) + " (" + (i+1) + "/" + namesOfUpdatedFilesToCheck.size() + ")", LogMessage.MessageType.INFO);
                LogMessage.logMessage("Updated File " + namesOfUpdatedFilesToCheck.get(i) + " does not exist! Copying file " + namesOfUpdatedFilesToCheck.get(i) + " from src...", LogMessage.MessageType.INFO);
                copyAndPasteFile("src/com/mathematicalbasedefense/mathematicalbasedefense/" + namesOfUpdatedFilesToCheck.get(i).replace("\\", "/"), System.getenv("LOCALAPPDATA") + "\\MathematicalBaseDefense\\" + namesOfUpdatedFilesToCheck.get(i));
            }
        } catch (Exception exception){
            StringWriter stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            new ErrorWindow(stringWriter.toString());
        }
    
    
    }




    //wow this actually works lol
    public static String getStackTraceAsString(Exception exception){
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

}
