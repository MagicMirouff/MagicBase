package com.magicmirouf.magicbase.ui.text;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.TextView;

/**
 * Created by MagicMirouf on 21/12/2016.
 */

public class MyEditText extends TextInputEditText implements TextView.OnEditorActionListener{
    public MyEditText(Context context) {
        super(context);
        init(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

    }

    @Override
    public boolean onEditorAction(TextView textView, int action, KeyEvent keyEvent) {
        return editAction(textView,action,keyEvent);
    }

    protected boolean editAction(TextView textView, int action, KeyEvent keyEvent){
        clearFocus();
        return true;
    }
}
