package com.balazscsernai.kioskmode;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.balazscsernai.kioskmode.widget.Toast;

import java.io.IOException;

import static com.balazscsernai.kioskmode.Constants.COMMAND_HIDE_NAVIGATION_BAR;
import static com.balazscsernai.kioskmode.Constants.COMMAND_SHOW_NAVIGATION_BAR;
import static com.balazscsernai.kioskmode.Constants.EXTRA_HIDE_NAVIGATION_BAR;
import static com.balazscsernai.kioskmode.Constants.NAVIGATION_BAR_HIDDEN_DEF;
import static com.balazscsernai.kioskmode.Constants.PROCESS_ID_POST_JELLY_BEAN;
import static com.balazscsernai.kioskmode.Constants.PROCESS_ID_PRE_JELLY_BEAN;

/**
 * Service for hiding navigation bar.
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
        if (intent != null && intent.hasExtra(EXTRA_HIDE_NAVIGATION_BAR)) {
            hideNavigationBar(intent.getBooleanExtra(EXTRA_HIDE_NAVIGATION_BAR, NAVIGATION_BAR_HIDDEN_DEF));
        }
        return Service.START_NOT_STICKY;
    }

    private void hideNavigationBar(boolean hide) {
        hidePreviousToast();
        Process process = null;
        try {
            if (hide) {
                process = Runtime.getRuntime().exec(String.format(COMMAND_HIDE_NAVIGATION_BAR, getProcessNumber()));
            } else {
                process = Runtime.getRuntime().exec(COMMAND_SHOW_NAVIGATION_BAR);
            }
            process.waitFor();
            showToast(String.format("Navigation bar is %s", hide ? "hidden" : "shown"));
        } catch (IOException e) {
            showToast(String.format("Super user privileges needed to %s navigation bar!", hide ? "hide" : "show"));
        } catch (InterruptedException e) {
            showToast(String.format("%s navigation bar failed!", hide ? "Hiding" : "Showing"));
        }
    }

    private int getProcessNumber() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return PROCESS_ID_POST_JELLY_BEAN;
        }
        return PROCESS_ID_PRE_JELLY_BEAN;
    }

    private void showToast(String message) {
        toast = Toast.create(this, message);
        toast.show();
    }

    private void hidePreviousToast() {
        if (toast != null && toast.isShown()) {
            toast.dismiss();
            toast = null;
        }
    }

}
