package com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects;

/**
 * Created by DaNN on 28/10/2017.
 */

public class Price {
    public int tier;
    public String message ="";
    public String currency ="";

    public Price (){}

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
