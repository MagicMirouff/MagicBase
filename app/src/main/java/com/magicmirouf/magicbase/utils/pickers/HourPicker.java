package com.magicmirouf.magicbase.utils.pickers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by MagicMirouf on 08/01/16.
 */
public class HourPicker extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    public static final String TAG = "HourPicker";

    private onHourSelected onHourSelected;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = 0;

        // Create a new instance of TimePickerDialog and return it
        AlertDialog dialog = new TimePickerDialog(getActivity(), this, hour, minute,true);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        return dialog;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        DecimalFormat decimalFormat = new DecimalFormat("00");
        onHourSelected.hourSelected(String.format("%s:%s", decimalFormat.format(hourOfDay), decimalFormat.format(minute)));
    }

    public interface onHourSelected{
        void hourSelected(String formatHour);
    }

    public void setOnHourSelected(HourPicker.onHourSelected onHourSelected) {
        this.onHourSelected = onHourSelected;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        getDialog().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
