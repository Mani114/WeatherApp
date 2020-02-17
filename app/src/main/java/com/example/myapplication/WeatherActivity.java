package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;
import com.example.myapplication.ui.Data;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getData("Tehran", R.id.weather_data );

    }

        private void getData (final String city, final int viewid){

            WeatherProvider weatherProvider = new WeatherProviderImpl();
            weatherProvider.getData(city, new WeatherProvider.DataCallback() {
                @Override
                public void onData(final Data data) {
                    showData(data, viewid);
                    //  showHumidity(humidity, viewid);
                }

            });
        }

    private void showData(Data data, int viewid ) {

        //showTemperature(temp, viewid);
        TextView textView = findViewById(viewid);
        textView.setText(data.getDescription());
    }

}



