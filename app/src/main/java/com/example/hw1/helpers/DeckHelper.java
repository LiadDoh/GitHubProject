package com.example.hw1.helpers;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hw1.objects.Card;

import java.util.ArrayList;
import java.util.Collections;

public class DeckHelper {

    public static int player1_score = 0;
    public static int player2_score = 0;

    public static ArrayList<Card> createDeck(Context context, char[] LETTERS) {
        ArrayList<Card> deck = new ArrayList<Card>();
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
        ((Base_Activity) context).main_BAR_progress.setMax(deck.size() / 2);
        return deck;
    }


    public static void drawCards(Context context, ArrayList<Card> deck) {

        Card first_card = deck.get(0);
        deck.remove(0);
        Card second_card = deck.get(0);
        deck.remove(0);
        UIHelper.convertIMG(context,first_card.getId(), ((Base_Activity) context).main_IMG_player_card1);
        UIHelper.convertIMG(context,second_card.getId(), ((Base_Activity) context).main_IMG_player_card2);
        if (first_card.getValue() > second_card.getValue()) {
            player1_score++;
            ((Base_Activity) context).main_LBL_score1.setText("" + player1_score);
        } else if (first_card.getValue() < second_card.getValue()) {
            player2_score++;
            ((Base_Activity) context).main_LBL_score2.setText("" + player2_score);
        }
    }


}
