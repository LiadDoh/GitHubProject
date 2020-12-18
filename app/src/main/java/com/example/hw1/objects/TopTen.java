package com.example.hw1.objects;

import java.util.ArrayList;
import java.util.Collections;

public class TopTen {

    public ArrayList<Player> players=new ArrayList<Player>();;

    public TopTen() {
    }

    public ArrayList<Player> getRecords() {
        return players;
    }


    public static void addPlayer(TopTen topTen, Player player) {
        int max = 10;
        if (topTen.players.size() == max) {
            Player temp = topTen.players.get(max - 1);
            if (temp.getScore() >= player.getScore())
                return;
            else {
                topTen.players.remove(max - 1);
                topTen.players.add(player);
            }

        } else
            topTen.players.add(player);
        topTen.players.sort(Collections.reverseOrder());
    }
}