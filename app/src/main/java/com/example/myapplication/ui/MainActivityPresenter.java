package com.example.myapplication.ui;

import com.example.myapplication.R;
import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.TemperatureConverter;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;

public class MainActivityPresenter {

    private MainActivityView mainActivityView;
    private SettingsProvider settingsProvider;


    MainActivityPresenter(final SettingsProvider settingsProvider, final MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
        this.settingsProvider = settingsProvider;
    }


    void onRefreshButtonClicked() {
        getCurrentData("Tehran", R.id.view1);
        getCurrentData("Stockholm", R.id.view2);
        getCurrentData("Milan", R.id.view3);
        getCurrentData("New York", R.id.view4);
        getCurrentData("Beijing", R.id.view5);
    }

    void onSettingsClicked() {
        mainActivityView.launchSettingsActivity();
    }


    private void getCurrentData(final String city, final int viewId) {
        mainActivityView.showProgressBar(city, true, viewId);

        WeatherProvider weatherProvider = new WeatherProviderImpl();
        weatherProvider.getCurrentTemperature(city, new WeatherProvider.TemperatureCallback() {
            @Override
            public void onResult(final double temperature) {
                mainActivityView.showProgressBar(city, false, viewId);
                if (settingsProvider.getTemperatureMetric()) {
                    mainActivityView.showSubtitle(TemperatureConverter.getFahrenheit(temperature), viewId);
                } else {
                    mainActivityView.showSubtitle(TemperatureConverter.getCelsius(temperature), viewId);
                }

            }

            @Override
            public void onFailure() {
                mainActivityView.showProgressBar(city, false, viewId);

            }

        }, settingsProvider.withDelay());

    }

    void onCityClicked(String city) {
        mainActivityView.launchWeatherActivity(city);

    }


}
