package com.example.youdrank.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import androidx.annotation.RequiresApi;

import com.example.youdrank.models.WaterIntake;
import com.example.youdrank.util.database.DatabaseHelper;
import com.example.youdrank.util.database.DatabaseInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WaterIntakeController {
    Context context;
    private final String dateFormat = "yyyy-mm-dd";


    public WaterIntakeController(Context context) {
        this.context = context;
    }

    public void addWaterIntake(WaterIntake waterIntake) {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(context);

        ContentValues values = new ContentValues();

        DateFormat dateFormat = new SimpleDateFormat(this.dateFormat, Locale.ENGLISH);
        String createdOnString = dateFormat.format(waterIntake.getCreatedOn());

        values.put(DatabaseInfo.WaterIntake.WATER_INTAKE_COLUMN, waterIntake.getInTakeInMilliliter());
        values.put(DatabaseInfo.WaterIntake.CREATED_DATE_COLUMN, createdOnString);

        dbHelper.insert(DatabaseInfo.WaterIntake.WATER_INTAKE_TABLE, null, values);
    }

    public ArrayList<WaterIntake> getAllWaterIntake() throws ParseException {
        DatabaseHelper helper = DatabaseHelper.getHelper(context);
        Cursor rs = helper.query(DatabaseInfo.WaterIntake.WATER_INTAKE_TABLE, new String[]{"*"}, null, null, null, null, null);
        rs.moveToFirst();

        ArrayList<WaterIntake> waterIntakes = new ArrayList<WaterIntake>();
        while (rs.moveToNext()) {
            int waterIntakeInMl = rs.getInt(rs.getColumnIndex(DatabaseInfo.WaterIntake.WATER_INTAKE_COLUMN));
            String createdOnString = rs.getString(rs.getColumnIndex(DatabaseInfo.WaterIntake.CREATED_DATE_COLUMN));

            Date parsedDate = new SimpleDateFormat(this.dateFormat, Locale.ENGLISH).parse(createdOnString);
            waterIntakes.add(new WaterIntake(waterIntakeInMl, parsedDate));
        }

        return waterIntakes;
    }
}
