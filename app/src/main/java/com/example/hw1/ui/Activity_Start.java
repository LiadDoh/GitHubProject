package com.example.hw1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.hw1.R;
import com.example.hw1.helpers.Base_Activity;


public class Activity_Start extends Base_Activity {

    private Button start_BTN_start_game;
    private Button start_BTN_scoreboard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViews();
        initViews();
    }


    public void findViews() {

        start_BTN_start_game = findViewById(R.id.start_BTN_start_game);
        start_BTN_scoreboard = findViewById(R.id.start_BTN_scoreboard);
    }

    public void initViews() {
        start_BTN_start_game.setOnClickListener(v -> startGame());
        start_BTN_scoreboard.setOnClickListener(v -> showScoreBoard());

    }

    private void showScoreBoard() {
        Intent intent = new Intent(this, Activity_Score.class);
        startActivity(intent);
    }

    private void startGame() {
        Intent intent = new Intent(this, Activity_Game.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}