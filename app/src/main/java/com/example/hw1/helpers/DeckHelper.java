package com.example.hw1.helpers;

import android.content.Context;

import com.example.hw1.objects.Card;
import com.example.hw1.ui.Activity_Main;

import java.util.ArrayList;
import java.util.Collections;

public class DeckHelper {

    public static int player1_score = 0;
    public static int player2_score = 0;

    public static ArrayList<Card> createDeck(Context context, ArrayList<Card> deck, char[] LETTERS) {
        deck = new ArrayList<Card>();
        int drawableResourceId;
        String name;
        for (char c : LETTERS) {
            for (int i = 2; i < 15; i++) {
                name = c + "_" + i;
                drawableResourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
                deck.add(new Card(i, drawableResourceId));
            }
        }
        Collections.shuffle(deck);
        return deck;
    }


    public static void drawCards(Context context, ArrayList<Card> deck) {

        Card first_card = deck.get(0);
        deck.remove(0);
        Card second_card = deck.get(0);
        deck.remove(0);
        ((Activity_Main) context).main_IMG_player_card1.setImageResource(first_card.getId());
        ((Activity_Main) context).main_IMG_player_card2.setImageResource(second_card.getId());
        if (first_card.getValue() > second_card.getValue()) {
            player1_score++;
            ((Activity_Main) context).main_LBL_score1.setText("" + player1_score);
        } else if (first_card.getValue() < second_card.getValue()) {
            player2_score++;
            ((Activity_Main) context).main_LBL_score2.setText("" + player2_score);
        }
        ((Activity_Main) context).main_BTN_step.setEnabled(true);
    }
}
