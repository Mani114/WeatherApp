package com.example.myapplication.ui;

import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.WeatherProvider;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MainActivityPresenterTest {
    private MainActivityPresenter mainActivityPresenter;
    private SettingsProvider      settingsProvider;
    @Mock
    private MainActivityView      mainActivityView;


    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainActivityPresenter = new MainActivityPresenter(settingsProvider, mainActivityView);

    }

    @Test
    public void

}


