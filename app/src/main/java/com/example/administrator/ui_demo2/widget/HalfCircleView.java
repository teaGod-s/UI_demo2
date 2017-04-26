package com.example.administrator.ui_demo2.widget;

/**
 * Created by Administrator on 2017/4/26.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.ui_demo2.R;

/**
 * Created by ldf on 16/8/16.
 */
public class HalfCircleView extends View {

    private int maxProgress = 100;
    private float progress = 30f;
    private int circleLineStrokeWidth = 40;
    private final int txtStrokeWidth = 3;
    // 画圆所在的距形区域
    private final RectF progressRectF;
    private final Paint progressPaint;

    public HalfCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressRectF = new RectF();
        progressPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = getRadius();
        drawBackgroundRecf(radius);
        drawProgress(canvas);
        // 绘制进度百分比显示
        drawText(canvas, radius);
    }

    private void drawBackgroundRecf(int radius) {
        // 位置
        progressRectF.left = circleLineStrokeWidth / 2;
        progressRectF.top = circleLineStrokeWidth / 2;
        progressRectF.right = radius - circleLineStrokeWidth / 2;
        progressRectF.bottom = radius - circleLineStrokeWidth / 2;
    }

    private int getRadius() {
        int width = this.getWidth() - circleLineStrokeWidth * 8;
        //因为你要画的是一个半圆,半圆一定是在一个矩形里的
        //而在画半圆的时候其实是在一个正方形里绘制一个整圆的一部分
        //那么我们整圆的半径就是半圆进度条的宽度
        return width;
    }

    private void drawProgress(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(Color.rgb(0xe9, 0xe9, 0xe9));
        progressPaint.setStrokeWidth(circleLineStrokeWidth);
        progressPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(progressRectF, 180, 180, false, progressPaint);
        if (progress > 80) {
            progressPaint.setColor(getResources().getColor(R.color.cornflowerblue));
        } else {
            progressPaint.setColor(getResources().getColor(R.color.cadetblue));
        }
        canvas.drawArc(progressRectF, 180, (progress / maxProgress) * 180, false, progressPaint);
    }

    private void drawText(Canvas canvas, int radius) {
        progressPaint.setStrokeWidth(txtStrokeWidth);
        String progressText = "7000" + "步";
        progressPaint.setTextSize(radius / 8);
        int textWidth = (int) progressPaint.measureText(progressText, 0, progressText.length());
        progressPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(progressText, radius / 2 - textWidth / 2, 300, progressPaint);
        progressPaint.setTextSize(radius / 16);
        progressPaint.setColor(getResources().getColor(R.color.initcolor));
        canvas.drawText("9000步", radius / 2 - textWidth / 2, radius / 2, progressPaint);
    }

    public void setProgress(float progress) {
        this.progress = progress;
        this.invalidate();
    }

    public void setProgressNotInUiThread(float progress) {
        this.progress = progress;
        this.postInvalidate();
    }

    public void setCircleLineStrokeWidth(int circleLineStrokeWidth) {
        this.circleLineStrokeWidth = circleLineStrokeWidth;
        this.invalidate();
    }
}