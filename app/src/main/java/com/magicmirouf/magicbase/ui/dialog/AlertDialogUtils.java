package com.magicmirouf.magicbase.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by MagicMirouf on 19/12/2016.
 */

public class AlertDialogUtils {

    public static void showalertDialog(Context context, String message, DialogInterface.OnClickListener okClick){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("Oui",okClick)
                .setNegativeButton("Non",null)
                .create().show();
    }

}
