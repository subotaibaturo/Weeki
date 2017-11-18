package com.example.alext.facebooklogintest.MenuItems;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alext.facebooklogintest.Login;
import com.example.alext.facebooklogintest.MenuItems.Map.Map_Activity;
import com.example.alext.facebooklogintest.R;
import com.example.alext.facebooklogintest.MenuItems.Search.Search_View;
import com.example.alext.facebooklogintest.MenuItems.fragments.Tab1_Main_Buscador;
import com.example.alext.facebooklogintest.MenuItems.fragments.Tab2_List;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by DaNN on 28/09/2017.
 */

public class Menu_activity extends AppCompatActivity{
    private static final String TAG ="MenuActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private TextView texto;

    private GoogleApiClient googleApiClient;
    private boolean Googlelogin,Facebooklogin;
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);





        Toolbar toolbar =(Toolbar) findViewById(R.id.main_toolbar2);
        toolbar.setTitle("Weeki");
        setSupportActionBar(toolbar);
        Googlelogin = getIntent().getExtras().getBoolean("flagG");
        Facebooklogin = getIntent().getExtras().getBoolean("flagF");
        //GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        //googleApiClient = new GoogleApiClient.Builder(Menu_activity.this).enableAutoManage(getActivity(),this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewpage_maincontent);
        setupViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.menutablayout);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorWhiteTextTitle),
                                   getResources().getColor(R.color.colorTabSelected));


    //        texto = (TextView) findViewById(R.id.textoo);

    }

    public void goSearchView(String query){
        Intent intent = new Intent(Menu_activity.this, Search_View.class);
        intent.putExtra("query",query);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m_settings,menu);
        MenuItem menuItem = menu.findItem(R.id.action_Settings);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_Settings){
            Intent intent = new Intent(this, Settings_View.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.toolbar_map){
            if(isServicesOk()){
                Intent intent = new Intent(Menu_activity.this, Map_Activity.class);
                startActivity(intent);
            }

        }
        if(item.getItemId() == R.id.action_logout){


            if(Facebooklogin==true && Googlelogin ==false){
                LoginManager.getInstance().logOut();
                goLoginScreen();
            }
            else if(Facebooklogin==false|| Googlelogin == false){
                goLoginScreen();
            }
            else{
                Toast.makeText(Menu_activity.this.getApplicationContext(),"FAILED LOGOUT",Toast.LENGTH_SHORT).show();
            }

        }


        return true;
    }

    private void goLoginScreen(){
        Intent intent = new Intent(Menu_activity.this , Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    public void revoke(View view){
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if(status.isSuccess()){
                    goLoginScreen();
                }
                else
                    Toast.makeText(Menu_activity.this.getApplicationContext(),"Error revoke access", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1_Main_Buscador(),"Buscador");
        adapter.addFragment(new Tab2_List(),"Mis listas");
        viewPager.setAdapter(adapter);
    }
    public boolean isServicesOk(){
        Log.d(TAG, "isServicesOk: checking google services version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Menu_activity.this);

        if(available == ConnectionResult.SUCCESS){                                              //Todo esta en orden y podemos hacer un request
            Log.d(TAG, "isServicesOk: Google Play Services is working");
            return true;
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){         //Ocurrio un error pero lo podemos resolver
            Log.d(TAG, "isServicesOk: an error ocurred but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Menu_activity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{                                                                                  //Ocurrio un error pero no hay nada que hacer
            Toast.makeText(this, "You can't make a request :(", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}

