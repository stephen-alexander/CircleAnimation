package com.sjalexander.circleanimation.animations;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.sjalexander.circleanimation.views.CircleView;


/**
 * Created by stephen.alexander on 27/01/2017.
 */

public class CirclePulseAnimation extends Animation {

    private CircleView circle;
    private float maxStrokeWidth;
    private float minStrokeWidth;

    public CirclePulseAnimation(CircleView circle) {
        this.circle = circle;
        this.circle.resetFull();

        maxStrokeWidth = circle.getStrokeWidth();
        minStrokeWidth = 0.1f;
    }

    public void setMinStrokeWidth(float minStrokeWidth)
    {
        this.minStrokeWidth = minStrokeWidth;
        maxStrokeWidth = maxStrokeWidth-minStrokeWidth;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {

        if (interpolatedTime < 0.5f)
        {
            float width = maxStrokeWidth * interpolatedTime * 2.0f;
            circle.setStrokeWidth(width+minStrokeWidth);
        }
        else
        {
            float width = maxStrokeWidth * (1.0f - interpolatedTime) * 2.0f;
            circle.setStrokeWidth(width+minStrokeWidth);
        }

        circle.requestLayout();
    }

}
