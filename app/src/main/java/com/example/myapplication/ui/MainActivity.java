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

    public SettingsProvider settingsProvider;

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

        findViewById(R.id.view1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("Tehran");
            }
        });

        CityTemperatureView view1 = findViewById(R.id.view1);
        view1.setTitle("Tehran");

        findViewById(R.id.view2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("Stockholm");
            }
        });

        CityTemperatureView view2 = findViewById(R.id.view2);
        view2.setTitle("Stockholm");

        findViewById(R.id.view3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("Milan");
            }
        });
        CityTemperatureView view3 = findViewById(R.id.view3);
        view3.setTitle("Milan");


        findViewById(R.id.view4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("New York");
            }
        });

        CityTemperatureView view4 = findViewById(R.id.view4);
        view4.setTitle("New York");

        findViewById(R.id.view5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("Beijing");
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

                //getCurrentData("Milan", R.id.Milan_current_temperature, R.id.progressBar_cyclic_Milan);
                //getCurrentData("Tehran", R.id.current_temperature, R.id.progressBar_cyclic);
                //getCurrentData("Stockholm", R.id.current_temperature, R.id.progressBar_cyclic);
                //getCurrentData("New York", R.id.New_York_current_temperature, R.id.progressBar_cyclic_New_York);
                //getCurrentData("Beijing", R.id.Beijing_current_temperature, R.id.progressBar_cyclic_Beijing);
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

    private void changeTextViewVisibility(int v, int textViewId) {
        TextView hideText = findViewById(textViewId);
        hideText.setVisibility(v);
    }

    private void onWeatherResult(double temperature, int viewId, int progressBarId) {
        showTemperature((int) temperature, viewId);
        showProgressBar(GONE, progressBarId);
        changeTextViewVisibility(VISIBLE, viewId);
    }

    private void onLoadingStarted(int viewId, int progressBarId) {
        showProgressBar(VISIBLE, progressBarId);
        changeTextViewVisibility(GONE, viewId);
    }

    private void onWeatherFail(int viewId, int progressBarId) {
        Toast.makeText(MainActivity.this, "Network connection is not available", Toast.LENGTH_SHORT).show();
        showProgressBar(GONE, progressBarId);
        changeTextViewVisibility(VISIBLE, viewId);
    }

}

