package com.example.myapplication.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.myapplication.R;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    private static final String TEMP_KEY = "Temperature_metric";
    private static final String WIND_KEY = "Wind";
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        pref = getApplicationContext().getSharedPreferences("myPref", 0);

        final CheckBox checkBoxFahrenheit = findViewById(R.id.fahrenheit);
        checkBoxFahrenheit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {

                Log.d("onCheckedChanged", String.valueOf(isChecked));
                saveData(TEMP_KEY, isChecked);

            }
        });

        checkBoxFahrenheit.setChecked(loadData(TEMP_KEY));

        final CheckBox checkBoxWind = findViewById(R.id.wind);
        checkBoxWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                Log.d("onCheckedChanged", String.valueOf(isChecked));
                saveData(WIND_KEY, isChecked);
            }

        });

        checkBoxWind.setChecked(loadData(WIND_KEY));

    }

    private void saveData(String key, boolean b) {

        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, b);
        editor.apply();
    }

    private boolean loadData(String key) {
        return pref.getBoolean(key, false);

    }



}




