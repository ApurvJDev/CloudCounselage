package com.example.cloudcounselageconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity{
    ImageView logo;
    LottieAnimationView lottie;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.logo);
        lottie = findViewById(R.id.lottie);

        logo.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        lottie.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        Intent intent = new Intent(SplashScreen.this,IntroActivity.class);
        new Handler().postDelayed(() -> {
            startActivity(intent);
            finish();
        },4000);
    }
}