package com.example.myapplication.ui;

import com.example.myapplication.data.response.Weather;

import java.util.List;

public interface WeatherActivityView {

    void displayWeather (List<Weather> weatherList);

    void displayNoWeather();



}
