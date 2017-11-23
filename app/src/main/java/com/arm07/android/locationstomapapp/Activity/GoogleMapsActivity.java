package com.arm07.android.locationstomapapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.arm07.android.locationstomapapp.Model.Location;
import com.arm07.android.locationstomapapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapsActivity extends AppCompatActivity {

    TextView tvName, tvLatitude, tvLongitude, tvAddress, tvArrivalTime;
    double latitude, longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        tvName = findViewById(R.id.tvName);
        tvLatitude = findViewById(R.id.tvLatitude);
        tvLongitude = findViewById(R.id.tvLongitude);
        tvAddress = findViewById(R.id.tvAddress);
        tvArrivalTime = findViewById(R.id.tvArrivalTime);

        Location location = getIntent().getExtras().getParcelable("location");
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        setTitle(location.getName());
        tvName.setText(location.getName());
        tvLatitude.setText("Latitude:" + String.valueOf(latitude));
        tvLongitude.setText("Longitude: "+ String.valueOf(longitude));
        tvArrivalTime.setText("Arrival time: \n"+location.getArrivalTime());
        tvAddress.setText(location.getAddress());

        //display map in a fragment.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                LatLng gps = new LatLng(latitude, longitude);
                googleMap.addMarker(new MarkerOptions().position(gps));

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(gps));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gps,14.0f));
            }
        });
    }
}
