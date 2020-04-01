package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.DateProvider;
import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;
import com.squareup.picasso.Picasso;


public class WeatherActivity extends AppCompatActivity implements WeatherActivityView {

    public  SettingsProvider         settingsProvider;
    private ProgressBar              mProgressBar;
    public  WeatherProvider          weatherProvider;
    public  WeatherActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getIntent().getStringExtra("City");
        settingsProvider = new SettingsProvider(getApplicationContext());
        weatherProvider = new WeatherProviderImpl();
        mProgressBar = findViewById(R.id.progressBar_cyclic);
        showProgressBar(true);
        String cityName = getIntent().getStringExtra("City");
        showCity(cityName);


        presenter = new WeatherActivityPresenter(this, weatherProvider, settingsProvider, cityName);
        presenter.onCreate();


    }

    public void showCity(String cityName) {

        TextView textView = findViewById(R.id.city_name);
        textView.setText(cityName);
    }

    public void showTime() {

        TextView lastUpdated = findViewById((R.id.last_updated_time));
        lastUpdated.setText("Last Updated: " + DateProvider.getDateNow());
    }

    @Override
    public void showProgressBar(boolean b) {

        if (b) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);

        }

    }

    @Override
    public void showDescription(final String description) {

        TextView descriptionView = findViewById(R.id.description);
        descriptionView.setText(description);

    }

    @Override
    public void showHumidity(final String humidity) {

        TextView humidityView = findViewById(R.id.humidity);
        humidityView.setText(humidity);
    }

    @Override
    public void showMaxTemp(final String maxTemp) {

        TextView tempMaxView = findViewById(R.id.tempMax);
        tempMaxView.setText(maxTemp);

    }

    @Override
    public void showMinTemp(final String minTemp) {

        TextView tempMinView = findViewById(R.id.tempMin);
        tempMinView.setText(minTemp);

    }

    @Override
    public void showWind(final String wind) {
        TextView windView = findViewById(R.id.wind);
        windView.setText(wind);
    }

    @Override
    public void showIcon(final Data data) {

        Picasso.get().load(data.getImageUrl()).into((ImageView) findViewById(R.id.Weather_icon));

    }

}