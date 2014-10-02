package com.balazscsernai.kioskmode;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.balazscsernai.kioskmode.widget.Toast;

import java.io.IOException;

import static com.balazscsernai.kioskmode.Constants.COMMAND_DISABLE_KIOSK_MODE;
import static com.balazscsernai.kioskmode.Constants.COMMAND_ENABLE_KIOSK_MODE;
import static com.balazscsernai.kioskmode.Constants.KIOSK_MODE_ENABLED_DEFAULT;
import static com.balazscsernai.kioskmode.Constants.PROCESS_ID_POST_JELLY_BEAN;
import static com.balazscsernai.kioskmode.Constants.PROCESS_ID_PRE_JELLY_BEAN;
import static com.balazscsernai.kioskmode.KioskModeStore.STORE;
import static com.balazscsernai.kioskmodehelper.Constants.EXTRA_ENABLE_KIOSK_MODE;

/**
 * Service for kiosk mode.
 * @author Balazs_Csernai
 */
public class KioskModeService extends Service {

    private Toast toast;

    @Override
    public IBinder onBind(Intent intent) {
        /**
         * This service can not be bound.
         */
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (isValidIntent(intent)) {
            boolean enable = getIntentExtra(intent);
            if (isKioskModeChanged(enable)) {
                enableKioskMode(enable);
            }
        }
        return Service.START_NOT_STICKY;
    }

    private boolean isValidIntent(Intent intent) {
        return intent != null && intent.hasExtra(EXTRA_ENABLE_KIOSK_MODE);
    }

    private boolean getIntentExtra(Intent intent) {
        return intent.getBooleanExtra(EXTRA_ENABLE_KIOSK_MODE, KIOSK_MODE_ENABLED_DEFAULT);
    }

    private boolean isKioskModeChanged(boolean enable) {
        return enable != STORE.isKioskModeEnabled(this);
    }

    private void enableKioskMode(boolean enable) {
        Process process = null;

        hidePreviousToast();
        try {
            if (enable) {
                process = Runtime.getRuntime().exec(String.format(COMMAND_ENABLE_KIOSK_MODE, getProcessNumber()));
            } else {
                process = Runtime.getRuntime().exec(COMMAND_DISABLE_KIOSK_MODE);
            }
            process.waitFor();
            STORE.setKioskMode(this, enable);
            showInfoToast(String.format("Kiosk mode %s", enable ? "enabled" : "disabled"));
        } catch (IOException e) {
            showErrorToast(String.format("Super user privileges needed to %s kiosk mode!", enable ? "enable" : "disable"));
        } catch (InterruptedException e) {
            showErrorToast(String.format("%s kiosk mode failed!", enable ? "Enabling" : "Disabling"));
        }
    }

    private int getProcessNumber() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return PROCESS_ID_POST_JELLY_BEAN;
        }
        return PROCESS_ID_PRE_JELLY_BEAN;
    }

    private void showInfoToast(String message) {
        toast = Toast.createInfo(this, message);
        toast.show();
    }

    private void showErrorToast(String message) {
        toast = Toast.createInfo(this, message);
        toast.show();
    }

    private void hidePreviousToast() {
        if (toast != null && toast.isShown()) {
            toast.dismiss();
            toast = null;
        }
    }

}
