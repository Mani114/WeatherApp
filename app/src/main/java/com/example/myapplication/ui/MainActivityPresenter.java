package com.example.myapplication.ui;

import com.example.myapplication.R;
import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.TemperatureConverter;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;
import com.example.myapplication.data.response.Main;

public class MainActivityPresenter {

    private MainActivityView mainActivityView;
    private SettingsProvider settingsProvider;
    private WeatherProvider weatherProvider;



    public MainActivityPresenter(final WeatherProvider weatherProvider, final SettingsProvider settingsProvider, final MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
        this.settingsProvider = settingsProvider;
        this.weatherProvider = weatherProvider;
    }

    public void onCreate() {

    }

    void onRefreshButtonClicked(){
        getCurrentDataNew("Tehran", R.id.view1);
        getCurrentDataNew("Stockholm", R.id.view2);
        getCurrentDataNew("Milan", R.id.view3);
        getCurrentDataNew("New York", R.id.view4);
        getCurrentDataNew("Beijing", R.id.view5);
    }

    private void getCurrentDataNew(final String city, final int viewId) {
        WeatherProvider weatherProvider = new WeatherProviderImpl();
        weatherProvider.getCurrentTemperature(city, new WeatherProvider.TemperatureCallback() {
            @Override
            public void onResult(final double temperature) {
                // onWeatherResult(temperature, viewId, progressBarId);
                mainActivityView.showProgressBar(city, false, viewId);
                if (settingsProvider.getTemperatureMetric()) {
                    mainActivityView.showSubtitle(TemperatureConverter.getFahrenheit(temperature), viewId);
                } else {
                    mainActivityView.showSubtitle(TemperatureConverter.getCelsius(temperature), viewId);
                }

            }

            @Override
            public void onFailure() {
                //   onWeatherFail(viewId, progressBarId);
                mainActivityView.showProgressBar(city, false, viewId);

            }

        }, settingsProvider.withDelay());

    }

    void onCityClicked (String city) {
        mainActivityView.launchWeatherActivity(city);

    }

    void onItemSelected() {

    }

    void onShowProgressBar(String city, boolean show, int viewId){
        mainActivityView.showProgressBar(city, false, viewId);


    }

    void onShowSubtitle(String temperature, int viewId){
        mainActivityView.showSubtitle(temperature, viewId);

    }



}
