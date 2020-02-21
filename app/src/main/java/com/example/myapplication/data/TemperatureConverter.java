package com.example.myapplication.data;


class TemperatureConverter {

        static int getCelsius(double temp) {

            return (int) (temp - 273.16);

        }

}