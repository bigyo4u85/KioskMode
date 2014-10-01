package com.balazscsernai.kioskmode.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balazscsernai.kioskmode.R;

/**
 * Widget for displaying Toast like messages.
 * @author Balazs_Csernai
 */
public class WidgetToast extends RelativeLayout {

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

    public void setMessage(CharSequence message) {
        messageTV.setText(message);
    }

}
