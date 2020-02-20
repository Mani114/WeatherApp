package com.example.myapplication.data;

import android.util.Log;

import com.example.myapplication.R;
import com.example.myapplication.data.response.WeatherResponse;
import com.example.myapplication.ui.Data;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherProviderImpl implements WeatherProvider {

    @Override
    public void getCurrentTemperature(final String city, final TemperatureCallback callBack) {


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
            public void onResponse(final Call<WeatherResponse> call, final Response<WeatherResponse> response) {

                double temp = response.body().getMain().getTemp();
                int celsiusTemp = TemperatureConverter.getCelsius(temp);
                callBack.onResult(celsiusTemp);
            }

            @Override
            public void onFailure(final Call<WeatherResponse> call, final Throwable t) {
                Log.d("Weather", "Failure");
                callBack.onFailure();

            }
        });
    }

    @Override
    public void getData(final String city, final DataCallback callback) {

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
            public void onResponse(final Call<WeatherResponse> call, final Response<WeatherResponse> response) {


                double minTemp = response.body().getMain().getTempMin();
                double maxTemp = response.body().getMain().getTempMax();
                int celsiusminTemp = TemperatureConverter.getCelsius(minTemp);
                int celsiusmaxTemp = TemperatureConverter.getCelsius(maxTemp);


                Data data = new Data(
                        response.body().getWeather().get(0).getDescription(),
                        response.body().getMain().getHumidity(),
                        celsiusmaxTemp,
                        celsiusminTemp
                );

                callback.onData(data);

            }

            @Override
            public void onFailure(final Call<WeatherResponse> call, final Throwable t) {
                Log.d("Weather", "Failure");
                callback.onFail();


            }

        });

    }




}
