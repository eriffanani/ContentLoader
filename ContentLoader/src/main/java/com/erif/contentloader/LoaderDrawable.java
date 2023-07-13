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

public class LoaderDrawable extends Drawable {

    private final Paint paint;
    private final Rect rect;
    private final int[] colors;
    private int width = 0;
    private int height = 0;
    private final Matrix shaderMatrix;
    private ValueAnimator animator;

    public LoaderDrawable() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        rect = new Rect();
        shaderMatrix = new Matrix();
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
            float dropOffLarge = 0.5f;
            float dropOffSmall = 0.001f;
            float[] positions = new float[]{
                    getPosition(dropOffLarge, false),
                    getPosition(dropOffSmall, false),
                    getPosition(dropOffSmall, true),
                    getPosition(dropOffLarge, true)
            };
            LinearGradient gradient = new LinearGradient(
                    0, 0, width, 0,
                    colors, positions, Shader.TileMode.CLAMP
            );
            paint.setShader(gradient);
        }
        float tiltSize = 20f;
        final float tiltSizeTan = (float) Math.tan(Math.toRadians(tiltSize));
        final float translateWidth = width + tiltSizeTan * height;
        final float animatedValue = animator != null ? animator.getAnimatedFraction() : 0f;
        final float dx = dxOffset(-translateWidth, translateWidth, animatedValue);
        shaderMatrix.reset();
        shaderMatrix.setRotate(tiltSize, width / 2f, height / 2f);
        shaderMatrix.postTranslate(dx, 0);
        paint.getShader().setLocalMatrix(shaderMatrix);
        canvas.drawRect(rect, paint);
    }

    private float getPosition(float drop, boolean add) {
        float result;
        if (add) {
            result = Math.max((1f + 0f + drop) / 2f, 0f);
        } else {
            result = Math.max((1f - 0f - drop) / 2f, 0f);
        }
        return result;
    }

    private float dxOffset(float start, float end, float percent) {
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
