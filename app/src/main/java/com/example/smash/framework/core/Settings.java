package com.example.smash.framework.core;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Settings {
    public static boolean soundEnabled = true;
    public static int[] highscores = new int[] { 100, 80, 50, 30, 10};

    public static void load(FileIO file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(
                    file.readFile(Constant.PATH_SETTING_MR_NORM)
            ));
            Settings.soundEnabled = Boolean.parseBoolean(
                    reader.readLine()
            );
            for(int i = 0; i<5; i++) {
                Settings.highscores[i] = Integer.parseInt(
                        reader.readLine()
                );
            }
        } catch (IOException e) {
            Log.d("Setting#load", e.getMessage());
        } catch (NumberFormatException e) {
            Log.d("Setting#load", e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {

            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    files.writeFile(Constant.PATH_SETTING_MR_NORM)
            ));
            writer.write(Boolean.toString(Settings.soundEnabled));
            for (int i = 0;i < 5;i++) {
                writer.write(Integer.toString(Settings.highscores[i]));
            }
        } catch (IOException e) {
            Log.d("Setting#save", e.getMessage());
        } finally {
            try {
                if (writer !=null) {
                    writer.close();
                }
            } catch (IOException e) {

            }
        }
    }

    public static void addScore(int score) {
        int maxHighScoreLength = Settings.highscores.length;
        for (int i = 0;i < maxHighScoreLength; i++) {
            if (Settings.highscores[i] < score) {
                for (int j = maxHighScoreLength - 1;j > i;j--) {
                    Settings.highscores[j] = Settings.highscores[j - 1];
                }
                Settings.highscores[i] = score;
                break;
            }
        }
    }
}
