package com.example.myapplication.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class TemperatureConverterTest {

    @Test
    public void getCelsius() {
        String result = TemperatureConverter.getCelsius(200);
        assertEquals("-73 ˚C", result);
    }

    @Test
    public void getCelsius_highValue(){
        String result = TemperatureConverter.getCelsius(1000000);
        assertEquals("999726 ˚C", result);

    }



    @Test
    public void getFahrenheit() {

    }
}