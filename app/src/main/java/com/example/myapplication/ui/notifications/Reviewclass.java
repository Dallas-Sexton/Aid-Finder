package com.example.myapplication.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Reviewclass extends AppCompatActivity{



    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewiew_page1);
        Log.d("Reviewclass", "Reviewclass activity started");
        RatingBar ratingBar = findViewById(R.id.ratingBar);



    }

    public void skip(View v){
        finish();
    }
    public void submit(View v){
        Toast.makeText(this,"Review has been submitted",Toast.LENGTH_SHORT).show();
        finish();
    }

}
