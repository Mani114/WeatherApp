package com.example.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingsProvider {

    private static final String            TEMP_KEY  = "Temperature_metric";
    private static final String            WIND_KEY  = "Wind";
    private static final String            DELAY_KEY = "Delay";
    private              SharedPreferences pref;


    public SettingsProvider(Context context) {
        pref = context.getSharedPreferences("myPref", 0);

    }

    public void saveTemp(boolean b) {

        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(TEMP_KEY, b);
        editor.apply();

    }

    public void saveWind(boolean b) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(WIND_KEY, b);
        editor.apply();

    }

    public void saveDelay(boolean b) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(DELAY_KEY, b);
        editor.apply();

    }

    public boolean getTemperatureMetric() {
        return pref.getBoolean(TEMP_KEY, false);
    }

    public boolean getWind() {
        return pref.getBoolean(WIND_KEY, false);
    }

    public boolean withDelay (){
        return pref.getBoolean(DELAY_KEY, false);
    }


}
