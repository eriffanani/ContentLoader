package com.erif.contentloader;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentLoaderDrawable extends Drawable {

    private final Paint paint;
    private final Rect rect;
    private final int[] colors;
    private int width = 0;
    private int height = 0;
    private final Matrix mShaderMatrix;
    private ValueAnimator animator;

    public ContentLoaderDrawable() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        rect = new Rect();
        mShaderMatrix = new Matrix();
        int baseColor = 0xFF000000;
        int highlightColor = 0x66FAFAFA;
        colors = new int[]{
                baseColor,
                highlightColor,
                highlightColor,
                baseColor
        };
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        width = bounds.width();
        height = bounds.height();
        rect.set(0, 0, width, height);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (paint.getShader() == null) {
            float intensity = 0f;
            float dropOff = 0.5f;
            float[] positions = new float[]{
                    Math.max((1f - intensity - dropOff) / 2f, 0f),
                    Math.max((1f - intensity - 0.001f) / 2f, 0f),
                    Math.min((1f + intensity + 0.001f) / 2f, 1f),
                    Math.min((1f + intensity + dropOff) / 2f, 1f)
            };
            LinearGradient gradient = new LinearGradient(
                    0, 0, width, 0,
                    colors, positions, Shader.TileMode.CLAMP
            );
            paint.setShader(gradient);
        }
        float tilt = 20f;
        final float tiltTan = (float) Math.tan(Math.toRadians(tilt));
        final float translateWidth = width + tiltTan * height;
        final float animatedValue = animator != null ? animator.getAnimatedFraction() : 0f;
        final float dx = offset(-translateWidth, translateWidth, animatedValue);
        final float dy = 0;
        mShaderMatrix.reset();
        mShaderMatrix.setRotate(tilt, width / 2f, height / 2f);
        mShaderMatrix.postTranslate(dx, dy);
        paint.getShader().setLocalMatrix(mShaderMatrix);
        canvas.drawRect(rect, paint);
    }

    private float offset(float start, float end, float percent) {
        return start + (end - start) * percent;
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void start() {
        int repeatDelay = 200;
        int animationDuration = 1200;
        float additional = (float) repeatDelay / animationDuration;
        animator = ValueAnimator.ofFloat(0f, 1f + additional);
        animator.setDuration(animationDuration);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(valueAnimator -> invalidateSelf());
        animator.start();
    }

    public void maybeStartShimmer() {
        if (animator != null
                && !animator.isStarted()
                && getCallback() != null) {
            animator.start();
        }
    }

    public void stop() {
        if (animator != null)
            animator.cancel();
    }

}
