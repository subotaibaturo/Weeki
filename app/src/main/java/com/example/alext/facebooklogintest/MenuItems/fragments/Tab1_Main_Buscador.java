package com.example.alext.facebooklogintest.MenuItems.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alext.facebooklogintest.Login;
import com.example.alext.facebooklogintest.MenuItems.Menu_activity;
import com.example.alext.facebooklogintest.MenuItems.Search.Search_View;
import com.example.alext.facebooklogintest.R;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by DaNN on 28/09/2017.
 */

public class Tab1_Main_Buscador extends Fragment implements GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG ="Buscador";
    private SignInButton googleSignin;
    private Button logoutbutton;
    private  Button revoke;
    private TextView token;

    private GoogleApiClient googleApiClient;
    private boolean Googlelogin,Facebooklogin;
    private static final int REQ_CODE = 9001;
    private String not_log_in ="Conecting Error ";

    private Bundle extra;
    private TextView texto;
    private Button query;

    private Button b1,b2,b3,b4,b5,b6;

    String name1,name2, name3,  name4, name5, name6;

    TextView tx;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view =inflater.inflate(R.layout.fragment_main,container,false);
        //revoke = (Button) view.findViewById(R.id.button_revoke);


        //Googlelogin = getActivity().getIntent().getExtras().getBoolean("flagG");
        //Facebooklogin = getActivity().getIntent().getExtras().getBoolean("flagF");
       // logoutbutton = (Button) view.findViewById(R.id.button_logout);

        //GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        //googleApiClient = new GoogleApiClient.Builder(getActivity()).enableAutoManage(getActivity(),this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

       /* query =(Button) view.findViewById(R.id.button_query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //json.setResult(texto,view,R.id.textoo);
            }
        });
*/
        /*
        revoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Googlelogin==true){
                    revoke(v);
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(),"FAILED REVOKING",Toast.LENGTH_SHORT).show();
                }
            }
        });
        */

        /*SearchView searchView = (SearchView) view.findViewById(R.id.searchview_menu);
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorSearchTEXT));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorAccent));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                goSearchView(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        */
        tx = (TextView) view.findViewById(R.id.search_TextView);
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSearchView("");
            }
        });

        /*
        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Googlelogin==true && Facebooklogin ==false){
                    revoke(v);
                    //goLoginScreen();
                }
                else if(Facebooklogin==true && Googlelogin ==false){
                    LoginManager.getInstance().logOut();
                    goLoginScreen();
                }
                else if(Facebooklogin==false|| Googlelogin == false){
                    goLoginScreen();
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(),"FAILED LOGOUT",Toast.LENGTH_SHORT).show();
                }
            }
        });
        */

        b1 = (Button) view.findViewById(R.id.Button1);
        b2 = (Button) view.findViewById(R.id.Button2);
        b3 = (Button) view.findViewById(R.id.Button3);
        b4 = (Button) view.findViewById(R.id.Button4);
        b5 = (Button) view.findViewById(R.id.Button5);
        b6 = (Button) view.findViewById(R.id.Button6);
        getinfo();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSearchView(name1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSearchView(name2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSearchView(name3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSearchView(name4);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSearchView(name5);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goSearchView(name6);
            }
        });

        return view;
    }

    private void goLoginScreen(){
        Intent intent = new Intent(getActivity() , Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goSearchView(String query){
        boolean isEmpty=true;
        Intent intent = new Intent(getActivity(), Search_View.class);
        if(query=="")
            isEmpty=true;
        else
            isEmpty=false;
        intent.putExtra("query",query);
        intent.putExtra("isempty",isEmpty);
        startActivity(intent);
    }

    public void onStart() {
        super.onStart();

        if(Googlelogin==true){
            Log.d("onSTart","Se accedio mediante login");
            OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
            if (opr.isDone()) {
                GoogleSignInResult result = opr.get();
                handlerSignInResult(result);
            } else {
                opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    @Override
                    public void onResult(@NonNull GoogleSignInResult result) {
                        handlerSignInResult(result);
                    }
                });
            }
        }
    }

    public void handlerSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            /*
            * Utilizar la información del usuario
            *
            * */
        }
        else{
            goLoginScreen();
        }

    }
    private void logout(){
        if(Googlelogin==true) {
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    if (status.isSuccess()) {
                        goLoginScreen();
                    } else
                        Toast.makeText(getActivity().getApplicationContext(), "Error Logout", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            goLoginScreen();
            //Intent intent = new Intent(this, Login.class);
            //start
        }
    }

    public void revoke(View view){
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if(status.isSuccess()){
                    goLoginScreen();
                }
                else
                    Toast.makeText(getActivity().getApplicationContext(),"Error revoke access", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getinfo(){

        SharedPreferences sharedPref =  this.getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
         name1 = sharedPref.getString("button1",""); //el segundo parametro es un default
         name2 = sharedPref.getString("button2","");
         name3 = sharedPref.getString("button3","");
         name4 = sharedPref.getString("button4","");
         name5 = sharedPref.getString("button5","");
         name6 = sharedPref.getString("button6","");

        Log.d("TEST","valor de : " + name1);

        b1.setText(name1);
        b2.setText(name2);
        b3.setText(name3);
        b4.setText(name4);
        b5.setText(name5);
        b6.setText(name6);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onResume() {

        getinfo();
        super.onResume();
    }
}
