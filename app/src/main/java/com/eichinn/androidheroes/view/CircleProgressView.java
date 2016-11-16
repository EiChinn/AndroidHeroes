package com.eichinn.androidheroes.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.eichinn.androidheroes.R;
import com.eichinn.utilities.view.DrawTextUtils;

/**
 * Created by chenrong on 2016/11/7.
 */

public class CircleProgressView extends View {
    public CircleProgressView(Context context) {
        super(context);
        init(null, null, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        if (context != null && attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);

            //实心圆相关
            circleColor = typedArray.getColor(R.styleable.CircleProgressView_circleColor, Color.parseColor("#ff00ddff"));

            //文字相关
            centerTestColor = typedArray.getColor(R.styleable.CircleProgressView_centerTextColor, Color.BLACK);
            centerTextSize = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_centerTextSize, 50);
            Log.i("tag", String.valueOf(centerTextSize));
            centerText = typedArray.getString(R.styleable.CircleProgressView_centerText);
            if (TextUtils.isEmpty(centerText)) {
                centerText = "centerText";
            }

            //圆弧相关
            arcColor = typedArray.getColor(R.styleable.CircleProgressView_arcColor, Color.parseColor("#ff00ddff"));
            arcWidth = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_arcWidth, 50);
            arcAngle = typedArray.getInt(R.styleable.CircleProgressView_arcAngle, 0);

            typedArray.recycle();
        }

        if (defStyleAttr != 0) {

        }

        //实心圆相关
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(circleColor);

        //文字相关
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(centerTextSize);
        textPaint.setColor(centerTestColor);

        //圆弧相关
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaint.setColor(arcColor);
        arcPaint.setStrokeWidth(arcWidth);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcRectF = new RectF();

    }

    private int width;
    private int height;

    //实心圆相关
    private int circleX;
    private int circleY;
    private int radius;
    private Paint circlePaint;
    private int circleColor;

    //文字相关
    private String centerText;
    private int centerTextSize;
    private int centerTestColor;
    private TextPaint textPaint;

    //圆弧相关
    private RectF arcRectF;
    private Paint arcPaint;
    private int arcColor;
    private int arcWidth;
    private int arcAngle;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();

        //padding
        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();
        width = width - paddingLeft - paddingRight;
        height = height - paddingTop - paddingBottom;

        //画实心圆
        circleX = width / 2;
        circleY = height / 2;
        radius = Math.min(width / 4, height / 4);
        canvas.drawCircle(circleX, circleY, radius, circlePaint);

        //画圆弧
        arcRectF.set(circleX - radius * 2 * 0.9f, circleY - radius * 2 * 0.9f, circleX + radius * 2 * 0.9f, circleY + radius * 2 * 0.9f);
        canvas.drawArc(arcRectF, 0, arcAngle % 360, false, arcPaint);

        //画文字
        DrawTextUtils.drawText(centerText, circleX, circleY, textPaint, canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int result;

        int measureMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureSize = MeasureSpec.getSize(widthMeasureSpec);

        if (measureMode == MeasureSpec.EXACTLY) {
            result = measureSize;
        } else {
            result = 500;
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
            result = 500;
            if (measureMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, measureSize);
            }
        }

        return result;


    }


    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        circlePaint.setColor(circleColor);
        invalidate();
    }

    public String getCenterText() {
        return centerText;
    }

    public void setCenterText(String centerText) {
        this.centerText = centerText;
        invalidate();
    }

    public int getCenterTextSize() {
        return centerTextSize;
    }

    public void setCenterTextSize(int centerTextSize) {
        this.centerTextSize = centerTextSize;
        textPaint.setTextSize(centerTextSize);
        invalidate();
    }

    public int getCenterTestColor() {
        return centerTestColor;
    }

    public void setCenterTestColor(int centerTestColor) {
        this.centerTestColor = centerTestColor;
        textPaint.setColor(centerTestColor);
        invalidate();
    }

    public int getArcColor() {
        return arcColor;
    }

    public void setArcColor(int arcColor) {
        this.arcColor = arcColor;
        arcPaint.setColor(arcColor);
        invalidate();
    }

    public int getArcWidth() {
        return arcWidth;
    }

    public void setArcWidth(int arcWidth) {
        this.arcWidth = arcWidth;
        arcPaint.setStrokeWidth(arcWidth);
        invalidate();
    }

    public int getArcAngle() {
        return arcAngle;
    }

    public void setArcAngle(int arcAngle) {
        this.arcAngle = arcAngle;
        invalidate();
    }
}
