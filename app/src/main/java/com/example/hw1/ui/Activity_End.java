package com.example.hw1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hw1.R;

public class Activity_End extends AppCompatActivity {

    private TextView end_LBL_winner;
    private TextView end_LBL_score;
    private Button end_BTN_again;
    public Button end_BTN_exit;
    private ImageView end_IMG_avatar_1;
    private ImageView end_IMG_avatar_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        findViews();
        initViews();
    }

    private void findViews() {
        end_LBL_winner = findViewById(R.id.end_LBL_winner);
        end_LBL_score = findViewById(R.id.end_LBL_score);
        end_BTN_again = findViewById(R.id.end_BTN_again);
        end_IMG_avatar_2 = findViewById(R.id.end_IMG_avatar_2);
        end_IMG_avatar_1 = findViewById(R.id.end_IMG_avatar_1);
        end_BTN_exit = findViewById(R.id.end_BTN_exit);
    }

    private void initViews() {
        setWinner();
        end_BTN_again.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_End.this, Activity_Main.class);
            startActivity(intent);
            finish();
        });
        end_BTN_exit.setOnClickListener(v -> {
            finish();
        });
    }

    private void setWinner() {
        String[] returnString = getIntent().getStringArrayExtra("winner");
        if (returnString[0].equals("1")) {
            end_LBL_winner.setText(returnString[1]);
            end_LBL_score.setText(returnString[2]);
            end_IMG_avatar_1.setImageResource(R.drawable.scientist);
        } else if (returnString[0].equals("2")) {
            end_LBL_winner.setText(returnString[1]);
            end_LBL_score.setText(returnString[2]);
            end_IMG_avatar_1.setImageResource(R.drawable.werewolf);
        } else {
            end_LBL_winner.setText("Both Wins");
            end_LBL_score.setText(returnString[2]);
            end_IMG_avatar_1.setImageResource(R.drawable.werewolf);
            end_IMG_avatar_1.setImageResource(R.drawable.scientist);
        }
    }


}