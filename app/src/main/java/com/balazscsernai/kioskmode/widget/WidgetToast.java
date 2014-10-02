package com.balazscsernai.kioskmode.widget;

import static com.balazscsernai.kioskmode.widget.ToastType.*;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balazscsernai.kioskmode.R;

/**
 * Widget for displaying toast with message.
 * @author Balazs_Csernai
 */
public class WidgetToast extends RelativeLayout {

    private static final ToastType DEFAULT_TYPE = INFO;
    private TextView messageTV;

    /**
     * Constructor.
     * @param context Android context
     */
    public WidgetToast(Context context) {
        this(context, null);
    }

    /**
     * Constructor.
     * @param context Android context
     * @param attrs Attribute set
     */
    public WidgetToast(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Constructor.
     * @param context Android context
     * @param attrs Attribute set
     * @param defStyle Default style
     */
    public WidgetToast(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.widget_toast, this);
        if (!isInEditMode()) {
            setupViews();
        }
    }

    private void setupViews() {
        messageTV = (TextView) findViewById(R.id.widgetToast_message);
    }

    /**
     * Sets the type of the toast.
     * @param type Toast type
     */
    public void setType(ToastType type) {

    }

    /**
     * Sets the message displayed by the toast.
     * @param message Toast message
     */
    public void setMessage(CharSequence message) {
        messageTV.setText(message);
    }

}
