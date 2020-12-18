package com.example.hw1.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.core.app.ActivityCompat;

import com.example.hw1.objects.Player;
import com.example.hw1.objects.TopTen;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;
import static com.example.hw1.helpers.Constants.SP_FILE;
import static com.example.hw1.helpers.Constants.TOP_TEN;


public class ScoreBoardHelper {
    public final static int REQUEST_CODE = 101;

    public static void getLocation(Context context, String[] arr) {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                Player player = new Player(arr[1], Integer.parseInt(arr[2]), location.getLatitude(), location.getLongitude());
                topTenUpdate(context,player);
            }
        });
    }
    private static void topTenUpdate(Context context, Player player) {
        SharedPreferences prefs = context.getApplicationContext().getSharedPreferences(SP_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        TopTen topTenList = generateData(prefs, gson);
        if (topTenList == null)
            topTenList = new TopTen();
        TopTen.addPlayer(topTenList,player);
        String json = gson.toJson(topTenList);
        editor.putString(TOP_TEN, json);
        editor.apply();
    }

    private static TopTen generateData(SharedPreferences prefs, Gson gson) {
        String json = prefs.getString(TOP_TEN, "");
        return gson.fromJson(json, TopTen.class);

    }
}
