package com.example.myapplication.ui;

import com.example.myapplication.WeatherRepository;
import com.example.myapplication.data.response.Weather;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class WeatherActivityPresenterTest {

    @Test
    public void shouldPassWeatherToView() {

        // given (lets create initial conditions)
        WeatherActivityView view = new MockView();
        WeatherRepository weatherRepository = new MockWeatherRepository(true);

        // when (the actions we want to trigger),presenter gets instance of view
        WeatherActivityPresenter presenter = new WeatherActivityPresenter(view, weatherRepository);
        presenter.loadWeather();

        // then (did it work or did it not work)
        Assert.assertEquals(true, ((MockView) view).displayWeatherWithWeatherCalled);
    }


    @Test
    public void shouldHandleNoWeatherFound() {

        WeatherActivityView view = new MockView();
        WeatherRepository weatherRepository = new MockWeatherRepository(false);

        WeatherActivityPresenter presenter = new WeatherActivityPresenter(view, weatherRepository);
        presenter.loadWeather();

        Assert.assertEquals(true, ((MockView) view).displayWeatherWithNoWeatherCalled);


    }

    private class MockView implements WeatherActivityView {

        boolean displayWeatherWithWeatherCalled;
        boolean displayWeatherWithNoWeatherCalled;


        @Override
        public void displayWeather(final List<Weather> weatherList) {

            if (weatherList.size() == 3) {
                displayWeatherWithWeatherCalled = true;
            }

        }


        @Override
        public void displayNoWeather() {
            displayWeatherWithNoWeatherCalled = true;
        }


    }


    private class MockWeatherRepository implements WeatherRepository {

        private boolean returnSomeWeather;

        public MockWeatherRepository(boolean returnSomeWeather) {
            this.returnSomeWeather = returnSomeWeather;
        }

        @Override
        public List<Weather> getWeather() {

            if (returnSomeWeather) {

                return Arrays.asList(new Weather("Varmt", "sss", 2, "sss"),
                        new Weather("oks", "mskms", 3, "jsjs"),
                        new Weather("skoaksa", "msmss", 4, "kdd"));
            } else {
                return Collections.emptyList();
            }


        }
    }

}