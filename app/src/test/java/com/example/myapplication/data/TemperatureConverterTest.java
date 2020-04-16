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
    public void getCelsius_firstEdgeCase(){
        String result = TemperatureConverter.getCelsius(1000000);
        assertEquals("999726 ˚C", result);

    }

    @Test
    public void getCelsius_secondEdgeCase(){
        String result = TemperatureConverter.getCelsius(1000);
        assertEquals("726 ˚C", result);
    }

    @Test
    public void getCelsius_thirdEdgeCase(){
        String result = TemperatureConverter.getCelsius(100);
        assertEquals("-173 ˚C", result);
    }

    @Test
    public void getFahrenheit() {
        String result = TemperatureConverter.getFahrenheit(200);
        assertEquals("-99 ˚F", result);
    }

    @Test
    public void getFahrenheit_firstEdgeCase(){
        String result = TemperatureConverter.getFahrenheit(1000000);
        assertEquals("1799540 ˚F", result);
    }

    @Test
    public void getFahrenheit_secondEdgeCase(){
        String result = TemperatureConverter.getFahrenheit(1000);
        assertEquals("1340 ˚F", result);
    }

    @Test
    public void getFahrenheit_thirdEdgeCase(){
        String result = TemperatureConverter.getFahrenheit(100);
        assertEquals("-279 ˚F", result);

    }

}