package com.example.myapplication.ui;

public class Data {
    private String description;
    private int    humidity;
    private double    tempMax;
    private double    tempMin;
    private String imageUrl;
    private double   speed;

    public Data(final String description, final int humidity, final double tempMax, final double tempMin, final double speed, final String imageUrl) {
        this.description = description;
        this.humidity = humidity;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.speed = speed;
        this.imageUrl = imageUrl;

    }

    String getImageUrl() {
        return imageUrl;
    }

    double getSpeed() {
        return speed;
    }

    double getTempMin() {
        return tempMin;
    }

    double getTempMax() {
        return tempMax;
    }

    int getHumidity() {
        return humidity;
    }

    String getDescription() {

        return description;
    }

}
