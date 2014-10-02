package com.balazscsernai.kioskmode;

/**
 * Constatns.
 */
class Constants {

    public static final String PREFERENCES = "KioskModePreferences";
    public static final String KIOSK_MODE_ENABLED = "KioskModeEnabled";
    public static final boolean KIOSK_MODE_ENABLED_DEFAULT = false;
    public static final String COMMAND_ENABLE_KIOSK_MODE = "su -c service call activity %d s16 com.android.systemui";
    public static final String COMMAND_DISABLE_KIOSK_MODE = "su -c am startservice --user 0 -n com.android.systemui/.SystemUIService";
    public static final int PROCESS_ID_PRE_JELLY_BEAN = 79;
    public static final int PROCESS_ID_POST_JELLY_BEAN = 42;
}
