package com.balazscsernai.kioskmode;

class Constants {

    public static final String SHARED_PREFS = "HideNavigationBarPrefs";
    public static final String NAVIGATON_BAR_HIDDEN = "NavigationBarHidden";
    public static final boolean NAVIGATION_BAR_HIDDEN_DEF = false;
    public static final String ACTION_HIDE_NAVIGATION_BAR = "com.balazscsernai.action.HIDE_NAVIGATION_BAR";
    public static final String EXTRA_HIDE_NAVIGATION_BAR = "com.balazscsernai.extra.HIDE_NAVIGATION_BAR";
    public static final String COMMAND_HIDE_NAVIGATION_BAR = "su -c service call activity %d s16 com.android.systemui";
    public static final String COMMAND_SHOW_NAVIGATION_BAR = "su -c am startservice --user 0 -n com.android.systemui/.SystemUIService";
    public static final int PROCESS_ID_PRE_JELLY_BEAN = 79;
    public static final int PROCESS_ID_POST_JELLY_BEAN = 42;
}
