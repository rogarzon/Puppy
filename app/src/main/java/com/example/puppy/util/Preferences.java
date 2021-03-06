package com.example.puppy.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    public static final String TOKEN = "token.pref";
    private static final String PREF_NAME = "puppy.preference.name";
    private static Preferences instance = null;
    private static SharedPreferences pref;

    private Preferences(Context context) {
        if (pref == null) {
            pref = context.getApplicationContext()
                    .getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        }
    }

    public static Preferences getInstance(Context context) {
        if (instance == null) {
            instance = new Preferences(context);
        }
        return instance;
    }

    public void setToken(String token) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getToken(){
        return pref.getString(TOKEN, "");
    }
}
