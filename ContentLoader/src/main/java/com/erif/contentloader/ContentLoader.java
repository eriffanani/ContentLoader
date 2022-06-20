package com.erif.contentloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ContentLoader extends FrameLayout {

    private Animation anim;
    private final List<View> childViews = new ArrayList<>();

    public ContentLoader(@NonNull Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ContentLoader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public ContentLoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, null, defStyleAttr, 0);
    }

    public ContentLoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        anim = AnimationUtils.loadAnimation(context, R.anim.anim_loader_alpha);
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                    attrs, R.styleable.ContentLoader, defStyleAttr, defStyleRes
            );
            boolean autoStart;
            try {
                autoStart = typedArray.getBoolean(R.styleable.ContentLoader_android_autoStart, false);
            } finally {
                typedArray.recycle();
            }
            if (autoStart) {
                this.post(this::start);
            }
        }
    }

    void start() {
        if (!isShown()) {
            setVisibility(VISIBLE);
        }
        playAnim();
    }

    void stop() {
        for (View child: childViews) {
            child.getAnimation().cancel();
            child.clearAnimation();
        }
    }

    void stopAndHide() {
        stop();
        setVisibility(GONE);
    }

    private void playAnim() {
        if (childViews.size() == 0) {
            getChildViews();
        }
        for (View child: childViews) {
            child.startAnimation(anim);
        }
    }

    private void getChildViews() {
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i<childCount; i++) {
                childViews.add(getChildAt(i));
            }
        }
    }

}
