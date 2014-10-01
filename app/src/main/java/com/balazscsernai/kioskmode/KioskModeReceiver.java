package com.balazscsernai.kioskmode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.balazscsernai.kioskmode.Constants.ACTION_HIDE_NAVIGATION_BAR;
import static com.balazscsernai.kioskmode.Constants.EXTRA_HIDE_NAVIGATION_BAR;
import static com.balazscsernai.kioskmode.Constants.NAVIGATION_BAR_HIDDEN_DEF;

/**
 * Intent receiver for hiding navigation bar.
 * @author Balazs_Csernai
 */
public class KioskModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_HIDE_NAVIGATION_BAR) && intent.hasExtra(EXTRA_HIDE_NAVIGATION_BAR)) {
            Intent serviceIntent = new Intent();
            serviceIntent.setClass(context, KioskModeService.class);
            serviceIntent.putExtra(EXTRA_HIDE_NAVIGATION_BAR, intent.getBooleanExtra(EXTRA_HIDE_NAVIGATION_BAR, NAVIGATION_BAR_HIDDEN_DEF));
            context.startService(serviceIntent);
        }
    }
}
