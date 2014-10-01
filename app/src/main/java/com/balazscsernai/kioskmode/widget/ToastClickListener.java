package com.balazscsernai.kioskmode.widget;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * Toast click event listener.
 * @author Balazs_Csernai
 */
class ToastClickListener implements OnClickListener {

    private final Toast toast;

    /**
     * Constructor.
     * @param toast Toast
     */
    ToastClickListener(Toast toast) {
        this.toast = toast;
    }

    @Override
    public void onClick(View view) {
        toast.dismiss();
    }

}
