package com.example.myapplication.ui;

import com.example.myapplication.WeatherRepository;
import com.example.myapplication.data.response.Weather;

import java.util.List;

public class WeatherActivityPresenter {

    private final WeatherActivityView view;
    private       WeatherRepository   weatherRepository;


    WeatherActivityPresenter(WeatherActivityView view, final WeatherRepository weatherRepository) {
        this.view = view;
        this.weatherRepository = weatherRepository;
    }


    public void loadWeather() {

        List<Weather> weatherList = weatherRepository.getWeather();
        if(weatherList.isEmpty()){
            view.displayNoWeather();
        }else{
            view.displayWeather(weatherList);
        }



    }
}
