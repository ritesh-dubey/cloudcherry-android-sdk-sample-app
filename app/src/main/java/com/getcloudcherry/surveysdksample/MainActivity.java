package com.getcloudcherry.surveysdksample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.getcloudcherry.survey.helper.Constants;
import com.getcloudcherry.survey.helper.GsonHelper;
import com.getcloudcherry.survey.helper.SurveyCC;
import com.getcloudcherry.survey.interfaces.AnalyticsCallBack;
import com.getcloudcherry.survey.model.CustomTextStyle;
import com.getcloudcherry.survey.model.Data;
import com.getcloudcherry.survey.model.SurveyToken;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AnalyticsCallBack {
    private SwipeRevealLayout mSwipeLayout;
    private SwitchCompat mScStaticToken;
    private EditText mEtTokenField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mScStaticToken = (SwitchCompat) findViewById(R.id.scStaticToken);
        mEtTokenField = (EditText) findViewById(R.id.etTokenField);
        mScStaticToken.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                showHideTokenField();
            }
        });
        setDefaultValues();
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
                if (!mScStaticToken.isChecked() || (mScStaticToken.isChecked() && !TextUtils.isEmpty(mEtTokenField.getText()))) {
                    MyData.getInstance(MainActivity.this).setTokenType(mScStaticToken.isChecked());
                    MyData.getInstance(MainActivity.this).setToken(mEtTokenField.getText().toString().trim());
                    initialiseSDK();
                    launchSurveySDK();
                }
                mSwipeLayout.close(true);
            }

            @Override
            public void onSlide(SwipeRevealLayout view, float slideOffset) {
                super.onSlide(view, slideOffset);
                Log.i("Swipe", "slide");
            }
        });
        // Register listener to get Analytics update or can call RecordAnalytics.getInstance().getAnalyticsDataDump()
        SurveyCC.getInstance().setAnalyticsListener(this);
    }

    /**
     * Set default values of token type and stati token value if selected
     */
    private void setDefaultValues() {
        mScStaticToken.setChecked(MyData.getInstance(this).getTokenType());
        mEtTokenField.setText(MyData.getInstance(this).getToken());
    }

    /**
     * Initialise SDK
     */
    private void initialiseSDK() {
        SurveyToken aTokenConfig = new SurveyToken(1, "Chennai");
        ArrayList<Integer> aSmileyRatingSelector = new ArrayList<Integer>() {{
            add(R.drawable.smiley1_selector);
            add(R.drawable.smiley2_selector);
            add(R.drawable.smiley3_selector);
            add(R.drawable.smiley4_selector);
            add(R.drawable.smiley5_selector);
        }};
        ArrayList<Integer> aStarRatingSelector = new ArrayList<Integer>() {{
            add(R.drawable.star1_selector);
            add(R.drawable.star2_selector);
            add(R.drawable.star3_selector);
            add(R.drawable.star4_selector);
            add(R.drawable.star5_selector);
        }};
        if (mScStaticToken.isChecked())
            SurveyCC.initialise(this, "retail", "Cloudcherry@123", MyData.getInstance(this).getToken());
        else
            SurveyCC.initialise(this, "retail", "Cloudcherry@123", aTokenConfig);
        SurveyCC.getInstance().setCustomTextStyle(CustomTextStyle.STYLE_RECTANGLE);
        SurveyCC.getInstance().setSmileyRatingSelector(aSmileyRatingSelector);
        SurveyCC.getInstance().setStarRatingSelector(aStarRatingSelector);
    }

    /**
     * Triggers the SDK method to show survey
     */
    void launchSurveySDK() {
        HashMap<String, Object> aAnswers = new HashMap<>();
        aAnswers.put("prefillMobile", "9880083915");
        aAnswers.put("prefillEmail", "ritesh.dubey37@gmail.com");
        SurveyCC.getInstance().setPreFill(aAnswers);
        HashMap<String, String> aThrottleUniqueId = new HashMap<>();
        aThrottleUniqueId.put("email", "madhur.tewani@wowlabz.com");
        aThrottleUniqueId.put("mobile", "9876543210");
        SurveyCC.getInstance().setThrottleUniqueId(aThrottleUniqueId);
        SurveyCC.getInstance().trigger(this, true);
    }

    /**
     * Show or hide token field to take static token
     */
    void showHideTokenField() {
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mEtTokenField.getWindowToken(), 0);
        mEtTokenField.setVisibility(mScStaticToken.isChecked() ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SurveyCC.getInstance().removeAnalyticsListener(this);
    }

    @Override
    public void onSurveyQuestionSeen(Data iData) {
        Log.i("MainActivity", "Data: " + iData.toString());
    }

    @Override
    public void onUpdatedAnalyticsData(ArrayList<Data> iData) {
        Log.i("MainActivity", "Full Data: " + GsonHelper.toJson(iData));
    }

    @Override
    public void onSurveyExited(AnalyticsCallBack.SurveyExitedAt iSurveyState, String iSurveyToken) {
        Log.i("MainActivity", "Survey State: " + iSurveyState.toString());
        Log.i("MainActivity", "Survey Token: " + iSurveyToken);
    }
}
