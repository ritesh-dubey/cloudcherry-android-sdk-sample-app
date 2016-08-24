package com.getcloudcherry.surveysdksample;

import android.app.Application;

import com.getcloudcherry.survey.helper.SurveyCC;
import com.getcloudcherry.survey.model.SurveyToken;

/**
 * Created by riteshdubey on 8/8/16.
 */
public class SampleAppApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SurveyToken aTokenConfig = new SurveyToken(1, "mobile");
        SurveyCC.initialise(this, "rohith", "Test@123", aTokenConfig);
    }
}
