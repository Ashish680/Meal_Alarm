package com.ashish.mealalarm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ashish Tiwari on 28,April,2021
 */
public class TimeModel {
    @SerializedName("food")
    @Expose
    private String food;
    @SerializedName("meal_time")
    @Expose
    private String mealTime;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }
}
