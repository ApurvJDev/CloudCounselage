package com.example.cloudcounselageconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class onBoardingFragment4 extends Fragment {

    Button getStarted;
    Activity context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = getActivity();

        return (ViewGroup) inflater.inflate(R.layout.on_boarding_fragment4,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();

        getStarted = context.findViewById(R.id.btn_getStarted);
        getStarted.setOnClickListener(v -> {

            Intent intent = new Intent(context,AuthActivity.class);
            startActivity(intent);
        });
    }
}
