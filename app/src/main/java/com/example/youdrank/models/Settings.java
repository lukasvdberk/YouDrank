package com.example.youdrank.models;

public class Settings {
    private int waterIntakeInMLGoalPerDay;

    public Settings(int waterIntakeInMLGoalPerDay) {
        this.waterIntakeInMLGoalPerDay = waterIntakeInMLGoalPerDay;
    }

    public int getWaterIntakeInMLGoalPerDay() {
        return waterIntakeInMLGoalPerDay;
    }

    public void setWaterIntakeInMLGoalPerDay(int waterIntakeInMLGoalPerDay) {
        this.waterIntakeInMLGoalPerDay = waterIntakeInMLGoalPerDay;
    }
}
