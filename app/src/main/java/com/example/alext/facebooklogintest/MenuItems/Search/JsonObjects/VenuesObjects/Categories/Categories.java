package com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Categories;

/**
 * Created by DaNN on 21/10/2017.
 */

public class Categories {
    public String id;
    public String name;
    public String pluralName;
    public String shortName;



    public Categories(){}

    public Categories(String id, String name, String pluralName, String shortName){
        this.id = id;
        this.name = name;
        this.pluralName = pluralName;
        this.shortName = shortName;
    }

    public Categories(String name, String pluralName, String shortName){
        this.name = name;
        this.pluralName = pluralName;
        this.shortName = shortName;
    }

    public Categories(String name, String shortName){
        this.name = name;
        this.shortName = shortName;
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

    public String getPluralName() {
        return pluralName;
    }

    public void setPluralName(String pluralName) {
        this.pluralName = pluralName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
