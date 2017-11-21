package com.example.alext.facebooklogintest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alext.facebooklogintest.MenuItems.Menu_activity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;

public class Login extends AppCompatActivity implements  View.OnClickListener , GoogleApiClient.OnConnectionFailedListener{

    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;

    private SignInButton googleSignin;
    private Button signOut;
    private boolean loginGoogle, loginFacebook;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
    private String not_log_in ="Conectiong Error ";

    FusedLocationProviderClient locs;
    private Boolean mLocationPermissionsGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        loginGoogle =false;
        loginFacebook=false;
        loginButton = (LoginButton)findViewById(R.id.id_fb_login_button);
        textView = (TextView)findViewById(R.id.id_text_view);
        callbackManager = CallbackManager.Factory.create();

        signOut = (Button) findViewById(R.id.button_skip);
        googleSignin = (SignInButton) findViewById(R.id.google_button);
        saveInfo();
        googleSignin.setOnClickListener(this);
        signOut.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

        locs =  LocationServices.getFusedLocationProviderClient(this);
        //Task location = locs.getLastLocation();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                textView.setText("Login successuful\n" +
                        loginResult.getAccessToken().getUserId() + "\n" +
                        loginResult.getAccessToken().getToken());

                    loginFacebook = true;
                    loginGoogle = false;
                    Log.d("Loginfacebook", "Se va a hacer el intent de facebook");
                    Intent intent = new Intent(Login.this, Menu_activity.class);
                    intent.putExtra("flagF", loginFacebook);
                    intent.putExtra("flagG", loginGoogle);
                    startActivity(intent);
                }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }

        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.google_button:
                GoogleSignIn();break;
            case R.id.button_skip:
                skipLogin();break;
            //case R.id.id_fb_login_button:
               // FacebookSignIn();break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){


        callbackManager.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    private void GoogleSignIn(){

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        intent.putExtra("flagG",loginGoogle);
        startActivityForResult(intent, REQ_CODE);

    }


    private void skipLogin(){
        loginGoogle = false;
        loginFacebook =false;
        Intent intent = new Intent(Login.this,Menu_activity.class);
        intent.putExtra("flagG",loginFacebook);
        intent.putExtra("flagF",loginGoogle);
        startActivity(intent);
    }

    private void handleResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            loginGoogle =true;
            loginFacebook=false;
            Log.d("01","LOGIN SUCCESS");

            Intent intent = new Intent(Login.this,Menu_activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("flagG",loginGoogle);
            intent.putExtra("flagF",loginFacebook);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, not_log_in, Toast.LENGTH_SHORT).show();
        }

    }

    public void saveInfo(){
        //crear
        SharedPreferences sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("radio",250);
        //editor.putString("radio","");
        editor.putBoolean("Noradio",false);
        editor.putString("longitud","");
        editor.putString("latitud","");

        editor.putString("button1","Desayuno");
        editor.putString("button2","Bar");
        editor.putString("button3","Cafe");
        editor.putString("button4","Cenaduria");
        editor.putString("button5","Italiana");
        editor.putString("button6","Tacos");

        editor.apply();


    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
