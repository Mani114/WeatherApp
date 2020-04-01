package com.example.myapplication.data;

import android.os.Handler;
import android.util.Log;

import com.example.myapplication.data.response.WeatherResponse;
import com.example.myapplication.ui.Data;


import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherProviderImpl implements WeatherProvider {

    @Override
    public void getCurrentTemperature(final String city, final TemperatureCallback callBack, final boolean withDelay) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentData(city, "a075f44e32a1e6f1519737f716ff8d00");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NotNull final Call<WeatherResponse> call, @NotNull final Response<WeatherResponse> response) {

                assert response.body() != null;
                final double temp = response.body().getMain().getTemp();

                if (withDelay) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onResult(temp);

                        }
                    }, 4000);

                } else {
                    callBack.onResult(temp);
                }

            }

            @Override
            public void onFailure(@NotNull final Call<WeatherResponse> call, @NotNull final Throwable t) {
                Log.d("Weather", "Failure");
                callBack.onFailure();

            }
        });
    }

    @Override
    public void getData(final String city, final DataCallback callback, final boolean withDelay) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentData(city, "a075f44e32a1e6f1519737f716ff8d00");

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NotNull final Call<WeatherResponse> call, @NotNull final Response<WeatherResponse> response) {


                assert response.body() != null;
                double minTemp = response.body().getMain().getTempMin();
                double maxTemp = response.body().getMain().getTempMax();


                final Data data = new Data(
                        response.body().getWeather().get(0).getDescription(),
                        response.body().getMain().getHumidity(),
                        maxTemp,
                        minTemp,
                        response.body().getWind().getSpeed(),
                        "http://openweathermap.org/img/w/" + response.body().getWeather().get(0).getIcon() + ".png"
                );

                if (withDelay) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            callback.onData(data);

                        }
                    }, 4000);
                } else {
                    callback.onData(data);
                }

            }

            @Override
            public void onFailure(@NotNull final Call<WeatherResponse> call, @NotNull final Throwable t) {
                Log.d("Weather", "Failure");
                callback.onFail();

            }

        });

    }

}
