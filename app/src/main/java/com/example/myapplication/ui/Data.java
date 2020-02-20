package com.example.myapplication.ui;


public class Data {


    private String description;
    private int    humidity;
    private int    tempMax;
    private int    tempMin;

    public Data(final String description, final int humidity, final int tempMax, final int tempMin) {
        this.description = description;
        this.humidity = humidity;
        this.tempMax = tempMax;
        this.tempMin = tempMin;

    }

    int getTempMin() {
        return tempMin;
    }

    int getTempMax() {
        return tempMax;
    }

    int getHumidity() {
        return humidity;
    }

    String getDescription() {

        return description;
    }


}
