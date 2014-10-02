package com.balazscsernai.kioskmode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.balazscsernai.kioskmode.Constants.KIOSK_MODE_ENABLED_DEFAULT;
import static com.balazscsernai.kioskmodehelper.Constants.ACTION_ENABLE_KIOSK_MODE;
import static com.balazscsernai.kioskmodehelper.Constants.EXTRA_ENABLE_KIOSK_MODE;

/**
 * Intent receiver for kiosk mode.
 *
 * @author Balazs_Csernai
 */
public class KioskModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_ENABLE_KIOSK_MODE) && intent.hasExtra(EXTRA_ENABLE_KIOSK_MODE)) {
            Intent serviceIntent = new Intent();
            serviceIntent.setClass(context, KioskModeService.class);
            serviceIntent.putExtra(EXTRA_ENABLE_KIOSK_MODE, intent.getBooleanExtra(EXTRA_ENABLE_KIOSK_MODE, KIOSK_MODE_ENABLED_DEFAULT));
            context.startService(serviceIntent);
        }
    }

}
