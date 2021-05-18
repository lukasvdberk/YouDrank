package com.example.youdrank.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.example.youdrank.models.WaterIntake;
import com.example.youdrank.util.database.DatabaseHelper;
import com.example.youdrank.util.database.DatabaseInfo;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class WaterIntakeController {
    Context context;

    public WaterIntakeController(Context context) {
        this.context = context;
    }

    public void addWaterIntake(WaterIntake waterIntake) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);

        ContentValues values = new ContentValues();
        values.put(DatabaseInfo.WaterIntake.WATER_INTAKE_COLUMN, waterIntake.getInTakeInMilliliter());
        values.put(DatabaseInfo.WaterIntake.CREATED_DATE_COLUMN, waterIntake.getCreatedOn().toString());

        dbHelper.insert(DatabaseInfo.WaterIntake.WATER_INTAKE_TABLE, null, values);
    }

    private String dateToISO8096(Date date) {
        return String.format("%tFT%<tRZ", Calendar.getInstance(TimeZone.getTimeZone("Z")));
    }
}
