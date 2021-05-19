package com.example.youdrank.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class WaterIntakeOfToday {
    ArrayList<WaterIntake> waterIntakes;

    public WaterIntakeOfToday(ArrayList<WaterIntake> waterIntakes) {
        this.waterIntakes = waterIntakes;
    }

    public WaterIntake getTotalWaterIntakeOfToday() {
        WaterIntake totalIntake = new WaterIntake(0, new Date());
        for (WaterIntake waterIntake: this.waterIntakes) {
            totalIntake.setInTakeInMilliliter(totalIntake.getInTakeInMilliliter() + waterIntake.getInTakeInMilliliter());
        }

        return totalIntake;
    }

    public ArrayList<WaterIntake> getWaterIntakes() {
        return waterIntakes;
    }

    public void addWaterIntake(WaterIntake waterIntakes) {
        this.waterIntakes.add(waterIntakes);
    }
}
