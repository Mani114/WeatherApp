package com.example.myapplication.ui;

import com.example.myapplication.data.SettingsProvider;

public class MainActivityPresenter {

    private MainActivityview mainActivityview;
    private SettingsProvider settingsProvider;

    public MainActivityPresenter(final MainActivityview mainActivityview, final SettingsProvider settingsProvider) {
        this.mainActivityview = mainActivityview;
        this.settingsProvider = settingsProvider;
    }
}
