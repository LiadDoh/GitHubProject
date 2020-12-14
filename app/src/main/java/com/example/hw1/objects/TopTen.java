package com.example.hw1.objects;

import java.util.ArrayList;
import java.util.Collections;

public class TopTen {

    public ArrayList<Player> players;

    public TopTen() {
    }

    public ArrayList<Player> getRecords() {
        return players;
    }


    public void addPlayer(Player player) {
        int max = 10;
        if (players.size() == max) {
            Player temp = players.get(max - 1);
            if (temp.getScore() >= player.getScore())
                return;
            else {
                players.remove(max - 1);
                players.add(player);
            }

        } else
            players.add(player);
        players.sort(Collections.reverseOrder());
    }
}