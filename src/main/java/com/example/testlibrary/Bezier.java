package com.example.testlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 极速蜗牛 on 2017/6/14 0014.
 */

public class Bezier extends View {


    Paint mPaint;
    private int centerX, centerY;
    private PointF start, end, control;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        start.x = 200;
        start.y = 0;

        end.x = -200;
        end.y = 0;

        control.x = 0;
        control.y = 400;
    }

    public Bezier(Context context) {
        this(context, null);
    }

    public Bezier(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Bezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control = new PointF(0, 0);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = event.getX();
        control.y = getY();
        invalidate();
        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);

        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(control.x, control.y, 20, mPaint);
        canvas.drawCircle(start.x, start.y, 20, mPaint);
        canvas.drawCircle(end.x, end.y, 20, mPaint);

        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);

        canvas.drawLine(start.x, start.y, control.x, control.y, mPaint);
        canvas.drawLine(end.x, end.y, control.x, control.y, mPaint);


        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.quadTo(control.x, control.y, end.x, end.y);

        canvas.drawPath(path, mPaint);

    }
}
