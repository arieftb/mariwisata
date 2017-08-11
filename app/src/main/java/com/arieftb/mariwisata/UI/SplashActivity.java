package com.arieftb.mariwisata.UI;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.arieftb.mariwisata.R;

public class SplashActivity extends AppCompatActivity {

    private static final int TIME_SPLASH = 3 * 1000; // 3 detik


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mIntent = new Intent(SplashActivity.this, ListWisataActivity.class);
                startActivity(mIntent);

                finish();
            }
        },TIME_SPLASH);
    }
}
