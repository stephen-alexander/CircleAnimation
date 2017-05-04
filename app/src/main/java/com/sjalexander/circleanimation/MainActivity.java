package com.sjalexander.circleanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;

import com.sjalexander.circleanimation.animations.CirclePulseAnimation;
import com.sjalexander.circleanimation.animations.CircleTrailAnimation;
import com.sjalexander.circleanimation.views.CircleView;

public class MainActivity extends AppCompatActivity {

    private CircleView trailCircle;
    private CircleView pulseCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trailCircle = (CircleView) findViewById(R.id.trail_circle_view);
        pulseCircle = (CircleView) findViewById(R.id.pulse_circle_view);

        showPulseCircle();
        showTrailCircle();
    }

    private void showTrailCircle()
    {
        trailCircle.clearAnimation();
        CircleTrailAnimation animation = new CircleTrailAnimation(trailCircle);
        animation.setAnimateStroke(true);
        animation.setOvershoot(true);
        animation.setDuration(3000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        trailCircle.startAnimation(animation);
    }

    private void showPulseCircle()
    {
        pulseCircle.clearAnimation();

        CirclePulseAnimation animation = new CirclePulseAnimation(pulseCircle);
        animation.setMinStrokeWidth(0.1f);
        animation.setDuration(2000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.RESTART);
        pulseCircle.startAnimation(animation);
    }
}
