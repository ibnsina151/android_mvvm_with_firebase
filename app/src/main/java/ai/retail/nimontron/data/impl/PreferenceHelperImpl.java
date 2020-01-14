package ai.retail.nimontron.data.impl;

import android.content.Context;

import ai.retail.nimontron.data.PreferenceHelper;
import ai.retail.nimontron.di.ApplicationContext;

import net.grandcentrix.tray.TrayPreferences;

import javax.inject.Inject;

//import ai.retail.biponon.data.PreferenceHelper;
//import ai.retail.biponon.di.ApplicationContext;
//import ai.retail.biponon.utils.Constants;

public class PreferenceHelperImpl extends TrayPreferences implements PreferenceHelper {

    private static final String KEY_ACCESS_TOKEN = "key_token";

    public static final String KEY_ACCESS_STATE = "key_attendance";

    @Inject
    public PreferenceHelperImpl(@ApplicationContext Context context, String moduleName, int version) {
        super(context, moduleName, version);
    }

//    @Override
//    public void saveAccessToken(String token) {
//        put(KEY_ACCESS_TOKEN, token);
//    }
//
//    @Override
//    public void saveAttendanceState(boolean attendance) {
//        put(KEY_ACCESS_STATE,attendance);
//    }
//
//    @Override
//    public boolean getAttendanceState() {
//        return getBoolean(KEY_ACCESS_STATE,false);
//    }
//
//
//    @Override
//    public String getAccessToken() {
//        return getString(KEY_ACCESS_TOKEN, Constants.EMPTY_STRING);
//    }
}
