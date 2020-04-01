package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.data.SettingsProvider;
import com.example.myapplication.data.WeatherProvider;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements MainActivityView {


    public SettingsProvider      settingsProvider;
    public MainActivityPresenter mainActivityPresenter;
    public WeatherProvider weatherProvider;


    public void launchWeatherActivity(String city) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("City", city);
        startActivity(intent);
    }

    public void launchSettingsActivity(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //cityTemperatureView = new CityTemperatureView(this, (AttributeSet) cityTemperatureView);
        settingsProvider = new SettingsProvider(getApplicationContext());
        mainActivityPresenter = new MainActivityPresenter( weatherProvider, settingsProvider, this);

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
                mainActivityPresenter.onRefreshButtonClicked();

                break;

            case R.id.action_settings:
                mainActivityPresenter.onSettingsClicked();

                break;
        }
        return true;
    }



    public void showProgressBar(String city, boolean show, int viewId) {
        final CityTemperatureView customView = findViewById(viewId);
        customView.showProgressbar(show);

    }

    public void showSubtitle(String temperature, int viewId) {
        final CityTemperatureView customView = findViewById(viewId);
        customView.setSubtitle(temperature);


    }

}

