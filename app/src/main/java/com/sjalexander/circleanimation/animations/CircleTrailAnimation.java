package com.sjalexander.circleanimation.animations;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.sjalexander.circleanimation.views.CircleView;


/**
 * Created by stephen.alexander on 27/01/2017.
 */

public class CircleTrailAnimation extends Animation {

    private CircleView circle;

    private float oldDrawAngle;
    private float oldStartPosition;
    private float startAngle;
    private float maxStrokeWidth;

    private boolean strokeAnimation = false;
    private boolean overshoot = false;

    public CircleTrailAnimation(CircleView circle) {
        this.circle = circle;
        this.circle.resetEmpty();

        this.oldStartPosition = circle.getStartPosition();
        this.oldDrawAngle = circle.getDrawAngle();
        maxStrokeWidth = circle.getStrokeWidth();
        init();
    }

    public void setAnimateStroke(boolean animateStroke)
    {
        this.strokeAnimation = animateStroke;
    }

    public void setOvershoot(boolean overshoot)
    {
        this.overshoot = overshoot;
    }

    private void init()
    {
        startAngle = oldStartPosition;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {

        startAngle = oldStartPosition + ((360 - oldStartPosition) * interpolatedTime);
        circle.setStartPosition(startAngle);

        float drawAngle = oldDrawAngle + ((360 - oldDrawAngle - startAngle) * interpolatedTime * 3);

        if (!overshoot && drawAngle + startAngle > 360)
        {
            drawAngle = 360 - startAngle;
        }

        if (strokeAnimation) {
            animateStroke(interpolatedTime);
        }

        circle.setDrawAngle(drawAngle);
        circle.requestLayout();
    }

    private void animateStroke(float interpolatedTime)
    {
        if (interpolatedTime < 0.5)
        {
            circle.setStrokeWidth(maxStrokeWidth * interpolatedTime * 2);
        }
        else
        {
            circle.setStrokeWidth(maxStrokeWidth * (1 - interpolatedTime) * 2);
        }
    }


    @Override
    public void reset() {
        init();
    }
}
