package com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects;

import android.util.Log;

import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Categories.Categories;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Contact;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Hour.Hours;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Location.LocationA;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Photos.PhotoItems;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Price;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Venues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DaNN on 23/10/2017.
 */

public class ResponseApi {


    public ArrayList<Venues> listvenues;


    public String getJson() {
        return json;
    }

    public String json;
    public Venues venues;

    public ResponseApi(){}

    public ResponseApi(String finaljson){
        setResponse(finaljson);
    }
    public ResponseApi(JSONObject ob){
        listvenues = new ArrayList<>();
        try {
            JSONArray finalarray = ob.getJSONArray("venues");
            for(int i=0;i<finalarray.length();i++){
                JSONObject object = finalarray.getJSONObject(i);
                venues = new Venues();
                venues.setId(object.getString("id"));
                venues.setName(object.getString("name"));

                listvenues.add(venues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setResponse(String finalJson) {
        listvenues = new ArrayList<Venues>();
        JSONObject finalObject;
        JSONObject JsonResponse;
        try {
            finalObject = new JSONObject(finalJson);
            JsonResponse = finalObject.getJSONObject("response");
            JSONArray arrayVenues = JsonResponse.getJSONArray("venues");
            if(JsonResponse.has("venues")){

                for(int i=0;i<arrayVenues.length();i++) {
                    JSONObject object = arrayVenues.getJSONObject(i);
                    venues = new Venues();
                    if(object.has("id"))
                        venues.setId(object.getString("id"));
                    else
                        venues.setId("");


                    if(object.has("name"))
                        venues.setName(object.getString("name"));
                    else
                        venues.setName("");
                    Categories categories = new Categories();
                    LocationA locationA = new LocationA();

                    //JSONObject objectContact = object.getJSONObject("contact");
                    JSONObject objectLocation = object.getJSONObject("location");

                    if(object.has("location")) {

                        if ((objectLocation.has("address"))) {
                            locationA.setAddress(objectLocation.getString("address"));
                        } else {
                            Log.d("EMPTY JSON", "HUBO UN ERROR EN EL ADDRESS");
                            locationA.setAddress("");
                        }

                        if (objectLocation.has("city")) {
                            locationA.setCity(objectLocation.getString("city"));
                        }
                        else {
                            Log.d("EMPTY JSON", "HUBO UN ERROR CITY");
                            locationA.setCity("");
                        }

                        if(objectLocation.has("distance"))
                            locationA.setDistance(Long.parseLong(objectLocation.getString("distance")));
                        else
                            locationA.setDistance(0);

                        if(objectLocation.has("lat"))
                            locationA.setLat(Double.parseDouble(objectLocation.getString("lat")));
                        else
                            locationA.setLat(0.0);

                        if(objectLocation.has("lng"))
                            locationA.setLng(Double.parseDouble(objectLocation.getString("lng")));
                        else
                            locationA.setLat(0.0);
                    }

                    JSONArray arrayCategories = object.getJSONArray("categories");
                    if(object.has("categories")) {
                        for (int j = 0; j < arrayCategories.length(); j++) {
                            JSONObject objectCategories = arrayCategories.getJSONObject(j);
                            if(objectCategories.has("name")){
                                categories.setName(objectCategories.getString("name"));
                            }
                            else
                                categories.setName("");
                        }
                    }

                    venues.setCategories(categories);
                    venues.setLocationA(locationA);
                    listvenues.add(venues);
                }

            }
            else{
                Log.d("EMPTY JSON", "HUBO UN ERROR NO SE ENCONTRÓ EL LUGAR");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Venues getDetailInfo(String json){
        Venues venue = new Venues();
        JSONObject objectFinalJson;
        JSONObject objectResponse;

        try {
            objectFinalJson = new JSONObject(json);
            objectResponse = objectFinalJson.getJSONObject("response");
            JSONObject objectVenue = objectResponse.getJSONObject("venue");

            venue.setName(objectVenue.getString("name"));

            Contact contact = null;
            JSONObject objectContact = objectVenue.getJSONObject("contact");
            /*
            if( (objectContact.length())== 0)
                Log.d("CONTACT","IS NULL");
            else{
                contact.setPhone(objectContact.getString("phone"));
                venue.setContact(contact);
            }
            */
            LocationA locationA = new LocationA();

            JSONObject objectLocation = objectVenue.getJSONObject("location");

            locationA.setAddress(objectLocation.getString("address"));
            locationA.setLat(Double.parseDouble(objectLocation.getString("lat")));
            locationA.setLng(Double.parseDouble(objectLocation.getString("lng")));
            //locationA.setPostaCode(Long.parseLong(objectLocation.getString("postalCode")));
            locationA.setCc(objectLocation.getString("cc"));
            locationA.setCity(objectLocation.getString("city"));
            locationA.setState(objectLocation.getString("state"));
            locationA.setCountry(objectLocation.getString("country"));
            venue.setLocationA(locationA);

            JSONArray arrayCategories = objectVenue.getJSONArray("categories");
            Categories categories = new Categories();

            for(int j = 0;j<arrayCategories.length();j++){
                JSONObject objectCategories = arrayCategories.getJSONObject(j);
                categories.setName(objectCategories.getString("name"));
            }
            venue.setCategories(categories);

            /*
            try{
                if(objectVenue.getString("url")==null||objectVenue.getString("url").length()==0){
                    Log.d("CONTACT","IS NULL");
                }
                else{
                    venue.setUrl(objectVenue.getString("url"));
                }
            }catch (Exception e){e.printStackTrace();}
            */
            JSONObject objectPrice = objectVenue.getJSONObject("price");
            Price price = new Price();

            price.setTier(Integer.parseInt(objectPrice.getString("tier")));
            price.setMessage(objectPrice.getString("message"));
            price.setCurrency(objectPrice.getString("currency"));

            venue.setPrice(price);

            venue.setRating(Float.parseFloat(objectVenue.getString("rating")));


            JSONObject objectphotos = objectVenue.getJSONObject("photos");
            JSONArray arraygrouphotos = objectphotos.getJSONArray("groups");
            for(int i=0;i<arraygrouphotos.length();i++) {
               JSONObject o = arraygrouphotos.getJSONObject(i);
                JSONArray arrayitemsphotos = o.getJSONArray("items");

                for(int j = 0;j<arrayitemsphotos.length();j++){
                    JSONObject objectitemphotos = arrayitemsphotos.getJSONObject(j);
                    PhotoItems pi= new PhotoItems();

                    pi.setPrefix(objectitemphotos.getString("prefix"));
                    pi.setSuffix(objectitemphotos.getString("suffix"));
                    pi.setWITDTH(objectitemphotos.getString("width"));
                    pi.setHEIGHT(objectitemphotos.getString("height"));
                    venue.setPhotos(pi);
                }

            }

            JSONObject objectHour = objectVenue.getJSONObject("hours");
            Hours hr = new Hours();
            hr.setStatus(objectHour.getString("status"));
            hr.setOpen(Boolean.parseBoolean(objectHour.getString("isOpen")));
            hr.setLocalHoliday(Boolean.parseBoolean(objectHour.getString("isLocalHoliday")));

            JSONArray arrayTimeFrame =objectHour.getJSONArray("timeframes");
            for(int i=0;i<arrayTimeFrame.length();i++){
                JSONObject ta = arrayTimeFrame.getJSONObject(i);
                Hours.Timeframes tf = new Hours.Timeframes();
                tf.setDays(ta.getString("days"));
                JSONArray arrayRenderedTime = ta.getJSONArray("open");
                for(int j=0;j<arrayRenderedTime.length();j++){
                    JSONObject objectRen = arrayRenderedTime.getJSONObject(j);
                    Hours.Timeframes.OpenTime op = new Hours.Timeframes.OpenTime();
                    op.setRenderedtime(objectRen.getString("renderedTime"));
                    tf.adOP(op);
                }
                hr.setTime(tf);
            }

            venue.setHours(hr);


            JSONObject objectPopular = objectVenue.getJSONObject("popular");
            Hours pop = new Hours();
            hr.setStatus(objectHour.getString("status"));
            hr.setOpen(Boolean.parseBoolean(objectHour.getString("isOpen")));
            hr.setLocalHoliday(Boolean.parseBoolean(objectHour.getString("isLocalHoliday")));
            JSONArray arrayTimeFrame2 =objectHour.getJSONArray("timeframes");
            for(int i=0;i<arrayTimeFrame2.length();i++){
                JSONObject ta = arrayTimeFrame2.getJSONObject(i);
                Hours.Timeframes tf = new Hours.Timeframes();
                tf.setDays(ta.getString("days"));
                JSONArray arrayRenderedTime = ta.getJSONArray("open");
                for(int j=0;j<arrayRenderedTime.length();j++){
                    JSONObject objectRen = arrayRenderedTime.getJSONObject(j);
                    Hours.Timeframes.OpenTime op = new Hours.Timeframes.OpenTime();
                    op.setRenderedtime(objectRen.getString("renderedTime"));
                    tf.adOP(op);
                }
                hr.setTime(tf);
            }
            venue.setPopular(pop);

            JSONObject objectBestPhoto = objectVenue.getJSONObject("bestPhoto");
            PhotoItems bphoto = new PhotoItems();
            bphoto.setPrefix(objectBestPhoto.getString("prefix"));
            bphoto.setSuffix(objectBestPhoto.getString("suffix"));
            bphoto.setWITDTH(objectBestPhoto.getString("width"));
            bphoto.setHEIGHT(objectBestPhoto.getString("height"));
            venue.setBestphoto(bphoto);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return venue;
    }


    public Venues getVenueByJson(JSONObject objectR){
        Venues venue = new Venues();
        //JSONObject objectFinalJson;
        JSONObject objectResponse;

        try {
            //objectFinalJson = new JSONObject(json);
            objectResponse = objectR.getJSONObject("response");
            JSONObject objectVenue = objectResponse.getJSONObject("venue");

            venue.setName(objectVenue.getString("name"));

            Contact contact = new Contact();
            JSONObject objectContact = objectVenue.getJSONObject("contact");

            if( objectContact.has("contact")){
                contact.setPhone(objectContact.getString("phone"));
            }
            venue.setContact(contact);

            LocationA locationA = new LocationA();

            JSONObject objectLocation = objectVenue.getJSONObject("location");

            if(objectVenue.has("location")) {

                if(objectLocation.has("address"))
                    locationA.setAddress(objectLocation.getString("address"));
                else
                    locationA.setAddress("");

                if(objectLocation.has("lat"))
                    locationA.setLat(Double.parseDouble(objectLocation.getString("lat")));
                else
                    locationA.setLat(0.0);

                if(objectLocation.has("lng"))
                    locationA.setLat(Double.parseDouble(objectLocation.getString("lng")));
                else
                    locationA.setLng(0.0);

                if(objectLocation.has("postalCode"))
                    locationA.setPostaCode(Long.parseLong(objectLocation.getString("postalCode")));
                else
                    locationA.setPostaCode(0);

                if(objectLocation.has("cc"))
                    locationA.setCc(objectLocation.getString("cc"));
                else
                    locationA.setCc("");

                if(objectLocation.has("city"))
                    locationA.setCity(objectLocation.getString("city"));
                else
                    locationA.setCity("");

                if(objectLocation.has("state"))
                    locationA.setState(objectLocation.getString("state"));
                else
                    locationA.setState("");

                if(objectLocation.has("country"))
                    locationA.setCountry(objectLocation.getString("country"));
                else
                    locationA.setCountry("");
            }
            venue.setLocationA(locationA);

            JSONArray arrayCategories = objectVenue.getJSONArray("categories");
            Categories categories = new Categories();

            if(objectVenue.has("categories")) {

                for (int j = 0; j < arrayCategories.length(); j++) {
                    JSONObject objectCategories = arrayCategories.getJSONObject(j);
                    if(objectCategories.has("name"))
                        categories.setName(objectCategories.getString("name"));
                    else
                        categories.setName("");
                }
            }
            venue.setCategories(categories);

            if(objectVenue.has("url"))
                venue.setUrl(objectVenue.getString("url"));
            else
                venue.setUrl("");

            JSONObject objectPrice = objectVenue.getJSONObject("price");
            Price price = new Price();

            if(objectVenue.has("price")) {
                if(objectPrice.has("tier"))
                    price.setTier(Integer.parseInt(objectPrice.getString("tier")));
                else
                    price.setTier(0);

                if(objectPrice.has("message"))
                    price.setMessage(objectPrice.getString("message"));
                else
                    price.setMessage("");
                if(objectPrice.has("currency"))
                    price.setCurrency(objectPrice.getString("currency"));
                else
                    price.setCurrency("");
            }
            venue.setPrice(price);

            if(objectVenue.has("rating"))
                venue.setRating(Float.parseFloat(objectVenue.getString("rating")));
            else
                venue.setRating(0);

            JSONObject objectphotos = objectVenue.getJSONObject("photos");
            if(objectVenue.has("photos")) {
                JSONArray arraygrouphotos = objectphotos.getJSONArray("groups");
                venue.setHasPhotos(true);

                if(objectphotos.has("groups")) {
                    for (int i = 0; i < arraygrouphotos.length(); i++) {
                        JSONObject o = arraygrouphotos.getJSONObject(i);
                        JSONArray arrayitemsphotos = o.getJSONArray("items");
                        if(o.has("items")) {
                            for (int j = 0; j < arrayitemsphotos.length(); j++) {
                                JSONObject objectitemphotos = arrayitemsphotos.getJSONObject(j);
                                PhotoItems pi = new PhotoItems();

                                if(objectitemphotos.has("prefix"))
                                    pi.setPrefix(objectitemphotos.getString("prefix"));
                                if(objectitemphotos.has("suffix"))
                                    pi.setSuffix(objectitemphotos.getString("suffix"));
                                if(objectitemphotos.has("width"));
                                    pi.setWITDTH(objectitemphotos.getString("width"));
                                if(objectitemphotos.has("height"))
                                    pi.setHEIGHT(objectitemphotos.getString("height"));

                                venue.setPhotos(pi);
                            }
                        }
                    }
                }
            }

            JSONObject objectHour = objectVenue.getJSONObject("hours");
            Hours hr = new Hours();

            if(objectVenue.has("hours")) {
                venue.setHasHours(true);
                if(objectHour.has("status"))
                    hr.setStatus(objectHour.getString("status"));
                else
                    hr.setStatus("");
                if(objectHour.has("isOpen"))
                    hr.setOpen(Boolean.parseBoolean(objectHour.getString("isOpen")));
                else
                    hr.setOpen(false);
                if(objectHour.has("isLocalHoliday"))
                    hr.setLocalHoliday(Boolean.parseBoolean(objectHour.getString("isLocalHoliday")));
                else
                    hr.setLocalHoliday(false);

                JSONArray arrayTimeFrame = objectHour.getJSONArray("timeframes");

                if(objectHour.has("timeframes")) {
                    for (int i = 0; i < arrayTimeFrame.length(); i++) {
                        JSONObject ta = arrayTimeFrame.getJSONObject(i);
                        Hours.Timeframes tf = new Hours.Timeframes();
                       if(ta.has("days")) {
                           tf.setDays(ta.getString("days"));
                           JSONArray arrayRenderedTime = ta.getJSONArray("open");
                           if(ta.has("open")) {
                               for (int j = 0; j < arrayRenderedTime.length(); j++) {
                                   JSONObject objectRen = arrayRenderedTime.getJSONObject(j);
                                   Hours.Timeframes.OpenTime op = new Hours.Timeframes.OpenTime();
                                   if(objectRen.has("renderedTime")) {
                                       op.setRenderedtime(objectRen.getString("renderedTime"));
                                       tf.adOP(op);
                                   }
                               }
                           }

                       }
                        hr.setTime(tf);
                    }
                }
            }
            venue.setHours(hr);


            JSONObject objectPopular = objectVenue.getJSONObject("popular");
            if(objectVenue.has("popular")) {
                Hours pop = new Hours();
                if(objectHour.has("status"))
                    hr.setStatus(objectHour.getString("status"));
                else
                    hr.setStatus("");
                if(objectHour.has("isOpen"))
                    hr.setOpen(Boolean.parseBoolean(objectHour.getString("isOpen")));
                else
                    hr.setOpen(false);
                if(objectHour.has("isLocalHoliday"))
                    hr.setLocalHoliday(Boolean.parseBoolean(objectHour.getString("isLocalHoliday")));
                else
                    hr.setLocalHoliday(false);

                JSONArray arrayTimeFrame = objectHour.getJSONArray("timeframes");

                if(objectHour.has("timeframes")) {
                    for (int i = 0; i < arrayTimeFrame.length(); i++) {
                        JSONObject ta = arrayTimeFrame.getJSONObject(i);
                        Hours.Timeframes tf = new Hours.Timeframes();
                        if(ta.has("days")) {
                            tf.setDays(ta.getString("days"));
                            JSONArray arrayRenderedTime = ta.getJSONArray("open");
                            if(ta.has("open")) {
                                for (int j = 0; j < arrayRenderedTime.length(); j++) {
                                    JSONObject objectRen = arrayRenderedTime.getJSONObject(j);
                                    Hours.Timeframes.OpenTime op = new Hours.Timeframes.OpenTime();
                                    if(objectRen.has("renderedTime")) {
                                        op.setRenderedtime(objectRen.getString("renderedTime"));
                                        tf.adOP(op);
                                    }
                                }
                            }
                            hr.setTime(tf);
                        }
                    }
                }
                venue.setPopular(pop);
            }

            JSONObject objectBestPhoto = objectVenue.getJSONObject("bestPhoto");
            if(objectVenue.has("bestPhoto")) {
                venue.setHasBestohoto(true);
                PhotoItems bphoto = new PhotoItems();
                bphoto.setPrefix(objectBestPhoto.getString("prefix"));
                bphoto.setSuffix(objectBestPhoto.getString("suffix"));
                bphoto.setWITDTH(objectBestPhoto.getString("width"));
                bphoto.setHEIGHT(objectBestPhoto.getString("height"));
                venue.setBestphoto(bphoto);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



        return venue;
    }

    public String getIdVenue(){
        return null;
    }
    public ArrayList<Venues> getListvenues() {
        return listvenues;
    }


    public void setListvenues(ArrayList<Venues> listvenues) {
        this.listvenues = listvenues;
    }

    public Venues getVenues() {
        return venues;
    }

    public void setVenues(Venues venues) {
        this.venues = venues;
    }

}
