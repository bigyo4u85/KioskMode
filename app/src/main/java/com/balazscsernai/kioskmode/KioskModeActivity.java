package com.balazscsernai.kioskmode;

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.balazscsernai.kioskmodehelper.KioskModeHelper;

import static com.balazscsernai.kioskmode.KioskModeStore.STORE;

public class KioskModeActivity extends Activity {

    private CheckBox kioskModeCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk_mode);
        setupViews();
    }

    private void setupViews() {
        kioskModeCB = (CheckBox) findViewById(R.id.hideNavigationBarActivity_checkBox);
        kioskModeCB.setOnCheckedChangeListener(new HideNavigationBarCheckedListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        kioskModeCB.setChecked(STORE.isKioskModeEnabled(this));
    }

    private void setNavigationBarHidden(boolean hidden) {
        if (hidden) {
            KioskModeHelper.enableKioskMode(this);
        } else {
            KioskModeHelper.disableKioskMode(this);
        }
    }

    private class HideNavigationBarCheckedListener implements OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            setNavigationBarHidden(isChecked);
        }
    }
}
