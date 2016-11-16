package com.eichinn.androidheroes.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.eichinn.androidheroes.R;

/**
 * Created by chenrong on 2016/11/16.
 */

public class VolumeView extends View {
    public VolumeView(Context context) {
        super(context);
        init(context, null, 0);

    }

    public VolumeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public VolumeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VolumeView);

            rectCount = typedArray.getInt(R.styleable.VolumeView_rectCount, 12);
            rectOffset = typedArray.getInt(R.styleable.VolumeView_rectOffset, 5);

            rectColorStart = typedArray.getColor(R.styleable.VolumeView_rectColorStart, Color.YELLOW);
            rectColorEnd = typedArray.getColor(R.styleable.VolumeView_rectColorEnd, Color.BLUE);

            typedArray.recycle();
        }

        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectPaint.setColor(Color.BLUE);

    }

    private int width;
    private int height;

    private Paint rectPaint;
    private int rectCount;//矩形的个数
    private float rectWidth;//矩形的宽度
    private float currentHeight;//当前矩形的高度
    private int rectOffset;//矩形之间的间隙

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("tag", "onDraw");
        super.onDraw(canvas);

//        //padding
        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        rectWidth = (width - (rectCount - 1.0f) * rectOffset) / rectCount;

        linearGradient = new LinearGradient(0, 0, rectWidth, height, rectColorStart, rectColorEnd, Shader.TileMode.CLAMP);
        rectPaint.setShader(linearGradient);

        for (int i = 0; i < rectCount; i++) {
            currentHeight = (float)(height * Math.random());
            canvas.drawRect(paddingLeft + (rectWidth + rectOffset) * i, height + paddingTop - currentHeight, paddingLeft + rectWidth * (i + 1) + rectOffset * i, height + paddingTop, rectPaint);

        }

        postInvalidateDelayed(300);
    }

    private LinearGradient linearGradient;
    private int rectColorStart;
    private int rectColorEnd;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("tag", "onMeasure");
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int result;

        int measureMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureSize = MeasureSpec.getSize(widthMeasureSpec);

        if (measureMode == MeasureSpec.EXACTLY) {
            result = measureSize;
        } else {
            result = 400;
            if (measureMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, measureSize);
            }
        }

        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int result;

        int measureMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureSize = MeasureSpec.getSize(heightMeasureSpec);

        if (measureMode == MeasureSpec.EXACTLY) {
            result = measureSize;
        } else {
            result = 400;
            if (measureMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, measureSize);
            }
        }

        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i("tag", "onSizeChanged");
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    public int getRectOffset() {
        return rectOffset;
    }

    public void setRectOffset(int rectOffset) {
        this.rectOffset = rectOffset;
        invalidate();
    }

    public int getRectCount() {
        return rectCount;
    }

    public void setRectCount(int rectCount) {
        this.rectCount = rectCount;
        invalidate();
    }

    public int getRectColorStart() {
        return rectColorStart;
    }

    public void setRectColorStart(int rectColorStart) {
        this.rectColorStart = rectColorStart;
        invalidate();
    }

    public int getRectColorEnd() {
        return rectColorEnd;
    }

    public void setRectColorEnd(int rectColorEnd) {
        this.rectColorEnd = rectColorEnd;
        invalidate();
    }
}
