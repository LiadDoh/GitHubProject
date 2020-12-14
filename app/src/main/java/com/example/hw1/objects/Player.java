package com.example.hw1.objects;

public class Player implements Comparable<Player> {
    private String name;
    private int score;
    private double latitude;
    private double longitude;

    public Player(String name, int score, double latitude, double longitude) {
        this.name = name;
        this.score = score;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getScore() {
        return score;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Name: " +
                name +" score " + score;
    }

    public int compareTo(Player o) {
        return (score - o.score);
    }
}
