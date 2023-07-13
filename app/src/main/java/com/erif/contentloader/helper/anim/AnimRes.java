package com.erif.contentloader.helper.anim;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.erif.contentloader.R;

public class AnimRes {

    public static LayoutAnimationController fadeIn(Context context) {
        return AnimationUtils.loadLayoutAnimation(context, R.anim.fade_in_layout);
    }

}
