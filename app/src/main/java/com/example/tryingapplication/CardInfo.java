package com.example.tryingapplication;

public class CardInfo {


    private String Id;

    private String Name;
    private int Number;
    private CardType cardType;
    private int date;
    private int code;

    public CardInfo(String id, String name, int number, CardType cardType, int date, int code) {
        Id = id;
        Name = name;
        Number = number;
        this.cardType = cardType;
        this.date = date;
        this.code = code;
    }

    public String getId() {

        return Id;
    }

    public void setId(String id) {

        Id = id;
    }

    public String getName() {

        return Name;
    }

    public void setName(String name) {

        Name = name;
    }

    public int getNumber() {

        return Number;
    }

    public void setNumber(int number) {

        Number = number;
    }

    public int getDate() {

        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getCode() {

        return code;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public void setCode(int code) {

        this.code = code;
    }
}
