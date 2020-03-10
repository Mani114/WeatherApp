package com.example.myapplication;

import com.example.myapplication.data.response.Weather;

import java.util.List;

public interface WeatherRepository {

    List<Weather> getWeather();

}
