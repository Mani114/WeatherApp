package com.example.myapplication.ui;

import android.annotation.SuppressLint;
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

    public void launchweatheractivity(String city) {

        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("City", city);
        startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.Tehran).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchweatheractivity("Tehran");

                // getCurrentData("Tehran", R.id.Tehran);
                // description on a new page
            }
        });

        findViewById(R.id.Stockholm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
               launchweatheractivity("Stockholm");

                // description on a new page

            }
        });

        findViewById(R.id.Milan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchweatheractivity("Milan");




            }
        });

        findViewById(R.id.New_York).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchweatheractivity("New York");


            }
        });

        findViewById(R.id.Beijing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchweatheractivity("Beijing");


            }
        });


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

    private void getCurrentData(final String city, final int viewid) {

        WeatherProvider weatherProvider = new WeatherProviderImpl();
        weatherProvider.getCurrentTemperature(city, new WeatherProvider.TemperatureCallback() {
            @Override
            public void onResult(final int temperature) {
                showTemperature(temperature, viewid);
              //  showHumidity(humidity, viewid);
            }


        });

    }

    @SuppressLint("SetTextI18n")
    private void showTemperature(int temp, int viewid ) {

        //showTemperature(temp, viewid);
        TextView textView = findViewById(viewid);
        textView.setText(CelsiusSymbol.toDisplayTemperatur(temp));
    }

    
/*
    private void showHumidity (int humidity, viewid){
        TextView textView = findViewById(viewid);
        textView.setText((humidity) + "˚");



    }
*/


}

