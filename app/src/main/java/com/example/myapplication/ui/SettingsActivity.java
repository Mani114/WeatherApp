package com.example.myapplication.ui;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import com.example.myapplication.R;
import com.example.myapplication.data.SettingsProvider;

import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity implements SettingsActivityView {

    public SettingsProvider          settingsProvider;
    public SettingsActivityPresenter settingsActivityPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        settingsProvider = new SettingsProvider(getApplicationContext());

        settingsActivityPresenter = new SettingsActivityPresenter(this, settingsProvider);
        settingsActivityPresenter.onCreate();

    }

    @Override
    public void setCheckBoxFahrenheit(boolean fahrenheitChecked) {
        final CheckBox checkBoxFahrenheit = findViewById(R.id.fahrenheit);
        checkBoxFahrenheit.setChecked(fahrenheitChecked);
        checkBoxFahrenheit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                settingsActivityPresenter.onTemperatureCheckBoxChanged(isChecked);
            }
        });
    }

    @Override
    public void setCheckBoxWind(boolean windChecked) {
        final CheckBox checkBoxWind = findViewById(R.id.wind);
        checkBoxWind.setChecked(windChecked);
        checkBoxWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                settingsActivityPresenter.onWindCheckBoxChanged(isChecked);
            }
        });

    }

    @Override
    public void setCheckBoxDelay(boolean delayChecked) {
        final CheckBox checkBoxDelay = findViewById(R.id.delay);
        checkBoxDelay.setChecked(delayChecked);
        checkBoxDelay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                settingsActivityPresenter.onDelayCheckBoxChanged(isChecked);
            }
        });

    }
}




