package com.khurana.nikhil.tuhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
                new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, LoginScreen.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }


}
