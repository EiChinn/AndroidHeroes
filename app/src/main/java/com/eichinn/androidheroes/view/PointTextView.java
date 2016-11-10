package com.eichinn.androidheroes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by chenrong on 2016/11/8.
 */

public class PointTextView extends View {
    public PointTextView(Context context) {
        super(context);
        init();
    }

    public PointTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private TextPaint textPaint;
    private Paint pointPaint;
    private String text = "g";
    private Rect rect;
    private int textWidth;
    private int textHeight;

    private Paint rectPaint;
    private RectF rectF;
    private void init() {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(200);
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.RED);

        rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        textWidth = rect.width();
        textHeight = rect.height();
        Log.i("tag", rect.left + "|||");
        Log.i("tag", rect.top + "|||");
        Log.i("tag", rect.right + "|||");
        Log.i("tag", rect.bottom + "|||");
//        textWidth = textPaint.measureText(text);
//        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
//        textHeight = fontMetrics.bottom - fontMetrics.top;

        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setStrokeWidth(10);
        pointPaint.setColor(Color.BLACK);

        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setColor(Color.GREEN);
        rectF = new RectF();
    }

    private int originX;
    private int originY;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        originX = getMeasuredWidth() / 2;
        originY = getMeasuredHeight() / 2;



//        rectF.set(originX - textWidth / 2, originY - textHeight / 2, originX + textWidth / 2, originY + textHeight / 2);
//        rectF.set(originX, originY - textHeight, originX + textWidth, originY);
//        rect.top = originY - textHeight;
//        rect.bottom = originY;
//        rect.right = originX;
//        rect.left = originX + textWidth;
//        rect.right = originX;
//        Log.i("tag", rect.right + "|||");
//        Log.i("tag", rect.left + "|||");
//        canvas.drawRect(rectF, rectPaint);

//        originX = 0;
        //Paint.Align.LEFT
//        textPaint.setTextAlign(Paint.Align.LEFT);
//        originY = 200;
//        rectF.set(originX - textWidth / 2, originY - textHeight / 2, originX + textWidth / 2, originY + textHeight / 2);
//        canvas.drawRect(rectF, rectPaint);
//        canvas.drawText(text, originX - textWidth / 2, originY + textHeight / 2,  textPaint);
//        canvas.drawPoint(originX, originY, pointPaint);
//
//        //Paint.Align.RIGHT
//        textPaint.setTextAlign(Paint.Align.RIGHT);
//        originY = 500;
//        rectF.set(originX - textWidth / 2, originY - textHeight / 2, originX + textWidth / 2, originY + textHeight / 2);
//        canvas.drawRect(rectF, rectPaint);
//        canvas.drawText(text, originX + textWidth / 2, originY + textHeight / 2,  textPaint);
//        canvas.drawPoint(originX, originY, pointPaint);
//
//        //Paint.Align.CENTER
//        textPaint.setTextAlign(Paint.Align.CENTER);
//        originY = 800;
//        rectF.set(originX - textWidth / 2, originY - textHeight / 2, originX + textWidth / 2, originY + textHeight / 2);
//        canvas.drawRect(rectF, rectPaint);
//        canvas.drawText(text, originX, originY + textHeight / 2,  textPaint);
//        canvas.drawPoint(originX, originY, pointPaint);

//        //Paint.Align.LEFT
        textPaint.setTextAlign(Paint.Align.LEFT);
        originY = 200;
        rectF.set(originX - textWidth / 2, originY - textHeight / 2, originX + textWidth / 2, originY + textHeight / 2);
        canvas.drawRect(rectF, rectPaint);
        canvas.drawText(text, originX - textWidth / 2 - rect.left, originY + textHeight / 2 - rect.bottom,  textPaint);
        canvas.drawPoint(originX, originY, pointPaint);

        //Paint.Align.RIGHT
        textPaint.setTextAlign(Paint.Align.RIGHT);
        originY = 500;
        rectF.set(originX - textWidth / 2, originY - textHeight / 2, originX + textWidth / 2, originY + textHeight / 2);
        canvas.drawRect(rectF, rectPaint);
        canvas.drawText(text, originX + textWidth / 2 + rect.left, originY + textHeight / 2 - rect.bottom,  textPaint);
        canvas.drawPoint(originX, originY, pointPaint);

        //Paint.Align.CENTER
        textPaint.setTextAlign(Paint.Align.CENTER);
        originY = 800;
        rectF.set(originX - textWidth / 2, originY - textHeight / 2, originX + textWidth / 2, originY + textHeight / 2);
        canvas.drawRect(rectF, rectPaint);
        canvas.drawText(text, originX, originY + textHeight / 2 - rect.bottom,  textPaint);
        canvas.drawPoint(originX, originY, pointPaint);


        //Paint.Align.LEFT
//        canvas.drawText(text, originX - textWidth / 2 - rect.left, originY + textHeight / 2 - rect.bottom,  textPaint);
        //Paint.Align.RIGHT
//        canvas.drawText(text, originX + textWidth / 2 + rect.left, originY + textHeight / 2 - rect.bottom,  textPaint);
        //Paint.Align.CENTER
//        canvas.drawText(text, originX, originY + textHeight / 2 - rect.bottom,  textPaint);
//        canvas.drawText(text, originX, originY, textPaint);

//        canvas.drawPoint(originX, originY, pointPaint);
    }
}
