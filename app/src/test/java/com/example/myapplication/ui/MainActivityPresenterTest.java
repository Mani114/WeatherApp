package com.example.myapplication.ui;

import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.TemperatureConverter;
import com.example.myapplication.data.WeatherProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import androidx.core.widget.TextViewCompat;

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

    @Test
    public void handleResult_showProgressBar_always(){
        mainActivityPresenter.handleResult(10,"Tehran", 0);
        verify(mainActivityView).showProgressBar("Tehran", false, 0);
    }

    @Test
    public void handleResult_showSubtitle_fahrenheit(){
       // mainActivityView.showSubtitle(TemperatureConverter.getFahrenheit(10), 1);
        when(settingsProvider.getTemperatureMetric()).thenReturn(true);
        mainActivityPresenter.handleResult(10, "Tehran", 1);
        verify(mainActivityView).showSubtitle(TemperatureConverter.getFahrenheit(10), 1);
    }


    @Test
    public void handleResult_showSubtitle_celsius(){
       // mainActivityView.showSubtitle(TemperatureConverter.getCelsius(10), 1);
        when(settingsProvider.getTemperatureMetric()).thenReturn(false);
        mainActivityPresenter.handleResult(10,"Tehran", 1);
        verify(mainActivityView).showSubtitle(TemperatureConverter.getCelsius(10), 1);
    }







}


