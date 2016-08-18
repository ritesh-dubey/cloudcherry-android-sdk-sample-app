package com.getcloudcherry.surveysdksample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.getcloudcherry.survey.helper.SurveyCC;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private SwipeRevealLayout mSwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mSwipeLayout = (SwipeRevealLayout) findViewById(R.id.slSDK);
        mSwipeLayout.setSwipeListener(new SwipeRevealLayout.SimpleSwipeListener() {
            @Override
            public void onClosed(SwipeRevealLayout view) {
                super.onClosed(view);
                Log.i("Swipe", "Closed");
            }

            @Override
            public void onOpened(SwipeRevealLayout view) {
                super.onOpened(view);
                Log.i("Swipe", "Opened");
                launchSurveySDK();
                mSwipeLayout.close(true);
            }

            @Override
            public void onSlide(SwipeRevealLayout view, float slideOffset) {
                super.onSlide(view, slideOffset);
                Log.i("Swipe", "slide");
            }
        });
    }

    /**
     * Triggers the SDK method to show survey
     */
    void launchSurveySDK() {
        HashMap<String, Object> aAnswers = new HashMap<>();
        aAnswers.put("prefillMobile", "9880083915");
        aAnswers.put("prefillEmail", "ritesh.dubey37@gmail.com");
        SurveyCC.getInstance().setPreFill(aAnswers);
        SurveyCC.getInstance().trigger();
    }
}
