package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.TemperatureConverter;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    public  SettingsProvider settingsProvider;

    public void launchWeatherActivity(String city) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("City", city);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingsProvider = new SettingsProvider(getApplicationContext());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.Tehran).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("Tehran");
            }
        });

        findViewById(R.id.Stockholm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("Stockholm");
            }
        });

        findViewById(R.id.Milan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("Milan");
            }
        });

        findViewById(R.id.New_York).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("New York");
            }
        });

        findViewById(R.id.Beijing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("Beijing");
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                getCurrentData("Tehran", R.id.Tehran_current_temperature, R.id.progressBar_cyclic_Tehran);
                getCurrentData("Stockholm", R.id.Stockholm_current_temperature, R.id.progressBar_cyclic_Stockholm);
                getCurrentData("Milan", R.id.Milan_current_temperature, R.id.progressBar_cyclic_Milan);
                getCurrentData("New York", R.id.New_York_current_temperature, R.id.progressBar_cyclic_New_York);
                getCurrentData("Beijing", R.id.Beijing_current_temperature, R.id.progressBar_cyclic_Beijing);
                break;

            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);

                break;

        }
        return true;
    }


    private void getCurrentData(final String city, final int viewId, final int progressBarId) {
        onLoadingStarted(viewId, progressBarId);

        WeatherProvider weatherProvider = new WeatherProviderImpl();
        weatherProvider.getCurrentTemperature(city, new WeatherProvider.TemperatureCallback() {
            @Override
            public void onResult(final double temperature) {

                onWeatherResult(temperature, viewId, progressBarId);

            }

            @Override
            public void onFailure() {

                onWeatherFail(viewId, progressBarId);

            }

        }, settingsProvider.withDelay());

    }

    private void showProgressBar(int visibility, int progressViewBarId) {
        ProgressBar progressBarTextView = findViewById(progressViewBarId);
        progressBarTextView.setVisibility(visibility);
    }

    private void showTemperature(double temp, int viewId) {
        TextView temperatureTextView = findViewById(viewId);
        if (settingsProvider.getTemperatureMetric()) {
            temperatureTextView.setText(TemperatureConverter.getFahrenheit(temp));
        } else {
            temperatureTextView.setText(TemperatureConverter.getCelsius(temp));
        }

    }

    private void hideTextView(int v, int textViewId) {
        TextView hideText = findViewById(textViewId);
        hideText.setVisibility(v);
    }

    private void onWeatherResult(double temperature, int viewId, int progressBarId) {
        showTemperature((int) temperature, viewId);
        showProgressBar(GONE, progressBarId);
        hideTextView(VISIBLE, viewId);
    }

    private void onLoadingStarted(int viewId, int progressBarId) {
        showProgressBar(VISIBLE, progressBarId);
        hideTextView(GONE, viewId);
    }

    private void onWeatherFail(int viewId, int progressBarId) {
        Toast.makeText(MainActivity.this, "Network connection is not available", Toast.LENGTH_SHORT).show();
        showProgressBar(GONE, progressBarId);
        hideTextView(VISIBLE, viewId);
    }

}

