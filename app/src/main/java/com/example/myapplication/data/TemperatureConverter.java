package com.example.myapplication.data;


public class TemperatureConverter {

    public static String getCelsius(double temp) {
        int celsius = (int) (temp - 273.16);
        return (celsius + " ˚C");
    }

    public static String getFahrenheit(double temp) {
        int fahrenheit = (int) (temp * 9 / 5 - 459.67);
        return fahrenheit + " ˚F";
    }
}


