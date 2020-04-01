package com.example.myapplication.ui;

import android.view.View;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.R;

public class CityTemperatureView extends FrameLayout {


    private TextView    mTitle;
    private ProgressBar mProgressBar;
    private TextView    mSubTitle;

    public CityTemperatureView(final Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CityTemperatureView, 0, 0);

        String titleText = a.getString(R.styleable.CityTemperatureView_titleText);
        a.recycle();

        LayoutInflater.from(context).inflate(R.layout.custom_view, this, true);

        mTitle = findViewById(R.id.Title);
        setTitle(titleText);

        mSubTitle = findViewById(R.id.current_temperature);
        setSubtitle(titleText);

        mProgressBar = findViewById(R.id.progressBar_cyclic);

    }

    public CityTemperatureView(Context context) {
        this(context, null);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setSubtitle(String subTitle) {
        mSubTitle.setText(subTitle);
    }

    public void showProgressbar(boolean visible) {
        mProgressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
        mSubTitle.setVisibility(visible ? View.GONE : View.VISIBLE);

    }

}

