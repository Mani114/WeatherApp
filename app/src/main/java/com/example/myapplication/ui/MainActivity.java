package com.example.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.data.WeatherService;
import com.example.myapplication.data.response.WeatherResponse;

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
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                getCurrentData();

            }
        });

    }

    private void getCurrentData(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentData("Stockholm", "a075f44e32a1e6f1519737f716ff8d00");


        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(final Call<WeatherResponse> call, final Response<WeatherResponse> response) {
                String temprature = String.valueOf(response.body().getMain().getTemp());
                Log.d("Weather", temprature);
            }

            @Override
            public void onFailure(final Call<WeatherResponse> call, final Throwable t) {
                Log.d("Weather", "Failure");

            }
        });
    }
}

