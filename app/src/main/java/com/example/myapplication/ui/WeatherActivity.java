package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.DateProvider;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import static android.view.View.VISIBLE;


public class WeatherActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getIntent().getStringExtra("City");

        mProgressBar = findViewById(R.id.progressBar_cyclic);
        showProgressBar(true);



        String cityName = getIntent().getStringExtra("City");


        getData(cityName);
        showCity(cityName);

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

        });
    }

    private void showData(Data data) {

        TextView descriptionView = findViewById(R.id.description);
        descriptionView.setText(data.getDescription());

        TextView humidityView = findViewById(R.id.humidity);
        humidityView.setText((data.getHumidity()) + "%");

        TextView tempMaxView = findViewById(R.id.tempMax);
        tempMaxView.setText(CelsiusSymbol.toDisplayTemperature(data.getTempMax()));

        TextView tempMinView = findViewById(R.id.tempMin);
        tempMinView.setText(CelsiusSymbol.toDisplayTemperature(data.getTempMin()));


        Log.d("ImageUrl", data.getImageUrl());

        Picasso.get().load(data.getImageUrl()).into((ImageView) findViewById(R.id.Weather_icon), new Callback() {

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(final Exception e) {
                Log.d("Failed", e.getMessage());

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
        //   lastUpdated.setText(String.format("Last Updated: ",  getDateNow()));

    }

    private void showProgressBar(boolean b) {

        //mProgressBar.setVisibility(View.INVISIBLE);
        if (b) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);

        }

    }

}

