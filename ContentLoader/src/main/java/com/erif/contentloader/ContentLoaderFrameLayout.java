package com.erif.contentloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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
    private int loaderType = TYPE_BLINK;
    private ContentLoaderDrawable drawable;

    private static final int TYPE_BLINK = 0;
    private static final int TYPE_SHIMMER = 1;

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
        setWillNotDraw(false);

        animLoader = new AlphaAnimation(1f, 0.4f);
        animLoader.setDuration(600);
        animLoader.setRepeatCount(Animation.INFINITE);
        animLoader.setRepeatMode(Animation.REVERSE);

        animContentShow = new AlphaAnimation(0f, 1f);
        animContentShow.setDuration(600);

        animContentHide = new AlphaAnimation(1f, 0f);
        animContentHide.setDuration(600);

        drawable = new ContentLoaderDrawable();
        drawable.setCallback(this);
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                    attrs, R.styleable.ContentLoaderFrameLayout, defStyleAttr, defStyleRes
            );
            boolean autoStart;
            try {
                autoStart = typedArray.getBoolean(R.styleable.ContentLoaderFrameLayout_android_autoStart, false);
                loaderType = typedArray.getInt(R.styleable.ContentLoaderFrameLayout_loaderType, TYPE_BLINK);
                duration = typedArray.getInt(R.styleable.ContentLoaderFrameLayout_android_duration, loaderType == TYPE_SHIMMER ? 1200 : 600);
                if (loaderType == TYPE_SHIMMER) {
                    setLayerType(LAYER_TYPE_HARDWARE, null);
                } else {
                    animLoader.setDuration(duration);
                }
            } finally {
                typedArray.recycle();
            }
            if (autoStart) {
                this.post(this::start);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final int width = getWidth();
        final int height = getHeight();
        if (drawable != null)
            drawable.setBounds(0, 0, width, height);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (drawable != null)
            drawable.maybeStartShimmer();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (drawable != null)
            drawable.draw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (drawable != null)
            drawable.stop();
    }

    @Override
    protected boolean verifyDrawable(@NonNull Drawable who) {
        return super.verifyDrawable(who) || who == drawable;
    }

    public void start() {
        if (!isShown())
            setVisibility(VISIBLE);
        if (duration > 200) {
            if (loaderType == TYPE_SHIMMER) {
                drawable.start();
            } else {
                playAnim();
            }
        }
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
        if (loaderType == TYPE_SHIMMER) {
            drawable.stop();
        } else {
            for (View child: childViews) {
                if (child != null) {
                    if (child.getAnimation() != null) {
                        child.getAnimation().cancel();
                        child.clearAnimation();
                    }
                }
            }
        }
        Animation animHideLoader = new AlphaAnimation(1f, 0f);
        animHideLoader.setDuration(600);
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
        if (smooth)
            contentView.startAnimation(animContentShow);
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
