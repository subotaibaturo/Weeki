package com.example.alext.facebooklogintest.MenuItems.Search;

import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.alext.facebooklogintest.MenuItems.Menu_activity;
import com.example.alext.facebooklogintest.MenuItems.Search.Adapters.RecyclerAdapter;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.ResponseApi;
import com.example.alext.facebooklogintest.R;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Venues;

import java.util.ArrayList;


public class Search_View extends AppCompatActivity {

    private Button button;
    private TextView result;
    private EditText text;
    //private Retrofit retrofit;
    private String res;
    private Toolbar toolbar;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;


    private RequestQueue mrRequestQueue;
    private StringRequest stringRequest;
    private ArrayList<Venues> venues = new ArrayList<>();

    private String mquery="";

    private String url="https://api.foursquare.com/v2/venues/search?v=20131016&ll=20.6596988%20%2C%20-103.34960920000003&query=";
    private String query1="como si fuera domingo";
    private String url2="&intent=checkin&client_id=JLI2NNF4VHEX51OELCKCGIJFAIIVTUC4VMVFEZTQOFDRKGXL&client_secret=F2VNR4NZ5LNWTAQG4Y1QDTEL0CNRGWRYX1HSKTLUTP3DQBDS";
    private String urlcom= url+query1+url2;


    private ResponseApi responseApi;
    Context context;
    boolean isEmpty;
    Bundle extra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchview);

        result = (TextView) findViewById(R.id.json);
        toolbar = (Toolbar) findViewById(R.id.new_toolbar);
        toolbar.setTitle("Weeki");
        setSupportActionBar(toolbar);
        isEmpty=true;



        context =this;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,llm.getOrientation());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(itemDecoration);



    }
    public ArrayList<Venues> fetchJSON(String finaljson){

            ArrayList<Venues> listVenues = new ArrayList<Venues>();

            ResponseApi responseApi = new ResponseApi(finaljson);
            listVenues = responseApi.getListvenues();

    return listVenues;
    }

    public void setRecyclerAdapter(){
        recyclerAdapter = new RecyclerAdapter(venues,context);
        recyclerView.setAdapter(recyclerAdapter);

    }

    public ArrayList<Venues> getfinalVenues(String idVenue){

        return null;
    }

    private void sendRequest(String query){
       mrRequestQueue = Volley.newRequestQueue(this);
        String temp = query.replaceAll(" ","%20");
        stringRequest = new StringRequest(Request.Method.GET,temp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //result.setText(response.toString());
                res = response.toString();
                Log.d("QUERY", response.toString());
                venues = fetchJSON(response.toString());

                if(venues.size()==0){
                    Toast.makeText(Search_View.this.getApplicationContext(),"Information NOT FOUND",Toast.LENGTH_SHORT).show();
                }
                setRecyclerAdapter();


                /*String phonen="";
                phonen=venues.get(0).getContact().getPhone();

                result.setText(venues.get(0).getName()+"\n"+venues.get(0).getId()+"\nPhone: "+phonen);
                */
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Search_View.this.getApplicationContext(),"Information NOT FOUND",Toast.LENGTH_SHORT).show();
            }
        });
        mrRequestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m_search_view,menu);

        mquery = getIntent().getExtras().getString("query");
        isEmpty=getIntent().getExtras().getBoolean("isempty");
        final MenuItem menuitem = menu.findItem(R.id.search_viewer);
        Log.d("QUERY","El query es: " + mquery);
        //if(mquery=="-")


        if(isEmpty==false){
            sendRequest(url+mquery+url2);
        }
        else
            menuitem.expandActionView();

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuitem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //new JSONtask().execute(query);
                Log.d("Prueba1",query);

                String url1= url+query+url2;
                //String url3="http://www.mocky.io/v2/5808a03a10000048004c6269";

                sendRequest(url1);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;


            }
        });
        return true;
    }


    }
