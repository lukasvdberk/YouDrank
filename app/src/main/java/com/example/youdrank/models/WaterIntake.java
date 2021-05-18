package com.example.youdrank.models;

import java.util.Date;

public class WaterIntake {
    private int inTakeInMilliliter;
    private Date createdOn;

    public WaterIntake(int inTakeInMilliliter, Date createdOn) {
        this.inTakeInMilliliter = inTakeInMilliliter;
        this.createdOn = createdOn;
    }

    public int getInTakeInMilliliter() {
        return inTakeInMilliliter;
    }

    public void setInTakeInMilliliter(int inTakeInMilliliter) {
        this.inTakeInMilliliter = inTakeInMilliliter;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
