package com.magicmirouf.magicbase.ui.anim;

import android.animation.ObjectAnimator;
import android.view.View;

import com.github.aakira.expandablelayout.Utils;

/**
 * Created by MagicMirouf on 19/12/2016.
 */

public class AnimationBasics {

    public static void createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        animator.start();
    }
}
