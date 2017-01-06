package com.magicmirouf.magicbase.utils.config;

import android.util.Log;

/**
 * Created by sylva on 13/08/2016.
 */
public class Config {

    public static int DEBUG_LEVEL = 1;

    public static void Log(String tag, String message){
        if (message !=null && tag !=null) {
            if (DEBUG_LEVEL == 1)
                Log.d(tag, message);
        }
    }

    public static void LogE(String tag, String message) {
        if (message !=null && tag !=null) {
            if (DEBUG_LEVEL == 1)
                Log.e(tag, message);
        }
    }
}
