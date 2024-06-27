package com.example.myapplication.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsFragment extends Fragment {
    private GoogleMap googleMap;
    List<LatLng> markerDets= DashboardFragment.markers;

    List<String> shelter = DashboardFragment.details.get("Shelter Name");
    static Map<String, Integer> index= new HashMap<>();
    Intent intent;
    Bundle bundle;

    static double userLat;
    static double userLong;
    static double desLat;
    static double desLong;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap map) {
            googleMap=map;
            googleMap.getUiSettings().setZoomControlsEnabled(true);


            if (pendingUserLocation != null) {
                setUserLocation(pendingUserLocation.latitude, pendingUserLocation.longitude);
                pendingUserLocation = null; // Reset pendingUserLocation after handling it
            }
            loadLocations();

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    // Handle marker click here
                    String shelterName = (String) marker.getTag();
                    LatLng latLng= marker.getPosition();
                    desLat=latLng.latitude;
                    desLong=latLng.longitude;
                    if (shelterName != null) {
                        // Do something with the clicked shelterName, e.g., navigate to another fragment
                        onMarkerClicked(shelterName);
                    }
                    return true; // Return true to consume the event and prevent default behavior
                }
            });
        }
    };
    private void onMarkerClicked(String shelterName) {
//        send the sheltername as an intent
        intent=new Intent(requireActivity(), Details.class);
        bundle= new Bundle();
        bundle.putString("shelter",shelterName);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    private LatLng pendingUserLocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);

            Bundle args = getArguments();
            if (args != null && args.containsKey("latitude") && args.containsKey("longitude")) {
                double latitude = args.getDouble("latitude");
                double longitude = args.getDouble("longitude");
                setUserLocation(latitude, longitude);
            }
        }
    }
    public void setUserLocation(double latitude, double longitude) {
        if (googleMap != null) {
            userLat= latitude;
            userLong= longitude;
            LatLng userLocation = new LatLng(latitude, longitude);
            Log.d("MAP_DEBUG", "Setting user location: " + latitude + ", " + longitude);
            googleMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 10));
        } else {
            // If onMapReady hasn't been called yet, store the location for later use
            pendingUserLocation = new LatLng(latitude, longitude);
        }
    }
    public void loadLocations(){
        for(int i=0;i<markerDets.size();i++){
           LatLng location=markerDets.get(i);
           String shelterName=shelter.get(i);

           index.put(shelterName,i);

            Marker marker = googleMap.addMarker(new MarkerOptions().position(location).title(shelterName));
            marker.setTag(shelterName);
            marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.build));


        }
    }

}