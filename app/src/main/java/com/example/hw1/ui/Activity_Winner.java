package com.example.hw1.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.hw1.R;
import com.example.hw1.helpers.Base_Activity;
import com.example.hw1.helpers.LocationHelper;
import com.example.hw1.helpers.UIHelper;

public class Activity_Winner extends Base_Activity  {

    private TextView end_LBL_winner;
    private TextView end_LBL_score;
    private Button end_BTN_again;
    public Button end_BTN_back;
    private ImageView end_IMG_avatar_1;
    private ImageView end_IMG_avatar_2;
    private Button end_BTN_add_player;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        findViews();
        initViews();
    }

    private void findViews() {
        end_LBL_winner = findViewById(R.id.end_LBL_winner);
        end_LBL_score = findViewById(R.id.end_LBL_score);
        end_BTN_again = findViewById(R.id.end_BTN_again);
        end_IMG_avatar_2 = findViewById(R.id.end_IMG_avatar_2);
        end_IMG_avatar_1 = findViewById(R.id.end_IMG_avatar_1);
        end_BTN_back = findViewById(R.id.end_BTN_back);
        triumphal = MediaPlayer.create(this, R.raw.triumphal);
        end_BTN_add_player = findViewById(R.id.end_BTN_add_player);
    }

    private void initViews() {
        end_BTN_add_player.setEnabled(true);
        triumphal.start();
        end_BTN_add_player.setEnabled(true);
        setWinner();

        end_BTN_again.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Winner.this, Activity_Game.class);
            startActivity(intent);
            finish();
        });
        end_BTN_back.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_Winner.this, Activity_Start.class);
            startActivity(intent);
            finish();
        });
        end_BTN_add_player.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                String[] returnString = getIntent().getStringArrayExtra("winner");
                end_BTN_add_player.setEnabled(false);
                LocationHelper.getLocation(this,returnString);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            }

        });
    }

    private void setWinner() {
        String[] returnString = getIntent().getStringArrayExtra("winner");
        if (returnString[0].equals("1")) {
            end_LBL_winner.setText(returnString[1]);
            end_LBL_score.setText(returnString[2]);
            UIHelper.convertIMG(this, R.drawable.scientist, end_IMG_avatar_1);
        } else if (returnString[0].equals("2")) {
            end_LBL_winner.setText(returnString[1]);
            end_LBL_score.setText(returnString[2]);
            UIHelper.convertIMG(this, R.drawable.werewolf, end_IMG_avatar_1);
        } else {
            end_BTN_add_player.setEnabled(false);
            end_LBL_winner.setText("No Winner");
            end_LBL_score.setText(returnString[2]);
            UIHelper.convertIMG(this, R.drawable.werewolf, end_IMG_avatar_1);
            UIHelper.convertIMG(this, R.drawable.scientist, end_IMG_avatar_2);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (triumphal.isPlaying())
            triumphal.pause();
    }
}