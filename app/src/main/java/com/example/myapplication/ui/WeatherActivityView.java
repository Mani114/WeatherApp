package com.example.myapplication.ui;

import com.example.myapplication.data.response.Weather;

import java.util.List;

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
