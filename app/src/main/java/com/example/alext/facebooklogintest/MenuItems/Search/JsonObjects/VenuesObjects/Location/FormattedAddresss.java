package com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Location;

/**
 * Created by DaNN on 21/10/2017.
 */

public class FormattedAddresss {
    public String format0;
    public String format1;
    public String format2;

    public FormattedAddresss (String format0, String format1, String format2){
        this.format0 = format0;
        this.format1= format1;
        this.format2 =format2;
    }

    public FormattedAddresss (String format0, String format1){
        this.format0 = format0;
        this.format1= format1;

    }

    public FormattedAddresss (String format0){
        this.format0 = format0;

    }

    public FormattedAddresss(){}

    public String getFormat0() {
        return format0;
    }

    public void setFormat0(String format0) {
        this.format0 = format0;
    }

    public String getFormat1() {
        return format1;
    }

    public void setFormat1(String format1) {
        this.format1 = format1;
    }

    public String getFormat2() {
        return format2;
    }

    public void setFormat2(String format2) {
        this.format2 = format2;
    }


}
