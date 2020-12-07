package com.example.hw1.ui;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.hw1.R;
import com.example.hw1.helpers.Base_Activity;
import com.example.hw1.helpers.DeckHelper;
import com.example.hw1.objects.Card;

import java.util.ArrayList;


public class Activity_Game extends Base_Activity {
    private static final int POPUP_ACTIVITY_REQUEST_CODE = 527;//Snake Case
    private final String TAG = "ActivityMain";//Paskal Case
    public TextView main_LBL_name_1;
    public TextView main_LBL_name_2;
    private ProgressBar main_BAR_progress;
    public Button main_BTN_step;
    private ImageView main_IMG_dice;
    private ArrayList<Card> deck;
    private final char[] LETTERS = {'c', 'd', 'h', 's'};
    private AnimationDrawable frameAnimation;
    private boolean firstClick = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViews();
        initViews();
        Log.d(TAG, "onCreate:Start");
    }


    public void findViews() {
        main_LBL_name_1 = findViewById(R.id.main_LBL_name_1);
        main_LBL_name_2 = findViewById(R.id.main_LBL_name_2);
        main_LBL_score1 = findViewById(R.id.main_LBL_score1);
        main_LBL_score2 = findViewById(R.id.main_LBL_score2);
        main_BTN_step = findViewById(R.id.main_BTN_step);
        main_IMG_player_card1 = findViewById(R.id.main_IMG_player_card1);
        main_IMG_player_card2 = findViewById(R.id.main_IMG_player_card2);
        main_IMG_dice = findViewById(R.id.main_IMG_dice);
        main_BAR_progress = findViewById(R.id.main_BAR_progress);
    }

    public void initViews() {
        deck = DeckHelper.createDeck(this, LETTERS);
        main_BTN_step.setOnClickListener(v -> {
            if (firstClick) {
                firstClick = false;
                startPopUpActivity();
            } else {
                main_BTN_step.setEnabled(false);
                rollDice();

            }
        });
    }

    public void startPopUpActivity() {
        startActivityForResult(new Intent(Activity_Game.this, Activity_Popup.class), 527);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // check that it is the SecondActivity with an OK result
        if (requestCode == POPUP_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                String[] returnString = data.getStringArrayExtra("keyName");

                // set text view with string
                main_LBL_name_1.setText(returnString[0]);
                main_LBL_name_2.setText(returnString[1]);
                main_BTN_step.setText("Start");
            }
        }
    }

    private void rollDice() {
        main_IMG_dice.setImageResource(R.drawable.animation);
        frameAnimation = (AnimationDrawable) main_IMG_dice.getDrawable();
        frameAnimation.start();
        checkIfAnimationDone();
    }


    private void checkIfAnimationDone() {
        int timeBetweenChecks = 300;
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (frameAnimation.getCurrent() != frameAnimation.getFrame(frameAnimation.getNumberOfFrames() - 1)) {
                checkIfAnimationDone();
            } else if (!deck.isEmpty()) {
                DeckHelper.drawCards(Activity_Game.this, deck);
                main_BAR_progress.incrementProgressBy(1);
                rollDice();
            } else {
                main_BTN_step.setEnabled(true);
                main_BTN_step.setText("Finish");
                main_BTN_step.setOnClickListener(v -> startEndGameActivity());
            }
        }, timeBetweenChecks);
    }

    private void startEndGameActivity() {
        Intent intent = new Intent(Activity_Game.this, Activity_End.class);
        if (DeckHelper.player1_score > DeckHelper.player2_score) {
            intent.putExtra("winner", new String[]{"1", main_LBL_name_1.getText().toString(), main_LBL_score1.getText().toString()});
        } else if (DeckHelper.player1_score < DeckHelper.player2_score) {
            intent.putExtra("winner", new String[]{"2", main_LBL_name_2.getText().toString(), main_LBL_score2.getText().toString()});
        } else {
            intent.putExtra("winner", new String[]{"Both Wins", main_LBL_name_1.getText().toString(), main_LBL_name_2.getText().toString(), main_LBL_score2.getText().toString()});
        }
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        DeckHelper.player1_score = 0;
        DeckHelper.player2_score = 0;
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