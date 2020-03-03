package com.example.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import com.example.myapplication.R;
import com.example.myapplication.data.SettingsProvider;

import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {

    public SettingsProvider settingsProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        settingsProvider = new SettingsProvider(getApplicationContext());

        final CheckBox checkBoxFahrenheit = findViewById(R.id.fahrenheit);
        checkBoxFahrenheit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {

                Log.d("onCheckedChanged", String.valueOf(isChecked));
                settingsProvider.saveTemp(isChecked);
            }
        });

        checkBoxFahrenheit.setChecked(settingsProvider.getTemperatureMetric());

        final CheckBox checkBoxWind = findViewById(R.id.wind);
        checkBoxWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                Log.d("onCheckedChanged", String.valueOf(isChecked));
                settingsProvider.saveWind(isChecked);
            }

        });

        checkBoxWind.setChecked(settingsProvider.getWind());

        final CheckBox checkBoxDelay = findViewById(R.id.delay);
        checkBoxDelay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                Log.d("onCheckedChanged", String.valueOf(isChecked));
                settingsProvider.saveDelay(isChecked);
            }
        });

        checkBoxDelay.setChecked((settingsProvider.withDelay()));

    }


}




