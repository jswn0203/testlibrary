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

public class SanBezier extends View {

    Paint mPaint;

    int centX, centY;
    PointF start, end, cont1, cont2;
    boolean flag = true;

    public void setChoice(boolean flag) {
        this.flag = flag;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centX = w / 2;
        centY = h / 2;

        start.x = centX + 200;
        start.y = centY;

        end.x = centX - 200;
        end.y = centY;

        cont1.x = centX + 200;
        cont1.y = centY + 200;

        cont2.x = centX - 200;
        cont2.y = centY + 200;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (flag) {
                cont1.x = event.getX();
                cont1.y = event.getY();
            } else {
                cont2.x = event.getX();
                cont2.y = event.getY();
            }
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        canvas.drawPoint(cont1.x, cont1.y, mPaint);
        canvas.drawPoint(cont2.x, cont2.y, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        canvas.drawLine(start.x, start.y, cont1.x, cont1.y, mPaint);
        canvas.drawLine(end.x, end.y, cont2.x, cont2.y, mPaint);
        canvas.drawLine(cont1.x, cont1.y, cont2.x, cont2.y, mPaint);

        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.cubicTo(cont1.x, cont1.y, cont2.x, cont2.y, end.x, end.y);

        canvas.drawPath(path, mPaint);
    }

    public SanBezier(Context context) {
        this(context, null);
    }

    public SanBezier(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SanBezier(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        cont1 = new PointF(0, 0);
        cont2 = new PointF(0, 0);
    }
}
