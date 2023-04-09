package com.example.tryingapplication;

public class CardType {
    private String card;
    private int imgId;

    public CardType(String card, int imgId) {
        this.card = card;
        this.imgId = imgId;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}