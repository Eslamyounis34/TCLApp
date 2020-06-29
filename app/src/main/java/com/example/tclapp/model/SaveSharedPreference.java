package com.example.tclapp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.tclapp.model.PreferencesUtility.LOGGED_IN_Email;
import static com.example.tclapp.model.PreferencesUtility.LOGGED_IN_NAME;
import static com.example.tclapp.model.PreferencesUtility.LOGGED_IN_PREF;
import static com.example.tclapp.model.PreferencesUtility.LOGGED_IN_Phone;


public class SaveSharedPreference {

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    public static void setUserName(Context context, String username) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(LOGGED_IN_NAME, username);
        editor.apply();
    }
    public static void setUserEmail(Context context, String username) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(LOGGED_IN_Email, username);
        editor.apply();
    }
    public static void setUserPhone(Context context, String username) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putString(LOGGED_IN_Phone, username);
        editor.apply();
    }


    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }

    public static String getUserName(Context context) {
        return getPreferences(context).getString(LOGGED_IN_NAME, "tt");
    }

    public static String getUserEmail(Context context) {
        return getPreferences(context).getString(LOGGED_IN_Email, "tt");
    }
    public static String getUserPhone(Context context) {
        return getPreferences(context).getString(LOGGED_IN_Phone, "tt");
    }

}
