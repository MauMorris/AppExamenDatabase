package com.example.idscomercial.examen1.datasource.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.idscomercial.examen1.R;

public final class EnrollmentPreferences {
    public static boolean getEnrollmentDetails(Context context){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);

        String keyActivate = context.getString(R.string.pref_activate_key);
        boolean defaultActivate = true;

        return sp.getBoolean(keyActivate, defaultActivate);
    }

    public static void setEnrollmentDetails(Context context, boolean activateValue){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();

        String keyActivate = context.getString(R.string.pref_activate_key);

        editor.putBoolean(keyActivate, activateValue);
        editor.apply();
    }
}