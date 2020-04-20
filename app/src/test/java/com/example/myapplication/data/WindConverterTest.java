package com.example.myapplication.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class WindConverterTest {

    @Test
    public void getSpeed(){
        String result = WindConverter.getSpeed(5);
        assertEquals("11.0 mph", result);

    }

    @Test
    public void getSpeedEdgeCase(){
        String result = WindConverter.getSpeed(100000);
        assertEquals("223690.0 mph", result);

    }

}
