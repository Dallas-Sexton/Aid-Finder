package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.ui.dashboard.DashboardFragment;

public class transitionFragment extends FragmentActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState==null)
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new DashboardFragment()).commit();

    }

}
