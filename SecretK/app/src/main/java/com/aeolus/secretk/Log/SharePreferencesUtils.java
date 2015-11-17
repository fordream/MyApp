package com.aeolus.secretk.Log;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tulin on 2015/7/25.
 */
public class SharePreferencesUtils {
    public static String SP_NAME = "config";
    private static SharedPreferences sp;

    public static void saveBoolean(Context context,String key, boolean value){
        if(sp == null){
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue){
        if(sp == null){
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defaultValue);
    }
}
