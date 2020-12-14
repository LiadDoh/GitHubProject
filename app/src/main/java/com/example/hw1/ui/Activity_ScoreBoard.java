package com.example.hw1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.hw1.Interfaces.CallBackTop;
import com.example.hw1.R;
import com.example.hw1.helpers.Base_Activity;

public class Activity_ScoreBoard extends Base_Activity {

    private Button score_BTN_back;

    private Fragment_List list_fragment;
    private Fragment_Map map_fragment;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        list_fragment = new Fragment_List();
        list_fragment.setCallBack_top(callBackTop);
        getSupportFragmentManager().beginTransaction().add(R.id.score_LAY_list, list_fragment).commit();
        map_fragment = new Fragment_Map();
        getSupportFragmentManager().beginTransaction().add(R.id.score_LAY_map, map_fragment).commit();
        findViews();
        initViews();
    }


    private void initViews() {

        score_BTN_back.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_ScoreBoard.this, Activity_Start.class);
            startActivity(intent);
            finish();
        });


    }



    private void findViews() {
        score_BTN_back = findViewById(R.id.score_BTN_back);
    }

    private CallBackTop callBackTop = new CallBackTop() {
        @Override
        public void showLocation(double lat, double lon) {
            map_fragment.showLocationOnMap(lat,lon);
        }
    };
}
