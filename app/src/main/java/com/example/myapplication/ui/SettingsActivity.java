package com.example.myapplication.ui;

  import android.os.Bundle;
import android.util.Log;
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

        final CheckBox checkBoxFahrenheit = findViewById(R.id.fahrenheit);
        checkBoxFahrenheit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {

                Log.d("onCheckedChanged", String.valueOf(isChecked));
                settingsActivityPresenter.onTemperatureCheckBoxChanged(isChecked);
            }
        });

         checkBoxFahrenheit.setChecked(settingsProvider.getTemperatureMetric());

        final CheckBox checkBoxWind = findViewById(R.id.wind);
        checkBoxWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                Log.d("onCheckedChanged", String.valueOf(isChecked));
                settingsActivityPresenter.onWindCheckBoxChanged(isChecked);
            }
        });


        final CheckBox checkBoxDelay = findViewById(R.id.delay);
        checkBoxDelay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                Log.d("onCheckedChanged", String.valueOf(isChecked));
                settingsActivityPresenter.onDelayCheckBoxChanged(isChecked);
            }
        });


    }

    @Override
    public void setCheckBoxFahrenheit() {

    }

    @Override
    public void setCheckBoxWind() {

    }

    @Override
    public void setCheckBoxDelay() {

    }
}




