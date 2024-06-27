package com.example.myapplication.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.ui.dashboard.DashboardFragment;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

public class Details extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int[]myImages={R.drawable.i0,R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5};

    List<String> rating = DashboardFragment.details.get("Rating");
    List<String> opening = DashboardFragment.details.get("Time of opening");
    List<String> contact = DashboardFragment.details.get("Contact information");
    List<String> address = DashboardFragment.details.get("Address");

    List<String> provision = DashboardFragment.details.get("What it provides");

    Map<String, Integer> index= MapsFragment.index;

    TextView storeName;
    ImageView image;
    TextView mission;
    RatingBar ratings;
    TextView service1;
    TextView service2;
    TextView link;
    TextView hours;
    TextView contactNum;

    Intent intent;
    Bundle bundle;

    double userLat= MapsFragment.userLat;
     double userLong= MapsFragment.userLong;
     double desLat=MapsFragment.desLat;
     double desLong=MapsFragment.desLong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.details);

        intent=getIntent();
        bundle= intent.getExtras();
        String shelterName= bundle.getString("shelter");

        int a= index.get(shelterName);
        storeName = (TextView) findViewById(R.id.storeName);
        image= (ImageView) findViewById(R.id.image);
        mission=(TextView) findViewById(R.id.mission);
        ratings=(RatingBar) findViewById(R.id.rating);
        service1=(TextView) findViewById(R.id.s1);
        service2=(TextView) findViewById(R.id.s2);
        link= (TextView) findViewById(R.id.link);
        hours=(TextView) findViewById(R.id.hours);
        contactNum=(TextView) findViewById(R.id.number);

        storeName.setText(shelterName);
        String drawable="R.drawable.i"+a;
        image.setImageResource(myImages[a]);
        mission.setText(provision.get(a));
        ratings.setRating(Float.parseFloat(rating.get(a)));
//        services need adjusting in the excel sheet
        hours.setText(opening.get(a));
        contactNum.setText(contact.get(a));

    }
    public void close(View view){
        finish();
    }

    public void navigation(View view){
        String uri = "http://maps.google.com/maps?saddr=" + userLat + "," + userLong +
                "&daddr=" + desLat + "," + desLong;

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        intent.setPackage("com.google.android.apps.maps"); // Specify package to ensure it opens in Google Maps

        // Check if the device has the Google Maps app
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If Google Maps is not installed, you can redirect to the Google Maps website
            Uri webUri = Uri.parse("http://maps.google.com/maps?saddr=" + userLat + "," + userLong +
                    "&daddr=" + desLat + "," + desLong);
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
            startActivity(webIntent);
        }
    }
}