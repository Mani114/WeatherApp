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

    static final String tempKey = "Temperature_metric";
    static final String windKey = "Wind";
    SharedPreferences pref = getApplicationContext().getSharedPreferences("myPref", 0);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        final CheckBox checkBoxFahrenheit = findViewById(R.id.fahrenheit);
        checkBoxFahrenheit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {

                Log.d("onCheckedChanged", String.valueOf(isChecked));
                saveData(tempKey, isChecked);

            }
        });

        checkBoxFahrenheit.setChecked(loadData(tempKey));

        final CheckBox checkBoxWind = findViewById(R.id.wind);
        checkBoxWind.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                Log.d("onCheckedChanged", String.valueOf(isChecked));
                saveData(windKey, isChecked);
            }

        });

        checkBoxWind.setChecked(loadData(windKey));

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




