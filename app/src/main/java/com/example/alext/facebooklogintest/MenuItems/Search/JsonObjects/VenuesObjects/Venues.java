package com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects;


import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Categories.Categories;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Hour.Hours;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Location.LocationA;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Photos.PhotoItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaNN on 21/10/2017.
 */

public class Venues {

    public String id;
    public String name;


    public float rating;
    public String url;

    public Contact contact = null;
    public LocationA locationA= null;
    public Price price = null;
    public PhotoItems bestphoto=null;


    public boolean hasBestohoto=false;
    public boolean hasPhotos=false;

    public boolean hasHours=false;
    public boolean haspopular=false;

    public ArrayList<PhotoItems> listphotos = new ArrayList<>();

    public Hours hours = null;
    public Hours popular = null;


    public Categories categories=null;
    public String referralId;

    public boolean verified = false;
    public boolean allowMenuURLEdit = true;
    public boolean hasPerk = false;
    public JSONObject jsonObject;
    public JSONArray ob;

    public ArrayList<LocationA> getListlocation() {
        return listlocation;
    }

    public void setListlocation(ArrayList<LocationA> listlocation) {
        this.listlocation = listlocation;
    }

    public ArrayList<LocationA> listlocation;

    public Venues(){
        this.locationA = null;

    }
    public Venues(String id, String name,Contact contact, ArrayList<LocationA> location){
        this.id =id;
        this.name=name;
        this.contact = contact;
        this.listlocation  = location;

    }

    public Venues(JSONObject object){

    }
    public Venues(String name, ArrayList<LocationA> location){
        this.name=name;
        this.listlocation  = location;

    }


    public void setContacts(JSONObject ob, String title){
        JSONArray con = null;
        try {
            con = ob.getJSONArray(title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i=0;i<con.length();i++){

        }


    }

    public boolean isHasHours() {
        return hasHours;
    }

    public void setHasHours(boolean hasHours) {
        this.hasHours = hasHours;
    }

    public boolean isHaspopular() {
        return haspopular;
    }

    public void setHaspopular(boolean haspopular) {
        this.haspopular = haspopular;
    }
    public boolean isHasBestohoto() {
        return hasBestohoto;
    }

    public void setHasBestohoto(boolean hasBestohoto) {
        this.hasBestohoto = hasBestohoto;
    }

    public boolean isHasPhotos() {
        return hasPhotos;
    }

    public void setHasPhotos(boolean hasPhotos) {
        this.hasPhotos = hasPhotos;
    }
    public ArrayList<PhotoItems> getPhotos() {
        return listphotos;
    }

    public void setPhotos(PhotoItems photos) {
        this.listphotos.add(photos);
    }

    public PhotoItems getBestphoto() {
        return bestphoto;
    }

    public void setBestphoto(PhotoItems bestphoto) {
        this.bestphoto = bestphoto;
    }

    public Hours getPopular() {
        return popular;
    }

    public void setPopular(Hours popular) {
        this.popular = popular;
    }

    public Hours getHours() {
        return hours;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public LocationA getLocationA() {
        return locationA;
    }

    public void setLocationA(LocationA locationA) {
        this.locationA = locationA;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isAllowMenuURLEdit() {
        return allowMenuURLEdit;
    }

    public void setAllowMenuURLEdit(boolean allowMenuURLEdit) {
        this.allowMenuURLEdit = allowMenuURLEdit;
    }

    public boolean isHasPerk() {
        return hasPerk;
    }

    public void setHasPerk(boolean hasPerk) {
        this.hasPerk = hasPerk;
    }

    public List<LocationA> getLocation() {
        return listlocation;
    }

    public void setLocation(ArrayList<LocationA> location) {
        this.listlocation = location;
    }


}
