package com.example.cloudcounselageconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IntroActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    private static final int NUM_PAGES = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // initialize firebase Auth
        mAuth = FirebaseAuth.getInstance();


        ViewPager2 viewPager2 = findViewById(R.id.pager);

        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // check if user is signed in
        user = mAuth.getCurrentUser();
        // if user signed in we update UI accordingly
        Intent intent;
        if(user != null) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter {

        public ScreenSlidePagerAdapter(IntroActivity introActivity) {
            super(introActivity);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new onBoardingFragment1();
                case 1:
                    return new onBoardingFragment2();
                case 2:
                    return new onBoardingFragment3();
                case 3:
                    return new onBoardingFragment4();
                default:
                    return null;
                }
            }
        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }
}





