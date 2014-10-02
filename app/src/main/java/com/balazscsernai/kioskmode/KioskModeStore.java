package com.balazscsernai.kioskmode;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;
import static com.balazscsernai.kioskmode.Constants.KIOSK_MODE_ENABLED;
import static com.balazscsernai.kioskmode.Constants.KIOSK_MODE_ENABLED_DEFAULT;
import static com.balazscsernai.kioskmode.Constants.PREFERENCES;

/**
 * Store for kiosk mode preferences.
 * Created by Balazs_Csernai on 10/2/2014.
 */
enum KioskModeStore {

    STORE;

    /**
     * Checks if kiosk mode is enabled.
     * @return True if kiosk mode is enabled
     */
    public boolean isKioskModeEnabled(Context context) {
        return context.getSharedPreferences(PREFERENCES, MODE_PRIVATE).getBoolean(KIOSK_MODE_ENABLED, KIOSK_MODE_ENABLED_DEFAULT);
    }

    /**
     * Stores kiosk mode state.
     * @param enabled True if kiosk mode is enabled
     */
    public void setKioskMode(Context context, boolean enabled) {
        context.getSharedPreferences(PREFERENCES, MODE_PRIVATE).edit().putBoolean(KIOSK_MODE_ENABLED, enabled).commit();
    }

}
