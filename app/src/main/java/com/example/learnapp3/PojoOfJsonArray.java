package com.example.learnapp3;

public class PojoOfJsonArray {
    private String name;
    private String date;
    private String time;

    public PojoOfJsonArray(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}