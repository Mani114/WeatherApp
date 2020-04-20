package com.example.myapplication.ui;

import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.TemperatureConverter;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WindConverter;

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

    @Test
    public void handleData_showCity_always(){
        Data data = new Data("", 0,0,0,0, "");
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showCity("Tehran");

    }

    @Test
    public void handleData_showTime_always(){
        Data data = new Data("", 0,0,0,0, "");
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showTime();
    }

    @Test
    public void handleData_showProgressBar_always(){
        Data data = new Data("", 0,0,0,0, "");
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showProgressBar(false);

    }


    @Test
    public void handleData_showDescription_always(){
        Data data = new Data("It is warm", 0,0,0,0, "");
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showDescription("It is warm");

    }

    @Test
    public void handleData_showHumidity_always(){
        Data data = new Data("", 5,0,0,0, "");
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showHumidity(data.getHumidity() + "%");

    }

    @Test
    public void handleData_showMaxTemp_fahrenheitChecked(){
        Data data = new Data("", 0,50,0,0, "");
        when(settingsProvider.getTemperatureMetric()).thenReturn(true);
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showMaxTemp(TemperatureConverter.getFahrenheit(50));
    }

    @Test
    public void handleData_showMaxTemp_celsiusChecked(){
        Data data = new Data("", 0,50,0,0, "");
        when(settingsProvider.getTemperatureMetric()).thenReturn(false);
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showMaxTemp(TemperatureConverter.getCelsius(50));

    }

    @Test
    public void handleData_showMinTemp_fahrenheitChecked(){
        Data data = new Data("", 0,0,50,0, "");
        when(settingsProvider.getTemperatureMetric()).thenReturn(true);
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showMinTemp(TemperatureConverter.getFahrenheit(50));
    }

    @Test
    public void handleData_showMinTemp_celsiusChecked(){
        Data data = new Data("", 0,0,50,0, "");
        when(settingsProvider.getTemperatureMetric()).thenReturn(false);
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showMinTemp(TemperatureConverter.getCelsius(50));
    }

    @Test
    public void handleData_getWind_convertsWind(){
        Data data = new Data("", 0,0,0,50, "");
        when(settingsProvider.getWind()).thenReturn(true);
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showWind(WindConverter.getSpeed(50));

    }

    @Test
    public void handleData_getWind_windNotChecked(){
        Data data = new Data("", 0,0,0,50, "");
        when(settingsProvider.getWind()).thenReturn(false);
        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showWind(data.getSpeed() + " m/s");


    }

    @Test
    public void handleData_showIcon_always(){
        Data data = new Data("", 0,0,0,0, "");

        // How can this test be failed?

        weatherActivityPresenter.handleData(data);
        verify(weatherActivityView).showIcon(data);
    }



}
