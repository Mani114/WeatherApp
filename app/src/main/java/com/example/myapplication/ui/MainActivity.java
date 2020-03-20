package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.TemperatureConverter;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public SettingsProvider settingsProvider;
    public MainActivityPresenter mainActivityPresenter;

    public void launchWeatherActivity(String city) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("City", city);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     mainActivityPresenter = new MainActivityPresenter((MainActivityView) this, settingsProvider, this);


        settingsProvider = new SettingsProvider(getApplicationContext());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mainActivityPresenter.onCityClicked("Tehran");
            }
        });

        CityTemperatureView view1 = findViewById(R.id.view1);
        view1.setTitle("Tehran");

        findViewById(R.id.view2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mainActivityPresenter.onCityClicked("Stockholm");
            }
        });

        CityTemperatureView view2 = findViewById(R.id.view2);
        view2.setTitle("Stockholm");

        findViewById(R.id.view3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mainActivityPresenter.onCityClicked("Milan");
            }
        });
        CityTemperatureView view3 = findViewById(R.id.view3);
        view3.setTitle("Milan");


        findViewById(R.id.view4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mainActivityPresenter.onCityClicked("New York");
            }
        });

        CityTemperatureView view4 = findViewById(R.id.view4);
        view4.setTitle("New York");

        findViewById(R.id.view5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mainActivityPresenter.onCityClicked("Beijing");
            }
        });

        CityTemperatureView view5 = findViewById(R.id.view5);
        view5.setTitle("Beijing");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                getCurrentDataNew("Tehran", R.id.view1);
                getCurrentDataNew("Stockholm", R.id.view2);
                getCurrentDataNew("Milan", R.id.view3);
                getCurrentDataNew("New York", R.id.view4);
                getCurrentDataNew("Beijing", R.id.view5);
                break;


            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void getCurrentDataNew(final String city, final int viewId) {
        final CityTemperatureView customView = findViewById(viewId);
        customView.showProgressbar(true);


        WeatherProvider weatherProvider = new WeatherProviderImpl();
        weatherProvider.getCurrentTemperature(city, new WeatherProvider.TemperatureCallback() {
            @Override
            public void onResult(final double temperature) {
                // onWeatherResult(temperature, viewId, progressBarId);
                customView.showProgressbar(false);
                if (settingsProvider.getTemperatureMetric()) {
                    customView.setSubtitle(TemperatureConverter.getFahrenheit(temperature));
                } else {
                    customView.setSubtitle(TemperatureConverter.getCelsius(temperature));
                }

            }

            @Override
            public void onFailure() {
                //   onWeatherFail(viewId, progressBarId);
                customView.showProgressbar(false);

            }

        }, settingsProvider.withDelay());

    }

}

