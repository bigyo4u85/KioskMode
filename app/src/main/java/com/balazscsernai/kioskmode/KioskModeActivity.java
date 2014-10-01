package com.balazscsernai.kioskmode;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

import static com.balazscsernai.kioskmode.Constants.NAVIGATION_BAR_HIDDEN_DEF;
import static com.balazscsernai.kioskmode.Constants.NAVIGATON_BAR_HIDDEN;
import static com.balazscsernai.kioskmode.Constants.SHARED_PREFS;

public class KioskModeActivity extends Activity {

    private ToggleButton hideNavigationBarTB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk_mode);
        setupViews();
    }

    private void setupViews() {
        hideNavigationBarTB = (ToggleButton) findViewById(R.id.hideNavigationBarActivity_toggle);
        hideNavigationBarTB.setOnCheckedChangeListener(new HideNavigationBarCheckedListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        hideNavigationBarTB.setChecked(loadPref());
    }

    private void setNavigationBarHidden(boolean hidden) {
        if (hidden) {
            KioskModeHelper.hideNavigationBar(this);
        } else {
            KioskModeHelper.showNavigationBar(this);
        }
        savePref(hidden);
    }

    private boolean loadPref() {
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPrefs.getBoolean(NAVIGATON_BAR_HIDDEN, NAVIGATION_BAR_HIDDEN_DEF);
    }

    private void savePref(boolean hidden) {
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        sharedPrefs.edit().putBoolean(NAVIGATON_BAR_HIDDEN, hidden).commit();
    }

    private class HideNavigationBarCheckedListener implements OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            setNavigationBarHidden(isChecked);
        }
    }
}
