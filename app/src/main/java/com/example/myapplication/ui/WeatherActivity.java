package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.WeatherProvider;
import com.example.myapplication.data.WeatherProviderImpl;
import com.example.myapplication.ui.CelsiusSymbol;
import com.example.myapplication.ui.Data;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        getIntent().getStringExtra("City");

        String cityname = getIntent().getStringExtra("City");

        getData(cityname);
        showCity(cityname);

        //getData(cityname,R.id.city_name);

    }

        private void getData (final String city){

            WeatherProvider weatherProvider = new WeatherProviderImpl();
            weatherProvider.getData(city, new WeatherProvider.DataCallback() {
                @Override
                public void onData(final Data data) {
                    showData(data);




                    //showHumidity(humidity, viewid);
                }

            });
        }


    @SuppressLint("SetTextI18n")
    private void showData(Data data) {

        TextView descriptionView = findViewById(R.id.description);
        descriptionView.setText(data.getDescription());

        //showTemperature(temp, viewid);
        TextView humidityView = findViewById(R.id.humidity);
        humidityView.setText("Humidity: "+ (data.getHumidity()) +"%");

        TextView tempmaxView = findViewById(R.id.tempmax);
        tempmaxView.setText(CelsiusSymbol.toDisplayTemperature(data.getTempMax()));

        TextView tempminView = findViewById(R.id.tempmin);
        tempminView.setText(CelsiusSymbol.toDisplayTemperature(data.getTempMin()));





       // textView.setText(data.getHumidity());
        //textView.setText((int) data.getTempMax());
        //textView.setText((int) data.getTempMin());

       // TextView textView1 = findViewById(viewid);
        //textView1.setText(data.getHumidity());

    }

    private void showCity (String cityname){

        TextView textView = findViewById(R.id.city_name);
        textView.setText(cityname);
    }


}



