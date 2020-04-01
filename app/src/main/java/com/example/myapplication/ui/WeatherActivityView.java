package com.example.myapplication.ui;


public interface WeatherActivityView {

    void showDescription(String description);

    void showHumidity(String humidity);

    void showMaxTemp(String maxTemp);

    void showMinTemp(String minTemp);

    void showWind(String wind);

    void showProgressBar(boolean b);

    void showTime();

    void showIcon(Data data);

}
