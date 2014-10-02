package com.balazscsernai.kioskmode.widget;

import static com.balazscsernai.kioskmode.widget.ToastType.*;
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
    private final String toastMessage;
    private final ToastType toastType;
    private WindowManager windowManager;
    private WidgetToast toast;

    private Toast(Context context, String message, ToastType type) {
        this.context = context;
        this.toastMessage = message;
        this.toastType = type;
    }

    /**
     * Displays the toast.
     */
    public final void show() {
        if (!isShown()) {
            windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            toast = new WidgetToast(context);
            toast.setType(toastType);
            toast.setMessage(toastMessage);
            toast.setOnClickListener(new ToastClickListener(this));
            windowManager.addView(toast, getLayoutParams());
        }
    }

    /**
     * Checks if the toast is displayed.
     * @return True if toast is displayed
     */
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
     * Creates an info toast.
     * @param context Android context
     * @param message Toast toastMessage
     * @return Info toast
     */
    public static final Toast createInfo(Context context, String message) {
        return new Toast(context, message, INFO);
    }

    /**
     * Creates a warning toast.
     * @param context Android context
     * @param message Toast toastMessage
     * @return Warning toast
     */
    public static final Toast createWarning(Context context, String message) {
        return new Toast(context, message, WARNING);
    }

    /**
     * Creates an error toast.
     * @param context Android context
     * @param message Toast toastMessage
     * @return Error toast
     */
    public static final Toast createError(Context context, String message) {
        return new Toast(context, message, ERROR);
    }

}
