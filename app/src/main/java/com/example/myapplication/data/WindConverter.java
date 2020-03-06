package com.example.myapplication.data;

public class WindConverter {
    public static String getSpeed (double speed) {
        double windSpeed = (int) (speed * 2.2369);
        return (windSpeed + " mph");
    }

}


