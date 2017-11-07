package com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects;

/**
 * Created by DaNN on 21/10/2017.
 */

public class Contact {
    public String phone;
    public String formattedPhone;
    public String twitter;
    public String facebook;
    public String facebookUsername;
    public String facebookName;

    public Contact(){
        this.phone="";
        this.formattedPhone="";
        this.twitter="";
        this.facebook="";
        this.facebookUsername="";
        this.facebookName="";
    }

    public Contact(String phone){
        this.phone = phone;
    }

    public Contact(String phone, String formattedPhone){
        this.phone = phone;
        this.formattedPhone = formattedPhone;
    }

    public Contact(String phone, String formattedPhone, String facebookName){
        this.phone = phone;
        this.formattedPhone = formattedPhone;
        this.facebookName = facebookName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {

        if(phone==null)
            phone="empty";
        this.phone = phone;
    }

    public String getFormattedPhone() {
        return formattedPhone;
    }

    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }

    public void setFacebookUsername(String facebookUsername) {
        this.facebookUsername = facebookUsername;
    }

    public String getFacebookName() {
        return facebookName;
    }

    public void setFacebookName(String facebookName) {
        this.facebookName = facebookName;
    }


}
