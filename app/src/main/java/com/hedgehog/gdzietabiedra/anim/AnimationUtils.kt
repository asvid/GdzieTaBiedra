package com.hedgehog.gdzietabiedra.anim

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator

/**
 * Created by Windows on 04-03-2015.
 */
object AnimationUtils {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    fun animateFadeIn(holder: RecyclerView.ViewHolder,
                      goesDown: Boolean) {

        val holderHeight = holder.itemView.height
        val holderWidth = holder.itemView.width
        val holderItemView = holder.itemView
        holderItemView.pivotY = (if (goesDown) 0 else holderHeight).toFloat()
        holderItemView.pivotX = (holderWidth / 2).toFloat()
        val animatorSet = AnimatorSet()
        val animatorScaleX = ObjectAnimator
                .ofFloat(holderItemView, "scaleX", .2f, 1f)
        val animatorScaleY = ObjectAnimator
                .ofFloat(holderItemView, "scaleY", .2f, 1f)
        val animatorAlpha = ObjectAnimator
                .ofFloat(holderItemView, "alpha", 0f, 1f)
        animatorAlpha.interpolator = AccelerateInterpolator(1.5f)
        animatorSet.playTogether(animatorAlpha, animatorScaleX, animatorScaleY)
        animatorSet.setDuration(500).interpolator = DecelerateInterpolator(1.1f)
        animatorSet.start()
    }
}
