package com.balazscsernai.kioskmodehelper;

import android.content.Context;
import android.content.Intent;

import static com.balazscsernai.kioskmodehelper.Constants.ACTION_ENABLE_KIOSK_MODE;
import static com.balazscsernai.kioskmodehelper.Constants.EXTRA_ENABLE_KIOSK_MODE;

/**
 * KioskModeHelper for hiding navigation bar.
 * @author Balazs_Csernai
 */
public class KioskModeHelper {

    private KioskModeHelper() {}

    /**
     * Enables kiosk mode.<br>
     *     <em>Hides status- and navigation bar.</em>
     * @param context Android context
     */
    public static final void enableKioskMode(Context context) {
        context.sendBroadcast(getBroadcastIntent(true));
    }

    /**
     * Disables kiosk mode.<br>
     *     <em>Displays status- and navigation bar.</em>
     * @param context Android context
     */
    public static final void disableKioskMode(Context context) {
        context.sendBroadcast(getBroadcastIntent(false));
    }

    private static final Intent getBroadcastIntent(boolean enable) {
        return new Intent().setAction(ACTION_ENABLE_KIOSK_MODE).putExtra(EXTRA_ENABLE_KIOSK_MODE, enable);
    }

}
