package com.example.alext.facebooklogintest.MenuItems.Search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
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
import com.example.alext.facebooklogintest.MenuItems.Search.Adapters.HourRecyclerAdapter;
import com.example.alext.facebooklogintest.MenuItems.Search.Adapters.HoursAdapter;
import com.example.alext.facebooklogintest.MenuItems.Search.Adapters.ImageRecyclerAdapter;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.ResponseApi;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Photos.PhotoItems;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Photos.Photos;
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

    RecyclerView recyclerView;
    ImageRecyclerAdapter imageRecyclerAdapter;

    HourRecyclerAdapter hourRecyclerAdapter;
    RecyclerView.LayoutManager layoutManager;

    CardView c1,c2,c3,c4;
    Venues venueDetail;
    RatingBar ratingBar;
    ListView listhr;

    Bundle bundle;


    public HoursAdapter hoursAdapter;

    public ResponseApi responseApi;

    private RequestQueue mrRequestQueue;
    private StringRequest stringRequest;
    JsonRequest jsonRequest;

    private TextView textView_Address,
                     textView_Country,
                     textView_Category,
                     textView_Rating,
                     textView_hour,
                     textView_IsOpen,
                             textView_openUntil;

    ImageView im;
    ImageButton im1,im2,im3,im4,im5,im6;
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
        textView_openUntil =(TextView)findViewById(R.id.detail_openUntil);
        textView_Rating = (TextView) findViewById(R.id.textview_detail_rating);
        //textView_hour = (TextView) findViewById(R.id.textview_detail_times);
        im = (ImageView) findViewById(R.id.image_detail);
        ratingBar = (RatingBar) findViewById(R.id.detail_ratingbar);
        listhr = (ListView) findViewById(R.id.detail_listview);

        c1 = (CardView) findViewById(R.id.detail_cardview1);

        im1 = (ImageButton) findViewById(R.id.detail_image1);
        im2 = (ImageButton) findViewById(R.id.detail_image2);
        im3 = (ImageButton) findViewById(R.id.detail_image3);
        im4 = (ImageButton) findViewById(R.id.detail_image4);
        im5 = (ImageButton) findViewById(R.id.detail_image5);
        im6 = (ImageButton) findViewById(R.id.detail_image6);
        //textView.setText(id);
        sendRequest(id);

        /*
        recyclerView = (RecyclerView) findViewById(R.id.detail_hourRecycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,llm.getOrientation());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(itemDecoration);
        */

       // layoutManager = new GridLayoutManager(this,2);
       // recyclerView.setLayoutManager(layoutManager);


        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFullHD(0);
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFullHD(1);
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFullHD(2);
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFullHD(3);
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFullHD(4);
            }
        });
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goFullHD(5);
            }
        });
    }
public void goFullHD(int p){
    String h = venueDetail.getPhotos().get(p).getHEIGHT();
    String w = venueDetail.getPhotos().get(p).getHEIGHT();
    String u = venueDetail.getPhotos().get(p).getIMG(w,h);
    Log.d("IMAGE O","Image url:" + u);

    Intent intent = new Intent(Query_DetailView.this,ImageFull.class);
    intent.putExtra("full",u);
    startActivity(intent);
}

public void setItems(){
        textView_Address.setText(venueDetail.getLocationA().getAddress());
        Log.d("TEST","El address es: " + venueDetail.getLocationA().getAddress());
        textView_Country.setText(venueDetail.getLocationA().getCity()+" , "+venueDetail.getLocationA().getCountry());
        textView_Category.setText(venueDetail.getCategories().getName());
        textView_Rating.setText(String.valueOf(venueDetail.getRating()));
        try {
            String q = venueDetail.getHours().getStatus();
            //String h = venueDetail.getHours().getTimeframes().get(0).getListrenderedtime().get(0).getRenderedtime();
            //Log.d("RednderedTIME", "RENDERED TIME" + h);
            textView_openUntil.setText(venueDetail.getHours().getStatus());
            if(venueDetail.getHours().isOpen()){
                textView_IsOpen.setText("Abierto");
                textView_IsOpen.setTextColor(getResources().getColor(R.color.colorGreenStatus));

              /*
                if(venueDetail.getHours().getTimeframes().get(0).getDays()!=null||venueDetail.getHours().getTimeframes().get(0).getDays()!="")
                    textView_hour.setText(venueDetail.getHours().getTimeframes().get(0).getDays());
                else
                    textView_hour.setText("");
                    */
            }

            else{
                textView_IsOpen.setText("Cerrado");
                textView_IsOpen.setTextColor(getResources().getColor(R.color.colorRedStatus));
            }
            Log.d("Hours SIZE","el tamaño es: "+venueDetail.getHours().getTimeframes().size());
            for(int i=0;i<(int)(venueDetail.getHours().listtimeframes.size())/2;i++){
                String d = venueDetail.getHours().getTimeframes().get(i).getDays();

                String r = venueDetail.getHours().getTimeframes().get(i).getListrenderedtime().get(0).getRenderedtime();
                Log.d("Hours","Res :"+d+"  "+ r);

            }

            hoursAdapter = new HoursAdapter(context,R.layout.hour_item,venueDetail.getHours().getTimeframes());
            listhr.setAdapter(hoursAdapter);

            //hourRecyclerAdapter = new HourRecyclerAdapter(venueDetail.getHours().getTimeframes(),context);
            //recyclerView.setAdapter(hourRecyclerAdapter);


        }catch (Exception e){ e.printStackTrace();}
        //textView_hour.setText(venueDetail.getHours().getStatus());

        if(venueDetail.isHasBestohoto()){
            Glide.with(context).load(venueDetail.getBestphoto().getIMG("500","500")).into(im);
            Log.d("BestPhoto","has best photo");
        }
        else if(venueDetail.isHasPhotos())
            Glide.with(context).load(venueDetail.getPhotos().get(0).getIMG("500","500")).into(im);
            //Glide.with(context).load(venueDetail.getBestphoto().getIMG("500","500"));
        toolbar.setTitle(venueDetail.getName());

        ArrayList<PhotoItems> photos = venueDetail.getPhotos();
        Log.d("NUMBER OF PHOTOS", "The number of photos is: "+ photos.size());

        if(venueDetail.isHasPhotos()) {
            String imageUrlPath1 = photos.get(0).getIMG("200", "200");
            String imageUrlPath2 = photos.get(1).getIMG("200", "200");
            String imageUrlPath3 = photos.get(2).getIMG("200", "200");
            String imageUrlPath4 = photos.get(3).getIMG("200", "200");
            String imageUrlPath5 = photos.get(4).getIMG("200", "200");
            String imageUrlPath6 = photos.get(5).getIMG("200", "200");

            Glide.with(context).load(imageUrlPath1).into(im1);
            Glide.with(context).load(imageUrlPath2).into(im2);
            Glide.with(context).load(imageUrlPath3).into(im3);
            Glide.with(context).load(imageUrlPath4).into(im4);
            Glide.with(context).load(imageUrlPath5).into(im5);
            Glide.with(context).load(imageUrlPath6).into(im6);

        }

        ratingBar.setRating((venueDetail.getRating())/2);
        //imageRecyclerAdapter = new ImageRecyclerAdapter(photos,Query_DetailView.this);
        //recyclerView.setAdapter(imageRecyclerAdapter);




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
