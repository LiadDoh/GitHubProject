package com.example.hw1.ui;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.hw1.R;
import com.example.hw1.helpers.Base_Activity;
import com.example.hw1.helpers.DeckHelper;
import com.example.hw1.helpers.UIHelper;
import com.example.hw1.objects.Card;

import java.util.ArrayList;


public class Activity_Game extends Base_Activity {
    private static final int POPUP_ACTIVITY_REQUEST_CODE = 527;//Snake Case
    private final String TAG = "ActivityMain";//Paskal Case
    public TextView game_LBL_name_1;
    public TextView game_LBL_name_2;
    public Button game_BTN_step;
    private ImageView game_IMG_dice;
    private ArrayList<Card> deck;
    private final char[] LETTERS = {'c', 'd', 'h', 's'};
    private AnimationDrawable frameAnimation;
    private boolean firstClick = true;
    private MediaPlayer shuffle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.d(TAG, "onCreate:Start");
        findViews();
        initViews();
    }


    public void findViews() {
        game_LBL_name_1 = findViewById(R.id.game_LBL_name_1);
        game_LBL_name_2 = findViewById(R.id.game_LBL_name_2);
        game_LBL_score1 = findViewById(R.id.game_LBL_score1);
        game_LBL_score2 = findViewById(R.id.game_LBL_score2);
        game_BTN_step = findViewById(R.id.game_BTN_step);
        game_IMG_player_card1 = findViewById(R.id.game_IMG_player_card1);
        game_IMG_player_card2 = findViewById(R.id.game_IMG_player_card2);
        game_IMG_dice = findViewById(R.id.game_IMG_dice);
        game_BAR_progress = findViewById(R.id.game_BAR_progress);
        claps= MediaPlayer.create(this,R.raw.claps);
        shuffle= MediaPlayer.create(this,R.raw.shuffle);
    }

    public void initViews() {
        UIHelper.convertIMG(this, R.drawable.aces, game_IMG_player_card1);
        UIHelper.convertIMG(this, R.drawable.aces, game_IMG_player_card2);
        deck = DeckHelper.createDeck(this, LETTERS);
        game_BTN_step.setOnClickListener(v -> {
            if (firstClick) {
                firstClick = false;
                startPopUpActivity();
            } else {
                if(shuffle.isPlaying())
                    shuffle.pause();
                game_BTN_step.setEnabled(false);
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
                game_LBL_name_1.setText(returnString[0]);
                game_LBL_name_2.setText(returnString[1]);
                game_BTN_step.setText("Start");
                shuffle.start();
            }
            else
                firstClick = true;
        }
        else
            firstClick = true;
    }

    private void rollDice() {
        game_IMG_dice.setImageResource(R.drawable.animation);
        frameAnimation = (AnimationDrawable) game_IMG_dice.getDrawable();
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
                game_BAR_progress.incrementProgressBy(1);
                rollDice();
            } else {
                claps.start();
                game_BTN_step.setEnabled(true);
                game_BTN_step.setText("Finish");
                game_BTN_step.setOnClickListener(v -> startEndGameActivity());
            }
        }, timeBetweenChecks);
    }

    private void startEndGameActivity() {
        Intent intent = new Intent(Activity_Game.this, Activity_Winner.class);
        if (DeckHelper.player1_score > DeckHelper.player2_score) {
            intent.putExtra("winner", new String[]{"1", game_LBL_name_1.getText().toString(), game_LBL_score1.getText().toString()});
        } else if (DeckHelper.player1_score < DeckHelper.player2_score) {
            intent.putExtra("winner", new String[]{"2", game_LBL_name_2.getText().toString(), game_LBL_score2.getText().toString()});
        } else {
            intent.putExtra("winner", new String[]{"Both Wins", game_LBL_name_1.getText().toString(), game_LBL_name_2.getText().toString(), game_LBL_score2.getText().toString()});
        }
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        DeckHelper.player1_score = 0;
        DeckHelper.player2_score = 0;
        game_BAR_progress.setProgress(0);
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
        if(claps.isPlaying())
            claps.pause();
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