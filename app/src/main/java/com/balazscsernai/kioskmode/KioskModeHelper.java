package com.balazscsernai.kioskmode;

import android.content.Context;
import android.content.Intent;

import static com.balazscsernai.kioskmode.Constants.ACTION_HIDE_NAVIGATION_BAR;
import static com.balazscsernai.kioskmode.Constants.EXTRA_HIDE_NAVIGATION_BAR;

/**
 * KioskModeHelper for hiding navigation bar.
 * @author Balazs_Csernai
 */
public class KioskModeHelper {

    /**
     * Hides navigation bar.
     * @param context Android context
     */
    public static final void hideNavigationBar(Context context) {
        context.sendBroadcast(getBroadcastIntent(true));
    }

    /**
     * Shows navigation bar.
     * @param context Android context
     */
    public static final void showNavigationBar(Context context) {
        context.sendBroadcast(getBroadcastIntent(false));
    }

    private static final Intent getBroadcastIntent(boolean hide) {
        return new Intent().setAction(ACTION_HIDE_NAVIGATION_BAR).putExtra(EXTRA_HIDE_NAVIGATION_BAR, hide);
    }
}
