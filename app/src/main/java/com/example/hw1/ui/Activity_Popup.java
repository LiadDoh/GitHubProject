package com.example.hw1.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hw1.R;

public class Activity_Popup extends AppCompatActivity {
    private EditText popup_ET_player_1;
    private EditText popup_ET_player_2;
    private Button popup_BTN_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        findViews();
        initViews();
    }

    private void initViews() {
        popup_BTN_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringToPassBack_1 = popup_ET_player_1.getText().toString();
                String stringToPassBack_2 = popup_ET_player_2.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("keyName",new String[]{stringToPassBack_1,stringToPassBack_2} );
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void findViews() {
        popup_ET_player_1 = findViewById(R.id.popup_ET_player_1);
        popup_ET_player_2 = findViewById(R.id.popup_ET_player_2);
        popup_BTN_done = findViewById(R.id.popup_BTN_done);
    }
}