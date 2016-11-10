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
 * Created by chenrong on 2016/11/9.
 */

public class DrawTextView extends View{

    public DrawTextView(Context context) {
        super(context);
        init();
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private TextPaint textPaint;
    private String text = "abg12一二";
    private Rect minRect;
    private int textWidth;
    private int textHeight;

    private Paint rectPaint;
    private RectF rectF;

    private Paint pointPaint;
    private void init() {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(200);
        textPaint.setTypeface(Typeface.DEFAULT);
        textPaint.setColor(Color.RED);
        minRect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), minRect);
        textWidth = minRect.width();
        textHeight = minRect.height();
        Log.i("tag", minRect.left + "");
        Log.i("tag", minRect.top + "");
        Log.i("tag", minRect.right + "");
        Log.i("tag", minRect.bottom + "");

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

        //以(0,0)为源点绘制文字的话，文字则会完全落入到这个最小矩形里面。
        textPaint.setTextAlign(Paint.Align.LEFT);
        originX = 0;
        originY = 0;
        canvas.drawRect(minRect, rectPaint);
        canvas.drawText(text, originX, originY,  textPaint);
        canvas.drawPoint(originX, originY, pointPaint);

        /**
         *      _ _ _ _ _
         *     |         |
         *     |  (x,y)  |
         *     |----·    |height
         *     |    |    |
         *     |    |    |
         *     ·- - · - -·
         *        width
         *
         *  以指定点(x,y)为中心绘制文字，方框为待绘制文字所在的最小矩形，则矩形的各顶点均可以通过简单的逻辑换算得到。
         *  下面分别以Paint.Align.LEFT，Paint.Align.RIGHT和Paint.Align.CENTER三种方式绘制文字，
         *  其绘制源点分别为矩形底边的左右中三点
         */

        originX = getMeasuredWidth() / 2;
        //Paint.Align.LEFT
        textPaint.setTextAlign(Paint.Align.LEFT);
        originY = 200;
        rectF.set(originX - textWidth / 2, originY - textHeight / 2, originX + textWidth / 2, originY + textHeight / 2);
        canvas.drawRect(rectF, rectPaint);
        canvas.drawText(text, originX - textWidth / 2 - minRect.left, originY + textHeight / 2 - minRect.bottom,  textPaint);
        canvas.drawPoint(originX, originY, pointPaint);

        //Paint.Align.RIGHT
        textPaint.setTextAlign(Paint.Align.RIGHT);
        originY = 500;
        rectF.set(originX - textWidth / 2, originY - textHeight / 2, originX + textWidth / 2, originY + textHeight / 2);
        canvas.drawRect(rectF, rectPaint);
        canvas.drawText(text, originX + textWidth / 2 + minRect.left, originY + textHeight / 2 - minRect.bottom,  textPaint);
        canvas.drawPoint(originX, originY, pointPaint);

        //Paint.Align.CENTER
        textPaint.setTextAlign(Paint.Align.CENTER);
        originY = 800;
        rectF.set(originX - textWidth / 2, originY - textHeight / 2, originX + textWidth / 2, originY + textHeight / 2);
        canvas.drawRect(rectF, rectPaint);
        canvas.drawText(text, originX, originY + textHeight / 2 - minRect.bottom,  textPaint);
        canvas.drawPoint(originX, originY, pointPaint);

    }
}
