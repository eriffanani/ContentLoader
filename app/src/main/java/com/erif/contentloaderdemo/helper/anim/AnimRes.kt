package com.erif.contentloaderdemo.helper.anim

import android.content.Context
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.erif.contentloaderdemo.R

class AnimRes {

    companion object {

        fun fadeIn(context: Context?): LayoutAnimationController? {
            return AnimationUtils.loadLayoutAnimation(context, R.anim.fade_in_layout)
        }

    }

}