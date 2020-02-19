package com.example.myapplication.data;

import com.example.myapplication.ui.Data;

public interface WeatherProvider {



    interface TemperatureCallback {

         void onResult(int temperature);

     }
     interface DataCallback {

          void onData(Data data);

     }


     void getCurrentTemperature (String city, TemperatureCallback callBack);

     void getData (String city, DataCallback callback);



}


