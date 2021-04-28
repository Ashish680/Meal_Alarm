package com.ashish.mealalarm.models;

import java.util.List;

/**
 * Created by Ashish Tiwari on 28,April,2021
 */
public class CustomModel {
    private String day;
    private List<TimeModel> timeList;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<TimeModel> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<TimeModel> timeList) {
        this.timeList = timeList;
    }
}
