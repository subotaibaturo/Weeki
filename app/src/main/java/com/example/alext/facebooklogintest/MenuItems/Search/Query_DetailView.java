package com.example.alext.facebooklogintest.MenuItems.Search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.ResponseApi;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Venues;
import com.example.alext.facebooklogintest.R;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by DaNN on 25/10/2017.
 */

public class Query_DetailView extends AppCompatActivity{

    String baseurl= "https://api.foursquare.com/v2/venues/";
    String placeId="";
    String query0 = "/";
    String query2="?&v=20131016";
    String client ="&client_id=JLI2NNF4VHEX51OELCKCGIJFAIIVTUC4VMVFEZTQOFDRKGXL";
    String secret_client = "&client_secret=F2VNR4NZ5LNWTAQG4Y1QDTEL0CNRGWRYX1HSKTLUTP3DQBDS";
    String finalJson="";

    ArrayList<Venues> listVenues = new ArrayList<Venues>();
    ArrayList<Venues> listhour = new ArrayList<Venues>();

    Venues venueDetail;

    Bundle bundle;

    public ResponseApi responseApi;

    private RequestQueue mrRequestQueue;
    private StringRequest stringRequest;
    JsonRequest jsonRequest;

    private TextView textView_Address, textView_Country,textView_Category, textView_Rating,textView_hour, textView_IsOpen;

    ImageView im;
    Context context;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_detailview);

        toolbar =  (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        venueDetail = new Venues();
        responseApi = new ResponseApi();
        context =this;
        String id = getIntent().getExtras().getString("iditem");
        Log.d("id Value","The id value of the item is: "+ id);
        textView_Address = (TextView) findViewById(R.id.textview_detail_address);
        textView_Country = (TextView) findViewById(R.id.textview_detail_Country);
        textView_Category = (TextView) findViewById(R.id.textview_detail_Category);
        textView_IsOpen = (TextView) findViewById(R.id.textview_detail_isOpen);
        textView_Rating = (TextView) findViewById(R.id.textview_detail_rating);
        textView_hour = (TextView) findViewById(R.id.textview_detail_times);
        im = (ImageView) findViewById(R.id.image_detail);
        //textView.setText(id);
        sendRequest(id);


    }

    public void setItems(){
        textView_Address.setText(venueDetail.getLocationA().getAddress());
        textView_Country.setText(venueDetail.getLocationA().getCity()+" , "+venueDetail.getLocationA().getCountry());
        textView_Category.setText(venueDetail.getCategories().getName());
        textView_Rating.setText(String.valueOf(venueDetail.getRating()));
        try {
            String q = venueDetail.getHours().getStatus();
            String h = venueDetail.getHours().getTimeframes().get(0).getListrenderedtime().get(0).getRenderedtime();
            Log.d("RednderedTIME", "RENDERED TIME" + h);
            textView_hour.setText(venueDetail.getHours().getTimeframes().get(0).getDays());
            textView_IsOpen.setText(venueDetail.getHours().getStatus());

        }catch (Exception e){ e.printStackTrace();}
        //textView_hour.setText(venueDetail.getHours().getStatus());
        Glide.with(context).load(venueDetail.getPhotos().get(0).getIMG("500","500")).into(im);
        //Glide.with(context).load(venueDetail.getBestphoto().getIMG("500","500"));
        toolbar.setTitle(venueDetail.getName());


    }

    private void sendRequest(String query){
        mrRequestQueue = Volley.newRequestQueue(this);

        finalJson = FinalUrl(query);
        jsonRequest = new JsonObjectRequest(Request.Method.GET,finalJson,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                venueDetail = responseApi.getVenueByJson(response);
                setItems();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
    });

        mrRequestQueue.add(jsonRequest);

    }

    public String FinalUrl(String query1){
        return baseurl+placeId+query0+query1+ query2+client+secret_client;
    }






}
