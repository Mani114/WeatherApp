package com.example.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.WeatherRepository;
import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.TemperatureConverter;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;
import com.example.myapplication.data.WindConverter;
import com.example.myapplication.data.response.Weather;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivityPresenter {

    private final WeatherActivityView view;
    private       WeatherProvider     weatherProvider;
    private       SettingsProvider    settingsProvider;


    WeatherActivityPresenter(WeatherActivityView view, final WeatherProvider weatherProvider, final SettingsProvider settingsProvider) {
        this.view = view;
        this.weatherProvider = weatherProvider;
        this.settingsProvider = settingsProvider;
    }

    public void onCreate() {
        loadWeather();
    }


    void loadWeather() {

        weatherProvider.getData("City", new WeatherProvider.DataCallback() {
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
