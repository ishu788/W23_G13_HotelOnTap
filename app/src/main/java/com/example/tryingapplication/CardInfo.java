package com.example.tryingapplication;

public class CardInfo {
    private String NameOnCard;
    private String CardNumber;
    private String ExpiryDate;
    private String CardType;
    private String securityCode;

    public CardInfo() {
    }

    public CardInfo(String nameOnCard, String cardNumber, String expiryDate, String cardType, String securityCode) {
        NameOnCard = nameOnCard;
        CardNumber = cardNumber;
        ExpiryDate = expiryDate;
        CardType = cardType;
        this.securityCode = securityCode;
    }

    public String getNameOnCard() {
        return NameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        NameOnCard = nameOnCard;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return ExpiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        ExpiryDate = expiryDate;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
    @Override
    public String toString() {
        return "CardInfo{" +
                "  NameOnCard='" + NameOnCard + '\'' +
                ", CardNumber='" + CardNumber + '\'' +
                ", ExpiryDate='" + ExpiryDate + '\'' +
                ", securityCode='" + securityCode + '\'' +
                '}';
    }
}
