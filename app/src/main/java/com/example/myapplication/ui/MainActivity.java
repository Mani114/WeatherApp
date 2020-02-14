package com.example.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.WeatherService;
import com.example.myapplication.data.response.Coord;
import com.example.myapplication.data.response.WeatherResponse;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.Tehran).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

            }
        });

        findViewById(R.id.Stockholm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

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


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(final View v) {
                getCurrentData();


            }
        });

    }

    private void getCurrentData() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentData("Tehran", "a075f44e32a1e6f1519737f716ff8d00");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(final Call<WeatherResponse> call, final Response<WeatherResponse> response) {


                double temp = response.body().getMain().getTemp();
                // String temp = String.valueOf(response.body().getMain().getTemp());
               // String coord = String.valueOf(response.body().getCoord());
                temp = (temp - 273.15);
            //    String tempconv = String.format("%.0f", temp);
                temperature(temp);

                //String temp = String.valueOf(response.body().getMain().getTemp());




                //String clouds = String.valueOf(response.body().getClouds());
              //  Log.d("Weather", temperature);

                //clouds(clouds);
               // description(coord);
            }

            @Override
            public void onFailure(final Call<WeatherResponse> call, final Throwable t) {
                Log.d("Weather", "Failure");

            }

            private void temperature(double temp) {

                TextView textView = findViewById(R.id.Tehran_current_temperature);
                textView.setText((temp) + "˚");
            }

           // private void description (String coord){

             //   TextView textView = findViewById(R.id.textView);
               // textView.setText(coord);


            });

            //  private void clouds(String clouds) {

            //    TextView textView = findViewById(R.id.textView);
            //  textView.setText(clouds);

            //}



    }
}

