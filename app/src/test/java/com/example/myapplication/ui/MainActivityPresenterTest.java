package com.example.myapplication.ui;

import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.WeatherProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MainActivityPresenterTest {
    private MainActivityPresenter mainActivityPresenter;
    @Mock
    private SettingsProvider      settingsProvider;
    @Mock
    private MainActivityView      mainActivityView;
    @Mock
    private WeatherProvider       weatherProvider;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainActivityPresenter = new MainActivityPresenter(settingsProvider, mainActivityView, weatherProvider);

    }

    @Test
    public void onSettingsClicked_launchSettingsActivity_always() {
        mainActivityPresenter.onSettingsClicked();
        verify(mainActivityView).launchSettingsActivity();

    }

    @Test
    public void onCityClicked_launchWeatherActivity_always() {
        mainActivityPresenter.onCityClicked("Stockholm");
        verify(mainActivityView).launchWeatherActivity(eq("Stockholm"));
    }

    @Test
    public void onRefreshButtonClicked_queriesDataForTheCity_always() {
        mainActivityPresenter.onRefreshButtonClicked();
        verify(weatherProvider).getCurrentTemperature(eq("Tehran"), (WeatherProvider.TemperatureCallback) any(), anyBoolean());
        verify(weatherProvider).getCurrentTemperature(eq("Stockholm"), (WeatherProvider.TemperatureCallback) any(), anyBoolean());
        verify(weatherProvider).getCurrentTemperature(eq("Milan"), (WeatherProvider.TemperatureCallback) any(), anyBoolean());
        verify(weatherProvider).getCurrentTemperature(eq("New York"), (WeatherProvider.TemperatureCallback) any(), anyBoolean());
        verify(weatherProvider).getCurrentTemperature(eq("Beijing"), (WeatherProvider.TemperatureCallback) any(), anyBoolean());
    }

    @Test
    public void onRefreshButtonClicked_queriesDataWithDelay_settingSelected(){
        when(settingsProvider.withDelay()).thenReturn(true);
        mainActivityPresenter.onRefreshButtonClicked();
        verify(weatherProvider, atLeastOnce()).getCurrentTemperature(anyString(), (WeatherProvider.TemperatureCallback) any(), eq(true));

    }

    @Test
    public void onRefreshButtonClicked_queriesDataWithoutDelay_settingSelected(){
        when(settingsProvider.withDelay()).thenReturn(false);
        mainActivityPresenter.onRefreshButtonClicked();
        verify(weatherProvider, atLeastOnce()).getCurrentTemperature(anyString(), (WeatherProvider.TemperatureCallback) any(), eq(false));

    }







}


