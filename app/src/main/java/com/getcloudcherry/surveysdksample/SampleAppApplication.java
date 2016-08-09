package com.getcloudcherry.surveysdksample;

import android.app.Application;

import com.getcloudcherry.survey.helper.SurveyCC;

/**
 * Created by riteshdubey on 8/8/16.
 */
public class SampleAppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SurveyCC.initialise(this, "ROHITH-50000");
    }
}
