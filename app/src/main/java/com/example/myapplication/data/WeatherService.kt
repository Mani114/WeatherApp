package com.example.myapplication.data


import com.example.myapplication.data.response.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
const val API_KEY = "a075f44e32a1e6f1519737f716ff8d00"


interface WeatherService {
    @GET("data/2.5/weather?")
    fun getCurrentData(@Query("q") city: String, @Query("appid") app_id: String): Call<WeatherResponse>

}