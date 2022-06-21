package com.erif.contentloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class ContentLoaderViewBanner extends View {

    private Paint paint;
    private RectF rectFLeft;
    private RectF rectFRight;
    private RectF rectF;

    private static final int NONE = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int LEFT_AND_RIGHT = 3;

    private int peep = NONE;
    private Path pathLeft;
    private Path pathRight;
    private Path path;
    private float[] cornersLeft = new float[]{};
    private float[] cornersRight = new float[]{};
    private float[] corners = new float[]{};
    private float padding = 0f;
    private float peepPaddingVertical = 0f;

    public ContentLoaderViewBanner(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public ContentLoaderViewBanner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public ContentLoaderViewBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public ContentLoaderViewBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        int color = getColor(R.color.color_default_content_loader);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        rectFLeft = new RectF();
        rectFRight = new RectF();
        rectF = new RectF();
        pathLeft = new Path();
        pathRight = new Path();
        path = new Path();
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                    attrs, R.styleable.ContentLoaderViewBanner, defStyleAttr, defStyleRes
            );
            try {
                peep = typedArray.getInt(R.styleable.ContentLoaderViewBanner_bannerPeep, NONE);
                padding = typedArray.getDimension(R.styleable.ContentLoaderViewBanner_android_padding, 0f);
                peepPaddingVertical = typedArray.getDimension(R.styleable.ContentLoaderViewBanner_peepPaddingVertical, 0f);
                float cornerSize = typedArray.getDimension(R.styleable.ContentLoaderViewBanner_cornerRadius, 0f);
                cornersLeft = new float[]{
                        0f, 0f,
                        cornerSize, cornerSize,
                        cornerSize, cornerSize,
                        0f, 0f
                };
                cornersRight = new float[]{
                        cornerSize, cornerSize,
                        0f, 0f,
                        0f, 0f,
                        cornerSize, cornerSize
                };
                corners = new float[]{
                        cornerSize, cornerSize,
                        cornerSize, cornerSize,
                        cornerSize, cornerSize,
                        cornerSize, cornerSize
                };
                color = typedArray.getColor(R.styleable.ContentLoaderViewBanner_android_color, color);
                paint.setColor(color);
            } finally {
                typedArray.recycle();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (peep == LEFT_AND_RIGHT) {
            drawLeftAndRight(canvas);
        } else if (peep == LEFT) {
            drawLeft(canvas);
        } else if (peep == RIGHT) {
            drawRight(canvas);
        } else {
            drawSingle(canvas);
        }
    }

    private void drawSingle(Canvas canvas) {
        rectF.set(getLeft() + padding, getTop() + padding, getRight() - padding, getBottom() - padding);
        path.addRoundRect(rectF, corners, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }

    private void drawLeft(Canvas canvas) {
        float width = getWidth() / 25f;
        rectFLeft.set(getLeft(), getTop() + peepPaddingVertical, width, getBottom() - peepPaddingVertical);
        pathLeft.addRoundRect(rectFLeft, cornersLeft, Path.Direction.CW);
        canvas.drawPath(pathLeft, paint);

        rectF.set(getLeft() + width + padding, getTop() + padding, getRight() - padding, getBottom() - padding);
        path.addRoundRect(rectF, corners, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }

    private void drawRight(Canvas canvas) {
        float width = getWidth() / 25f;
        rectFRight.set(getRight() - width, getTop() + peepPaddingVertical, getRight(), getBottom() - peepPaddingVertical);
        pathRight.addRoundRect(rectFRight, cornersRight, Path.Direction.CW);
        canvas.drawPath(pathRight, paint);

        rectF.set(getLeft() + padding, getTop() + padding, getRight() - width - padding, getBottom() - padding);
        path.addRoundRect(rectF, corners, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }

    private void drawLeftAndRight(Canvas canvas) {
        float width = getWidth() / 25f;
        rectFLeft.set(getLeft(), getTop() + peepPaddingVertical, width, getBottom() - peepPaddingVertical);
        pathLeft.addRoundRect(rectFLeft, cornersLeft, Path.Direction.CW);
        canvas.drawPath(pathLeft, paint);

        rectFRight.set(getRight() - width, getTop() + peepPaddingVertical, getRight(), getBottom() - peepPaddingVertical);
        pathRight.addRoundRect(rectFRight, cornersRight, Path.Direction.CW);
        canvas.drawPath(pathRight, paint);

        rectF.set(getLeft() + width + padding, getTop() + padding, getRight() - width - padding, getBottom() - padding);
        path.addRoundRect(rectF, corners, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }

    private int getColor(int id) {
        return ContextCompat.getColor(getContext(), id);
    }

}
