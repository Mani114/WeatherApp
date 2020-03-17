package com.example.myapplication.ui;

import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.WeatherProvider;

import org.junit.Assert;
import org.junit.Test;


public class WeatherActivityPresenterTest {

    @Test
    public void shouldPassWeatherToView() {

        // given (lets create initial conditions)
        WeatherActivityView view = new MockView();
        WeatherProvider weatherProvider = new MockWeatherProvider(true);
        // SettingsProvider settingsProvider = new MockSettingsProvider

        // when (the actions we want to trigger),presenter gets instance of view
        WeatherActivityPresenter presenter = new WeatherActivityPresenter(view, weatherProvider, null);
        presenter.loadWeather();

        // then (did it work or did it not work)
        Assert.assertEquals(true, ((MockView) view).displayWeatherWithWeatherCalled);
    }


    @Test
    public void shouldHandleNoWeatherFound() {

        WeatherActivityView view = new MockView();
        WeatherProvider weatherProvider = new MockWeatherProvider(false);

        WeatherActivityPresenter presenter = new WeatherActivityPresenter(view, weatherProvider, (SettingsProvider) weatherProvider);
        presenter.loadWeather();

        Assert.assertEquals(true, ((MockView) view).displayWeatherWithNoWeatherCalled);


    }

    private class MockView implements WeatherActivityView {

        public boolean displayWeatherWithNoWeatherCalled;
        boolean displayWeatherWithWeatherCalled;


        @Override
        public void showDescription(final String description) {

        }

        @Override
        public void showHumidity(final String humidity) {

        }

        @Override
        public void showMaxTemp(final String maxTemp) {

        }

        @Override
        public void showMinTemp(final String minTemp) {

        }

        @Override
        public void showWind(final String wind) {

        }

        @Override
        public void showProgressBar(final boolean b) {

        }

        @Override
        public void showTime() {

        }

        @Override
        public void showIcon(final Data data) {

        }
    }

    public class MockSettingsProvider implements WeatherProvider{

        private boolean returnSomeSetting;

        public MockSettingsProvider() {



        }

        @Override
        public void getCurrentTemperature(final String city, final TemperatureCallback callBack, final boolean withDelay) {

        }

        @Override
        public void getData(final String city, final DataCallback callback, final boolean withDelay) {

        }
    }

    private class MockWeatherProvider implements WeatherProvider {

        private boolean returnSomeWeather;

        public MockWeatherProvider(boolean returnSomeWeather) {
            this.returnSomeWeather = returnSomeWeather;
        }

        @Override
        public void getCurrentTemperature(final String city, final TemperatureCallback callBack, final boolean withDelay) {

        }

        @Override
        public void getData(final String city, final DataCallback callback, final boolean withDelay) {

        }
    }

}