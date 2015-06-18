package com.hedgehog.gdzietabiedra.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by Windows on 04-03-2015.
 */
public class AnimationUtils {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static void animateFadeIn(RecyclerView.ViewHolder holder, boolean goesDown) {

        if(Build.VERSION.SDK_INT  > Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            int holderHeight = holder.itemView.getHeight();
            int holderWidth = holder.itemView.getWidth();
            View holderItemView = holder.itemView;
            holderItemView.setPivotY(goesDown ? 0 : holderHeight);
            holderItemView.setPivotX(holderWidth / 2);
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(holderItemView, "scaleX", .2f, 1f);
            ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(holderItemView, "scaleY", .2f, 1f);
            ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(holderItemView, "alpha", 0f, 1f);
            animatorAlpha.setInterpolator(new AccelerateInterpolator(1.5f));
            animatorSet.playTogether(animatorAlpha, animatorScaleX, animatorScaleY);
            animatorSet.setDuration(500).setInterpolator(new DecelerateInterpolator(1.1f));
            animatorSet.start();
        }
    }
}
