package com.ashish.mealalarm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * Created by Ashish Tiwari on 28,April,2021
 */
public class DietResponseModel {
    @SerializedName("diet_duration")
    @Expose
    private Integer dietDuration;
    @SerializedName("week_diet_data")
    @Expose
    private Map<String, List<TimeModel>> daySlot;

    public Map<String, List<TimeModel>> getDaySlot() {
        return daySlot;
    }

    public void setDaySlot(Map<String, List<TimeModel>> daySlot) {
        this.daySlot = daySlot;
    }

    public Integer getDietDuration() {
        return dietDuration;
    }

    public void setDietDuration(Integer dietDuration) {
        this.dietDuration = dietDuration;
    }

}
