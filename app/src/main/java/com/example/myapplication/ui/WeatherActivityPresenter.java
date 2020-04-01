package com.example.myapplication.ui;

import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.TemperatureConverter;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WindConverter;

public class WeatherActivityPresenter {

    private WeatherActivityView view;
    private WeatherProvider     weatherProvider;
    private SettingsProvider    settingsProvider;
    private String cityName;



    WeatherActivityPresenter(final WeatherActivityView view, final WeatherProvider weatherProvider, final SettingsProvider settingsProvider,
                             final String cityName) {
        this.view = view;
        this.weatherProvider = weatherProvider;
        this.settingsProvider = settingsProvider;
        this.cityName = cityName;
    }

    public void onCreate() {
        loadWeather();
    }


    void loadWeather() {

        weatherProvider.getData(cityName, new WeatherProvider.DataCallback() {
            @Override
            public void onData(final Data data) {

                view.showTime();

                view.showProgressBar(false);

                view.showDescription(data.getDescription());

                view.showHumidity(data.getHumidity() + "%");

                if (settingsProvider.getTemperatureMetric()) {
                    view.showMaxTemp(TemperatureConverter.getFahrenheit(data.getTempMax()));
                } else {
                    view.showMaxTemp(TemperatureConverter.getCelsius(data.getTempMax()));
                }

                if (settingsProvider.getTemperatureMetric()) {
                    view.showMinTemp(TemperatureConverter.getFahrenheit(data.getTempMin()));
                } else {
                    view.showMinTemp(TemperatureConverter.getCelsius(data.getTempMin()));
                }


                if (settingsProvider.getWind()) {
                    view.showWind(WindConverter.getSpeed(data.getSpeed()));
                } else {
                    view.showWind(data.getSpeed() + " m/s");
                }

                view.showIcon(data);


            }

            @Override
            public void onFail() {
                view.showProgressBar(false);

            }
        }, settingsProvider.withDelay());


    }


}
