package com.eichinn.utilities.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.Log;

/**
 * Created by chenrong on 2016/11/13.
 */

public class DrawTextUtils {

    public static void drawText(String text, float coordinateX, float coordinateY, TextPaint textPaint, Canvas canvas) {
        Rect minRect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), minRect);
        int textWidth = minRect.width();
        int textHeight = minRect.height();
        switch (textPaint.getTextAlign()) {
            case CENTER:
                canvas.drawText(text, coordinateX, coordinateY + textHeight / 2 - minRect.bottom, textPaint);
                break;
            case RIGHT:
                canvas.drawText(text, coordinateX + textWidth / 2 + minRect.left, coordinateY + textHeight / 2 - minRect.bottom, textPaint);
                break;
            case LEFT:
            default:
                canvas.drawText(text, coordinateX - textHeight / 2 - minRect.left, coordinateY + textHeight / 2 - minRect.bottom, textPaint);
                break;
        }
    }

    public static Rect getTextMinRect(String text, TextPaint textPaint) {
        Rect minRect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), minRect);
        return minRect;
    }
    /**
     * measure the text's width use its TextBounds
     * @param textPaint
     * @param text
     * @return the text's width
     */
    public static int measureTextWidthUseTextBounds(String text, TextPaint textPaint) {
        //方法一：利用textPaint的getTextBounds方法，可以得到文字所在的最小矩形的宽高
        Rect rect = getTextMinRect(text, textPaint);
        textPaint.getTextBounds(text, 0, text.length(), rect);
        Log.i("tag", "text's width by getTextBounds is: " + rect.width());

        return rect.width();
    }

    /**
     * measure the text's width by the TextPaint'measureText method
     * @param textPaint
     * @param text
     * @return the text's width
     */
    public static float measureTextWidth(String text, TextPaint textPaint) {
        //方法二：利用textPaint的measureText方法
        Log.i("tag", "text's width by measureText is: " + textPaint.measureText(text));
        return textPaint.measureText(text);
    }
    /**
     * measure the text's width by the TextPaint'getTextWidths method
     * @param textPaint
     * @param text
     * @return the text's width
     */
    public static float measureTextWidths(String text, TextPaint textPaint) {
        //方法三：利用textPaint的getTextWidths方法，逐个计算出文字的宽，然后求和
        float[] widths = new float[text.length()];
        textPaint.getTextWidths(text, widths);
        float totalWidth = 0;
        for (float width : widths) {
            totalWidth += width;
        }
        Log.i("tag", "text's width by getTextWidths is: " + totalWidth);
        return totalWidth;
    }

    /**
     * measure the text's height use its TextBounds
     * @param textPaint
     * @param text
     * @return the text's height
     */
    public static int measureTextHeightUseTextBounds(String text, TextPaint textPaint) {
        //方法一：利用textPaint的getTextBounds方法，可以得到文字所在的最小矩形的宽高
        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        Log.i("tag", "text's height by getTextBounds is: " + rect.height());

        return rect.height();
    }

    /**
     * 测量text在五线谱中系统建议的高度
     * @param textPaint
     */
    public static float measureTextRecommendHeight(TextPaint textPaint) {
        //方法二：利用Paint.FontMetrics。
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        Log.i("tag", "text's height by getFontMetrics is: " + (fontMetrics.descent - fontMetrics.ascent));
        return fontMetrics.descent - fontMetrics.ascent;
    }
    /**
     * 测量text在五线谱中系统允许最大的高度
     * @param textPaint
     */
    public static float measureTextMaxHeight(TextPaint textPaint) {
        //方法二：利用Paint.FontMetrics。
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        Log.i("tag", "text's height by getFontMetrics is: " + (fontMetrics.bottom - fontMetrics.top));
        return fontMetrics.bottom - fontMetrics.top;
    }
}
