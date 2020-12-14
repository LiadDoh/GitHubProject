package com.example.hw1.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.hw1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {
    Marker marker;
    GoogleMap myGoogleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        //Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);
        //Async map
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(this);
        return view;
    }

    public void showLocationOnMap(double lat, double lon) {
        myGoogleMap.clear();
        LatLng latLng = new LatLng(lat, lon);
        marker = myGoogleMap.addMarker(new MarkerOptions().position(latLng).title("Known Location"));
        myGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myGoogleMap=googleMap;
    }
}