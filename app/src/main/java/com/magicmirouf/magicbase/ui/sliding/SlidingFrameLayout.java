package com.magicmirouf.magicbase.ui.sliding;

/**
 * Created by MagicMirouf on 13/01/16.
 */

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.BounceInterpolator;
import android.widget.FrameLayout;

public class SlidingFrameLayout extends FrameLayout implements View.OnTouchListener{

    private float yFraction = 0;
    private float xFraction = 0;

    private boolean top = false;
    private float y0,yPosition;
    private int delta = 50;

    public SlidingFrameLayout(Context context) {
        super(context);
        init(context);
    }

    public SlidingFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlidingFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setOnTouchListener(this);
        final View slider = this;
        slider.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                yPosition = slider.getY()+slider.getHeight()-delta;
                slider.setY(yPosition);
                slider.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
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

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            y0 = event.getY();
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE){
            float delta = event.getY() - y0;
            if (delta < 150) {
                y0 = event.getY();
                setY(getY() + delta);
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP){
            dismissView();
        }
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE){
            dismissView();
        }
        if (event.getAction() == MotionEvent.ACTION_CANCEL){
            dismissView();
        }
        return true;
    }

    private void dismissView() {

        ObjectAnimator animator;

        if (isTop()){
            if (getY() > yPosition - (getHeight()-delta)){
                animator = ObjectAnimator.ofFloat(this,"Y",this.getY(),yPosition);
                setTop(false);
            } else {
                animator = ObjectAnimator.ofFloat(this,"Y",this.getY(),yPosition-(getHeight()-delta));
                setTop(true);
            }
        } else {
            if (getY() < yPosition + delta){
                animator = ObjectAnimator.ofFloat(this,"Y",this.getY(),yPosition-(getHeight()-delta));
                setTop(true);
            } else {
                animator = ObjectAnimator.ofFloat(this,"Y",this.getY(),yPosition);
                setTop(false);
            }
        }

        animator.setDuration(800)
                .setInterpolator(new BounceInterpolator());

        animator.start();
    }

    public void setBottom(){
        if (getY()!=yPosition) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(this, "Y", this.getY(), yPosition);
            animator.setDuration(800)
                    .setInterpolator(new BounceInterpolator());

            animator.start();
            setTop(false);
        }
    }

}