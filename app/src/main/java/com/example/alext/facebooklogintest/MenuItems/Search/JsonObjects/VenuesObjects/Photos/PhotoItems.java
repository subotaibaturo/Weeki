package com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Photos;

/**
 * Created by DaNN on 28/10/2017.
 */

public class PhotoItems {
    public String prefix;
    public String suffix;

    public String WITDTH;
    public String HEIGHT;

    public PhotoItems(){
        this.prefix="";
        this.suffix="";
        this.WITDTH="";
        this.HEIGHT="";

    }

    public String getIMG(String w, String h){
       return prefix+w+"x"+h+suffix;
    }


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getWITDTH() {
        return WITDTH;
    }

    public void setWITDTH(String WITDTH) {
        this.WITDTH = WITDTH;
    }

    public String getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(String HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

}
