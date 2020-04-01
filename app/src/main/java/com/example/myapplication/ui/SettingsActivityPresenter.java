package com.example.myapplication.ui;


import com.example.myapplication.data.SettingsProvider;

public class SettingsActivityPresenter {

    private SettingsActivityView settingsActivityView;
    private SettingsProvider     settingsProvider;


    SettingsActivityPresenter(final SettingsActivityView settingsActivityView, final SettingsProvider settingsProvider) {
        this.settingsActivityView = settingsActivityView;
        this.settingsProvider = settingsProvider;
    }


    public void onCreate() {
        settingsActivityView.setCheckBoxWind(settingsProvider.getWind());
        settingsActivityView.setCheckBoxFahrenheit(settingsProvider.getTemperatureMetric());
        settingsActivityView.setCheckBoxDelay(settingsProvider.withDelay());

    }

    void onTemperatureCheckBoxChanged(final boolean isChecked) {
        settingsProvider.saveTemp(isChecked);

    }

    void onWindCheckBoxChanged(final boolean isChecked) {
        settingsProvider.saveWind(isChecked);

    }

    void onDelayCheckBoxChanged(final boolean isChecked) {
        settingsProvider.saveDelay(isChecked);

    }

}
