package com.example.myapplication.data;


public class TemperatureConverter {

        public static int getCelsius(double temp) {

            return (int) (temp - 273.16);

        }

}