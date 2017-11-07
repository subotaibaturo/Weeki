package com.example.alext.facebooklogintest.MenuItems.fragments;

import android.content.Intent;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

        SearchView searchView = (SearchView) view.findViewById(R.id.searchview_menu);
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

        return view;
    }

    private void goLoginScreen(){
        Intent intent = new Intent(getActivity() , Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void goSearchView(String query){
        Intent intent = new Intent(getActivity(), Search_View.class);
        intent.putExtra("query",query);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
