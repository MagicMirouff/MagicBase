package com.magicmirouf.magicbase.ui.sliding;

/**
 * Created by MagicMirouf on 13/01/16.
 */

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.widget.LinearLayout;

public class SlidingLinearLayout extends LinearLayout {

    private float yFraction = 0;
    private float xFraction = 0;

    private boolean top = false;
    private float y0,yPosition;
    private int delta = 50;
    private boolean expand;

    public SlidingLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public SlidingLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlidingLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
    }

    private ViewTreeObserver.OnPreDrawListener preDrawListener = null;

    public void setYFraction(float fraction) {

        this.yFraction = fraction;

        if (getHeight() == 0) {
            if (preDrawListener == null) {
                preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        getViewTreeObserver().removeOnPreDrawListener(preDrawListener);
                        setYFraction(yFraction);
                        return true;
                    }
                };
                getViewTreeObserver().addOnPreDrawListener(preDrawListener);
            }
            return;
        }

        float translationY = getHeight() * fraction;
        setTranslationY(translationY);
    }

    public float getYFraction() {
        return this.yFraction;
    }

    public void setXFraction(float fraction) {

        this.xFraction = fraction;

        if (getHeight() == 0) {
            if (preDrawListener == null) {
                preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        getViewTreeObserver().removeOnPreDrawListener(preDrawListener);
                        setXFraction(xFraction);
                        return true;
                    }
                };
                getViewTreeObserver().addOnPreDrawListener(preDrawListener);
            }
            return;
        }

        float translationX = getWidth() * fraction;
        setTranslationX(translationX);
    }

    public float getXFraction() {
        return this.xFraction;
    }


    public void collapse() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "yFraction", 1f, 0);
        animator.setDuration(800)
                .setInterpolator(new BounceInterpolator());

        animator.start();
        expand = false;
    }

    public void expand() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "yFraction", 0f, 1f);
        animator.setDuration(800)
                .setInterpolator(new BounceInterpolator());

        animator.start();
        expand = true;
    }

    public void toggle(){
        if (expand){
            collapse();
        } else {
            expand();
        }
    }
}