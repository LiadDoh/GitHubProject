package com.example.hw1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.hw1.R;
import com.example.hw1.helpers.Base_Activity;

public class Activity_Score extends Base_Activity {

    private Button score_BTN_back;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Fragment map_fragment = new MapFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.score_LAY_map, map_fragment).commit();
        findViews();
        initViews();
    }

    private void initViews() {
        score_BTN_back.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Score.this, Activity_Start.class);
            startActivity(intent);
            finish();
        });
    }

    private void findViews() {
        score_BTN_back = findViewById(R.id.score_BTN_back);
    }
}
