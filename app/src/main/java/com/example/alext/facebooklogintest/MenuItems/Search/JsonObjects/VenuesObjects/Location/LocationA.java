package com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Location;


import java.util.ArrayList;

/**
 * Created by DaNN on 21/10/2017.
 */

public class LocationA {
    public String address;
    public String crossStreet;
    public String cc;

    public String city;
    public String state;
    public String country;

    public double lat;
    public double lng;

    public long distance;
    public long postaCode;

    public ArrayList<FormattedAddresss> formattedAddressses;

    public LocationA(){
        this.address="";
        this.crossStreet = "";
        this.cc="";

        this.city="";
        this.country="";

        this.lat=0;
        this.lng=0;

        long distance=0;
        long postaCode=0;
        this.formattedAddressses =null;
    }

    public LocationA(String address){
        this.address=address;
    }
    public LocationA(String address, String crossStreet){
        this.address = address;
        this.crossStreet = crossStreet;
    }

    public LocationA(String address, String crossStreet, String cc){
        this.address = address;
        this.crossStreet = crossStreet;
        this.cc = cc;
    }

    public LocationA(String address, String crossStreet, String cc, String city){
        this.address = address;
        this.crossStreet = crossStreet;
        this.cc = cc;
        this.city = city;
    }

    public LocationA(String address, String crossStreet, String cc, String city, String country){
        this.address = address;
        this.crossStreet = crossStreet;
        this.cc = cc;
        this.city = city;
        this.country = country;
    }

    public LocationA(String address, String crossStreet, String cc, String city, String country, double lat, double lng){
        this.address = address;
        this.crossStreet = crossStreet;
        this.cc = cc;
        this.city = city;
        this.country = country;
        this.lat = lat;
        this.lng = lng;
    }
    public LocationA(String address, String crossStreet, String city, String country, double lat, double lng){
        this.address = address;
        this.crossStreet = crossStreet;
        this.city = city;
        this.country = country;
        this.lat = lat;
        this.lng = lng;
    }

    public LocationA(String address, String city, String country, double lat, double lng){
        this.address = address;
        this.city = city;
        this.country = country;
        this.lat = lat;
        this.lng = lng;
    }

    public LocationA(String address, String city, double lat, double lng){
        this.address = address;
        this.city = city;
        this.lat = lat;
        this.lng = lng;
    }

    public LocationA(String address,double lat, double lng){
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getPostaCode() {
        return postaCode;
    }

    public void setPostaCode(long postaCode) {
        this.postaCode = postaCode;
    }




}
