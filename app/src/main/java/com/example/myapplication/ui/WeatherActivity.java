package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.DateProvider;
import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.TemperatureConverter;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;
import com.example.myapplication.data.WindConverter;
import com.example.myapplication.data.response.Weather;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import static android.view.View.VISIBLE;



public class WeatherActivity extends AppCompatActivity implements WeatherActivityView {

    public  SettingsProvider settingsProvider;
    private ProgressBar      mProgressBar;
    private WeatherActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getIntent().getStringExtra("City");
        settingsProvider = new SettingsProvider(getApplicationContext());
        mProgressBar = findViewById(R.id.progressBar_cyclic);
        showProgressBar(true);
        String cityName = getIntent().getStringExtra("City");

        getData(cityName);
        showCity(cityName);

        presenter = new WeatherActivityPresenter(this, null);
    }

    private void getData(final String city) {

        WeatherProvider weatherProvider = new WeatherProviderImpl();
        weatherProvider.getData(city, new WeatherProvider.DataCallback() {
            @Override
            public void onData(final Data data) {
                showData(data);
                showTime();
                showProgressBar(false);

            }

            @Override
            public void onFail() {
                findViewById(R.id.Network_connection).setVisibility(VISIBLE);
                showProgressBar(false);

            }

        }, settingsProvider.withDelay());

    }

    @SuppressLint("SetTextI18n")
    private void showData(Data data) {

        TextView descriptionView = findViewById(R.id.description);
        descriptionView.setText(data.getDescription());

        TextView humidityView = findViewById(R.id.humidity);
        humidityView.setText("Humidity: " + (data.getHumidity() + "%"));

        TextView tempMaxView = findViewById(R.id.tempMax);
        if (settingsProvider.getTemperatureMetric()) {
            tempMaxView.setText("MaxTemp: " + TemperatureConverter.getFahrenheit(data.getTempMin()));
        } else {
            tempMaxView.setText("MaxTemp: " + TemperatureConverter.getCelsius(data.getTempMax()));
        }

        TextView tempMinView = findViewById(R.id.tempMin);
        if (settingsProvider.getTemperatureMetric()) {
            tempMinView.setText("MinTemp: " + TemperatureConverter.getFahrenheit(data.getTempMin()));
        } else {
            tempMinView.setText("MinTemp: " + TemperatureConverter.getCelsius(data.getTempMin()));
        }

        TextView windView = findViewById(R.id.wind);
        if (settingsProvider.getWind()) {
            windView.setText((WindConverter.getSpeed(data.getSpeed()) + ""));
        } else {
            windView.setText((data.getSpeed() + " m/s"));
        }


        Log.d("ImageUrl", data.getImageUrl());

        Picasso.get().load(data.getImageUrl()).into((ImageView) findViewById(R.id.Weather_icon), new Callback() {

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(final Exception e) {
                Log.d("Failed", Objects.requireNonNull(e.getMessage()));

            }
        });

    }

    private void showCity(String cityName) {

        TextView textView = findViewById(R.id.city_name);
        textView.setText(cityName);
    }

    private void showTime() {

        TextView lastUpdated = findViewById((R.id.last_updated_time));
        lastUpdated.setText("Last Updated: " + DateProvider.getDateNow());
    }

    private void showProgressBar(boolean b) {

        if (b) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);

        }

    }


    @Override
    public void displayWeather(final List<Weather> weatherList) {

    }

    @Override
    public void displayNoWeather() {

    }

}

