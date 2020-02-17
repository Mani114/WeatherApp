package com.example.myapplication.ui;



public class Data {

    private String description;
    private int    humidity;
    private double tempMax;
    private double tempMin;

    public Data(final String description, final int humidity, final double tempMax, final double tempMin) {
        this.description = description;
        this.humidity = humidity;
        this.tempMax = tempMax;
        this.tempMin = tempMin;

    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getDescription(){

        return description;
    }

}
