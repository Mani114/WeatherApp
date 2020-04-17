package com.example.myapplication.ui;

import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.WeatherProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class WeatherActivityPresenterTesting {
    private WeatherActivityPresenter weatherActivityPresenter;
    @Mock
    private WeatherActivityView      weatherActivityView;
    @Mock
    private WeatherProvider          weatherProvider;
    @Mock
    private SettingsProvider         settingsProvider;

    //private String cityName;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        weatherActivityPresenter = new WeatherActivityPresenter(weatherActivityView, weatherProvider, settingsProvider, "Tehran");
    }

    @Test
    public void loadWeather_queriesWeatherWithDelay_always() {
        when(settingsProvider.withDelay()).thenReturn(true);
        weatherActivityPresenter.loadWeather();
        verify(weatherProvider).getData(eq("Tehran"), (WeatherProvider.DataCallback) any(), eq(true));

    }

}
