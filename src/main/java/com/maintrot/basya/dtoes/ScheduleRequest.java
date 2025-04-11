package com.maintrot.basya.dtoes;

import com.maintrot.basya.enums.WeekDay;

public class ScheduleRequest {
    private Long masterId;
    private String day;
    private String startTime;
    private String endTime;

    public ScheduleRequest() {}

    public ScheduleRequest(Long masterId, String day, String startTime, String endTime) {
        this.masterId = masterId;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
