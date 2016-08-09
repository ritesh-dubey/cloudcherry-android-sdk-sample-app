package com.getcloudcherry.surveysdksample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Splash Screen
 */
public class SplashActivity extends AppCompatActivity {
    private boolean isExited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isExited) {
                    finish();
                    Intent aIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(aIntent);
                }
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {
        isExited = true;
        super.onBackPressed();
    }
}
