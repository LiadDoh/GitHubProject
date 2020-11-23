package com.example.hw1.objects;

public class Card {

    private int value;
    private int id;

    public Card() {
    }

    public Card(int value, int id) {
        this.value = value;
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }
}
