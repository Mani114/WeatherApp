package com.example.myapplication.ui;

import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.response.Main;

public class MainActivityPresenter {

    private MainActivityView mainActivityView;
    private SettingsProvider settingsProvider;
    private MainActivity mainActivity;

    public MainActivityPresenter(final MainActivityView mainActivityview, final SettingsProvider settingsProvider, final MainActivity mainActivity) {
        this.mainActivityView = mainActivityview;
        this.settingsProvider = settingsProvider;
        this.mainActivity = mainActivity;
    }

    public void onCreate() {

    }

    void onCityClicked (String city) {
        mainActivity.launchWeatherActivity(city);

    }

    void onItemSelected(){



    }


}
