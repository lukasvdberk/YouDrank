package com.example.youdrank.controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.youdrank.models.Settings;

public class SettingsController {
    Context context;
    SharedPreferences sharedpreferences;

    public SettingsController(Context context) {
        this.context = context;
        sharedpreferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
    }

    public void saveSettings(Settings settingsToSave) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt("waterInTakePerDay", settingsToSave.getWaterIntakeInMLGoalPerDay());
        editor.apply();
    }

    public Settings getSettings() {
        return new Settings(sharedpreferences.getInt("waterInTakePerDay", 2000));
    }
}
