package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.WeatherActivity;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public void launchweatheractivity(View view) {

        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);

        findViewById(R.id.Tehran).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                // getCurrentData("Tehran", R.id.Tehran);
                // description on a new page

            }
        });

        findViewById(R.id.Stockholm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // description on a new page

            }
        });

        findViewById(R.id.Milan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

            }
        });

        findViewById(R.id.New_York).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

            }
        });

        findViewById(R.id.Beijing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

            }
        });

    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                getCurrentData("Tehran", R.id.Tehran_current_temperature);
                getCurrentData("Stockholm", R.id.Stockholm_current_temperature);
                getCurrentData("Milan", R.id.Milan_current_temperature);
                getCurrentData("New York", R.id.New_York_current_temperature);
                getCurrentData("Beijing", R.id.Beijing_current_temperature);

            }
        });
    }
/*
    public void onClick(final View view) {

        getCurrentData("Tehran", R.id.Tehran);
        getCurrentData("Stockholm", R.id.Stockholm);
        getCurrentData("Milan", R.id.Milan);
        getCurrentData("New York", R.id.New_York);
        getCurrentData("Beijing", R.id.Beijing);
    }
*/

    private void getCurrentData(final String city, final int viewid) {

        WeatherProvider weatherProvider = new WeatherProviderImpl();
        weatherProvider.getCurrentTemperature(city, new WeatherProvider.TemperatureCallback() {
            @Override
            public void onResult(final double temperature) {
                showTemperature(temperature, viewid);
              //  showHumidity(humidity, viewid);
            }


        });

    }

    private void showTemperature(double temp, int viewid ) {

        //showTemperature(temp, viewid);
        TextView textView = findViewById(viewid);
        textView.setText((temp) + "˚");
    }

    
/*
    private void showHumidity (int humidity, viewid){
        TextView textView = findViewById(viewid);
        textView.setText((humidity) + "˚");



    }
*/


}

