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

public class ContentLoaderFrameLayout extends FrameLayout {

    private Animation animLoader;
    private Animation animContentShow;
    private Animation animContentHide;
    private final List<View> childViews = new ArrayList<>();
    private int duration = 0;

    public ContentLoaderFrameLayout(@NonNull Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ContentLoaderFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public ContentLoaderFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, null, defStyleAttr, 0);
    }

    public ContentLoaderFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        animLoader = AnimationUtils.loadAnimation(context, R.anim.anim_loader_alpha);
        animContentShow = AnimationUtils.loadAnimation(context, R.anim.anim_loader_show_content);
        animContentHide = AnimationUtils.loadAnimation(context, R.anim.anim_loader_hide_content);
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                    attrs, R.styleable.ContentLoaderFrameLayout, defStyleAttr, defStyleRes
            );
            boolean autoStart;
            try {
                autoStart = typedArray.getBoolean(R.styleable.ContentLoaderFrameLayout_android_autoStart, false);
                duration = typedArray.getInt(R.styleable.ContentLoaderFrameLayout_android_duration, 600);
                animLoader.setDuration(duration);
            } finally {
                typedArray.recycle();
            }
            if (autoStart) {
                this.post(this::start);
            }
        }
    }

    public void start() {
        if (!isShown())
            setVisibility(VISIBLE);
        if (duration > 200)
            playAnim();
    }

    public void startAndHideContent(View contentView) {
        start();
        contentView.setVisibility(GONE);
    }

    public void startAndHideContent(View contentView, boolean smooth) {
        start();
        if (smooth) {
            animContentHide.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {contentView.setVisibility(GONE);}
                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            contentView.startAnimation(animContentHide);
        }
    }

    public void stop() {
        for (View child: childViews) {
            if (child != null) {
                if (child.getAnimation() != null) {
                    child.getAnimation().cancel();
                    child.clearAnimation();
                }
            }
        }
        Animation animHideLoader = AnimationUtils.loadAnimation(getContext(), R.anim.anim_loader_hide_content);
        animHideLoader.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {setVisibility(GONE);}
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        startAnimation(animHideLoader);
    }

    public void stopAndShowContent(View contentView) {
        stop();
        contentView.setVisibility(VISIBLE);
    }

    public void stopAndShowContent(View contentView, boolean smooth) {
        stopAndShowContent(contentView);
        if (smooth) {
            contentView.startAnimation(animContentShow);
        }
    }

    private void playAnim() {
        if (childViews.size() == 0) {
            getChildViews();
        }
        for (View child: childViews) {
            if (child != null)
                child.startAnimation(animLoader);
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