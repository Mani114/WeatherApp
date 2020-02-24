package com.example.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    public void launchWeatherActivity(String city) {

        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("City", city);
        startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        findViewById(R.id.Tehran).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                launchWeatherActivity("Tehran");

                // getCurrentData("Tehran", R.id.Tehran);
                // description on a new page
            }
        });

        findViewById(R.id.Stockholm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
               launchWeatherActivity("Stockholm");

                // description on a new page

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

    public boolean onCreateOptionsMenu (Menu menu){

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
      //  return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_refresh:
                getCurrentData("Tehran", R.id.Tehran_current_temperature);
                getCurrentData("Stockholm", R.id.Stockholm_current_temperature);
                getCurrentData("Milan", R.id.Milan_current_temperature);
                getCurrentData("New York", R.id.New_York_current_temperature);
                getCurrentData("Beijing", R.id.Beijing_current_temperature);
                break;

            case R.id.action_settings:
                Intent intent =  new Intent(this, SettingsActivity.class);
                startActivity(intent);

                break;


           // default:
            //return super.onOptionsItemSelected(item);

        }
        return true;
    }



    private void getCurrentData(final String city, final int viewId) {

        WeatherProvider weatherProvider = new WeatherProviderImpl();
        weatherProvider.getCurrentTemperature(city, new WeatherProvider.TemperatureCallback() {
            @Override
            public void onResult(final int temperature) {
                showTemperature(temperature, viewId);
              //  showHumidity(humidity, viewId);
            }

            @Override
            public void onFailure() {

                    Toast.makeText(MainActivity.this, "Network connection is not available", Toast.LENGTH_SHORT).show();

            }

        });

    }

    private void showTemperature(int temp, int viewId ) {

        //showTemperature(temp, viewId);
        TextView textView = findViewById(viewId);
        textView.setText(CelsiusSymbol.toDisplayTemperature(temp));
    }

    
/*
    private void showHumidity (int humidity, viewId){
        TextView textView = findViewById(viewId);
        textView.setText((humidity) + "˚");



    }
*/


}

