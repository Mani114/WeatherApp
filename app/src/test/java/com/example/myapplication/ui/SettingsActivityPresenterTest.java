package com.example.myapplication.ui;

import com.example.myapplication.data.SettingsProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class SettingsActivityPresenterTest {
    private SettingsActivityPresenter settingsActivityPresenter;
    @Mock
    private SettingsActivityView settingsActivityView;
    @Mock
    private SettingsProvider settingsProvider;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        settingsActivityPresenter = new SettingsActivityPresenter(settingsActivityView, settingsProvider);

    }

    @Test
    public void onTemperatureCheckBoxChanged_changeMetrics_always(){
        when(settingsProvider.getTemperatureMetric()).thenReturn(true);
        settingsActivityPresenter.onTemperatureCheckBoxChanged(true);
        verify(settingsActivityView).setCheckBoxFahrenheit(true);
    }




}
