package com.sjalexander.circleanimation.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.sjalexander.circleanimation.R;

/**
 * Created by stephen.alexander on 27/01/2017.
 */
public class CircleView extends View {

    private int MAX_STROKE_WIDTH = 25;

    private Paint paint;
    private RectF rect;

    private float startPosition;
    private float drawAngle;

    private int desiredWidth;
    private int desiredHeight;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init()
    {
        final int strokeWidth = MAX_STROKE_WIDTH;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        //Circle color
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        //size 200x200 example
        rect = new RectF(strokeWidth, strokeWidth, 200 + strokeWidth, 200 + strokeWidth);

        desiredWidth = desiredHeight = 200 + (strokeWidth*2);

        //Initial Angle (optional, it can be zero)
        startPosition = 0;
        drawAngle = 0;

        setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
    }

    public void resetFull()
    {
        setStrokeWidth(MAX_STROKE_WIDTH);
        setStartPosition(0.0f);
        setDrawAngle(360);
    }

    public void resetEmpty()
    {
        setDrawAngle(0.0f);
        setStrokeWidth(MAX_STROKE_WIDTH);
        setStartPosition(0.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rect, startPosition, drawAngle, false, paint);
    }

    public void setStrokeWidth(float width)
    {
        paint.setStrokeWidth(width);
    }

    public float getStrokeWidth()
    {
        return paint.getStrokeWidth();
    }

    public float getMaxStrokeWidth()
    {
        return MAX_STROKE_WIDTH;
    }

    public float getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(float startPosition) {
        this.startPosition = startPosition;
    }

    public float getDrawAngle() {
        return drawAngle;
    }

    public void setDrawAngle(float drawAngle) {
        this.drawAngle = drawAngle;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        //Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            //Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            //Be whatever you want
            width = desiredWidth;
        }

        //Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            //Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            //Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            //Be whatever you want
            height = desiredHeight;
        }

        //MUST CALL THIS
        setMeasuredDimension(width, height);
    }
}
