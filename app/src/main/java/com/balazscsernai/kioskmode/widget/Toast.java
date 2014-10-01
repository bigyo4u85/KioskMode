package com.balazscsernai.kioskmode.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Custom toast.
 * @author Balazs_Csernai
 */
public class Toast {

    private final Context context;
    private final String message;
    private WindowManager windowManager;
    private WidgetToast toast;

    private Toast(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    /**
     * Displays the toast.
     */
    public final void show() {
        if (!isShown()) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            toast = new WidgetToast(context);
            toast.setMessage(message);
            toast.setOnClickListener(new ToastClickListener(this));
            windowManager.addView(toast, getLayoutParams());
        }
    }

    public final boolean isShown() {
        return windowManager != null && toast != null;
    }

    /**
     * Hides the toast.
     */
    public final void dismiss() {
        if (isShown()) {
            windowManager.removeView(toast);
            windowManager = null;
            toast = null;
        }
    }

    private WindowManager.LayoutParams getLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        layoutParams.gravity = Gravity.CENTER;
        return layoutParams;
    }

    /**
     * Creates a toast.
     * @param context Android context
     * @param message Toast message
     * @return Toast
     */
    public static final Toast create(Context context, String message) {
        return new Toast(context, message);
    }

}
