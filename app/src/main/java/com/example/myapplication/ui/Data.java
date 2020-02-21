package com.example.myapplication.ui;


public class Data {


    private String description;
    private int    humidity;
    private int    tempMax;
    private int    tempMin;
    private String imageUrl;


    public Data(final String description, final int humidity, final int tempMax, final int tempMin, final String imageUrl) {
        this.description = description;
        this.humidity = humidity;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.imageUrl = imageUrl;

    }
    String getImageUrl() {
        return imageUrl;
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
