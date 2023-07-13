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

public class LoaderView extends View {

    private Paint paint;
    private RectF rectF;

    private static final int SQUARE = 0;
    private static final int ROUNDED = 1;
    private static final int CIRCLE = 2;

    private int type = SQUARE;
    private Path path;
    private float[] corners = new float[]{};

    public LoaderView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public LoaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public LoaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        int color = getColor(R.color.color_default_content_loader);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        rectF = new RectF();
        path = new Path();
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                    attrs, R.styleable.LoaderView, defStyleAttr, 0
            );
            try {
                type = typedArray.getInt(R.styleable.LoaderView_viewShape, SQUARE);
                float cornerRadius = typedArray.getDimension(R.styleable.LoaderView_cornerRadius, 0f);
                corners = new float[]{
                        cornerRadius, cornerRadius,
                        cornerRadius, cornerRadius,
                        cornerRadius, cornerRadius,
                        cornerRadius, cornerRadius
                };
                color = typedArray.getColor(R.styleable.LoaderView_android_color, color);
                paint.setColor(color);
            } finally {
                typedArray.recycle();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x = getWidth() / 2f;
        float y = getHeight() / 2f;
        if (type == CIRCLE) {
            canvas.drawCircle(x, y, x, paint);
        } else if (type == ROUNDED) {
            rectF.set(0, 0, getWidth(), getHeight());
            path.addRoundRect(rectF, corners, Path.Direction.CW);
            canvas.drawPath(path, paint);
        } else {
            canvas.drawRect(0f, 0f, getWidth() * 1f, getHeight() * 1f, paint);
        }
    }

    private int getColor(int id) {
        return ContextCompat.getColor(getContext(), id);
    }

}
