package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.view.View.VISIBLE;


public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getIntent().getStringExtra("City");

        String cityname = getIntent().getStringExtra("City");

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
  //      String format = simpleDateFormat.format(new Date());
    //    Log.d("WeatherActivity", "Current Timestamp: " + format);

        //String time = new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss'Z'");

        getData(cityname);
        showCity(cityname);
      //showTime(simpleDateFormat);


    }

    private void getData(final String city) {

        WeatherProvider weatherProvider = new WeatherProviderImpl();
        weatherProvider.getData(city, new WeatherProvider.DataCallback() {
            @Override
            public void onData(final Data data) {
                showData(data);
                showTime();

            }

            @Override
            public void onFail() {

                findViewById(R.id.Network_connection).setVisibility(VISIBLE);

            }

        });
    }


    @SuppressLint("SetTextI18n")
    private void showData(Data data) {

        TextView descriptionView = findViewById(R.id.description);
        descriptionView.setText(data.getDescription());


        TextView humidityView = findViewById(R.id.humidity);
        humidityView.setText("Humidity: " + (data.getHumidity()) + "%");

        TextView tempmaxView = findViewById(R.id.tempmax);
        tempmaxView.setText(CelsiusSymbol.toDisplayTemperature(data.getTempMax()));

        TextView tempminView = findViewById(R.id.tempmin);
        tempminView.setText(CelsiusSymbol.toDisplayTemperature(data.getTempMin()));


    }

    private void showCity(String cityname) {

        TextView textView = findViewById(R.id.city_name);
        textView.setText(cityname);
    }

    @SuppressLint("SetTextI18n")
    private void showTime(){

        TextView lastupdated = findViewById((R.id.last_updated_time));
        lastupdated.setText("Last Updated: "+  getDateNow());
     //   lastupdated.setText(String.format("Last Updated: ",  getDateNow()));


    }
  /*
    public static String unixTimeStampToDateTime ( double unixTimeStamp) {
       DateFormat dateFormat = new SimpleDateFormat("HH:mm");
       Date date = new Date();
       date.setTime((long)unixTimeStamp * 1000);
       return dateFormat.format(date);
    }
   */
    public static String getDateNow(){

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        Date date = new Date();
        return dateFormat.format(date);



    }



}



