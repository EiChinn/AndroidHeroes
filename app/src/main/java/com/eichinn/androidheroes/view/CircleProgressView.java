package com.eichinn.androidheroes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by chenrong on 2016/11/7.
 */

public class CircleProgressView extends View {
    private Paint paint;
    public CircleProgressView(Context context) {
        super(context);
        init();
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        sweepValue = 125;

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.RIGHT);
        textPaint.setTypeface(Typeface.DEFAULT);
        showText = "CircleProgressView";
        textSizeRect = new Rect();
        textPaint.getTextBounds(showText, 0, showText.length(), textSizeRect);
        textWidth = textSizeRect.width();
        textHeight = textSizeRect.height();

        dotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dotPaint.setColor(Color.BLACK);
        dotPaint.setStrokeWidth(10);

    }

    private int width;
    private int height;

    private int circleX;
    private int circleY;
    private int radius;

    private RectF arcRectF;
    private int sweepValue;

    private Paint textPaint;
    private String showText;
    private Rect textSizeRect;
    private int textWidth;
    private int textHeight;

    private Paint dotPaint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        //画实心圆
        circleX = width / 2;
        circleY = height / 2;
        radius = Math.min(width / 4, height / 4);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(circleX, circleY, radius, paint);

        //画圆弧
        arcRectF = new RectF(circleX - 2 * radius, circleY - 2 * radius, circleX + 2 * radius, circleY + 2 * radius);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawArc(arcRectF, 0, sweepValue, false, paint);

        //画文字
        canvas.drawText(showText, circleX, circleY + textHeight / 2, textPaint);

        canvas.drawPoint(circleX, circleY, dotPaint);

    }

    public void setSweepValue(int sweepValue) {
        if (sweepValue != 0) {
            this.sweepValue = sweepValue;
            invalidate();
        }

    }
}
