package com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Photos;

import java.util.ArrayList;

/**
 * Created by DaNN on 28/10/2017.
 */

public class Photos {

    public ArrayList<PhotoItems> photoitems =  new ArrayList<PhotoItems>();

    public Photos(){
        this.photoitems = null;
    }


    public ArrayList<PhotoItems> getPhotoitems() {
        return photoitems;
    }

    public void setPhoto(PhotoItems photo){
        photoitems.add(photo);
    }
    public void setPhotoitems(ArrayList<PhotoItems> photoitems) {
        this.photoitems = photoitems;
    }

}
