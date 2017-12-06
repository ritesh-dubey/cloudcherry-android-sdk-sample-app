package com.getcloudcherry.surveysdksample;

import android.content.Context;
import android.content.SharedPreferences;

public class MyData {
    private static String PREFS_FILE_NAME = "CreDentials";

    Context mContext;

    private static MyData mInstance = null;

    public MyData(Context iContext) {

        this.mContext = iContext;

    }

    public static final synchronized MyData getInstance(Context iContext) {
        if (null == mInstance) {
            mInstance = new MyData(iContext);
        }
        return mInstance;
    }

    public void clearSavedData() {
        SharedPreferences aMyPrefs = mContext.getSharedPreferences(
                PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = aMyPrefs.edit();
        prefsEditor.clear().commit();
    }

    public void setTokenType(boolean iData) {
        SharedPreferences aMyPrefs = mContext.getSharedPreferences(
                PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = aMyPrefs.edit();
        prefsEditor.putBoolean("tokenType", iData);
        prefsEditor.commit();
    }

    public boolean getTokenType() {
        SharedPreferences aMyPrefs = mContext.getSharedPreferences(
                PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return aMyPrefs.getBoolean("tokenType", false);
    }

    public void setToken(String iData) {
        SharedPreferences aMyPrefs = mContext.getSharedPreferences(
                PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = aMyPrefs.edit();
        prefsEditor.putString("token", iData);
        prefsEditor.commit();
    }

    public String getToken() {
        SharedPreferences aMyPrefs = mContext.getSharedPreferences(
                PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return aMyPrefs.getString("token", "");
    }

}
