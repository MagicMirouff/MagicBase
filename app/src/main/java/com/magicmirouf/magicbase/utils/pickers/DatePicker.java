package com.magicmirouf.magicbase.utils.pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by MagicMirouf on 08/01/16.
 */
public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static final String TAG = "DatePicker";
    private onDateSelected onDateSelected;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        // Create a new instance of DatePickerDialog and return it
        return datePickerDialog;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
        Calendar dateSelected = GregorianCalendar.getInstance();
        dateSelected.set(year,month,day,0,0,0);
        dateSelected.set(Calendar.MILLISECOND,0);
        onDateSelected.dateSelected(dateSelected);
    }

    public interface onDateSelected{
        void dateSelected(Calendar calendar);
    }

    public void setOnDateSelected(DatePicker.onDateSelected onDateSelected) {
        this.onDateSelected = onDateSelected;
    }
}
