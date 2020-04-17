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
        settingsActivityPresenter.onTemperatureCheckBoxChanged(true);
        verify(settingsProvider).saveTemp(true);
    }


    @Test
    public void onWindCheckBoxChanged_changeWindMetrics_always (){
        settingsActivityPresenter.onWindCheckBoxChanged(true);
        verify(settingsProvider).saveWind(true);
    }

    @Test
    public void onDelayCheckBoxChanged_addDelay_always(){
        settingsActivityPresenter.onDelayCheckBoxChanged(true);
        verify(settingsProvider).saveDelay(true);
    }




}
