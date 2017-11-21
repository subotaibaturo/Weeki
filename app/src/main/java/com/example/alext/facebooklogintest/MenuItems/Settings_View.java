package com.example.alext.facebooklogintest.MenuItems;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.test.mock.MockApplication;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alext.facebooklogintest.R;

/**
 * Created by DaNN on 16/11/2017.
 */

public class Settings_View extends AppCompatActivity {

    Switch aSwitch;
    int radio;
    SeekBar radiobar;

    Button b1,b2,b3,b4,b5,b6;

    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    String txt1,txt2,txt3,txt4,txt5,txt6;

    boolean customcoor = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Settings_toolbar);
        setSupportActionBar(toolbar);

        getInfo();

        aSwitch = (Switch) findViewById(R.id.Settings_switch);
        radiobar = (SeekBar) findViewById(R.id.seekBar);

        tv1 = (TextView) findViewById(R.id.Settings_Button1_res);
        tv2 = (TextView) findViewById(R.id.Settings_Button2_res);
        tv3 = (TextView) findViewById(R.id.Settings_Button3_res);
        tv4 = (TextView) findViewById(R.id.Settings_Button4_res);
        tv5 = (TextView) findViewById(R.id.Settings_Button5_res);
        tv6 = (TextView) findViewById(R.id.Settings_Button6_res);


        b1 = (Button) findViewById(R.id.Settings_Button1_action);
        b2 = (Button) findViewById(R.id.Settings_Button2_action);
        b3 = (Button) findViewById(R.id.Settings_Button3_action);
        b4 = (Button) findViewById(R.id.Settings_Button4_action);
        b5 = (Button) findViewById(R.id.Settings_Button5_action);
        b6 = (Button) findViewById(R.id.Settings_Button6_action);

        tv1.setText(txt1);
        tv2.setText(txt2);
        tv3.setText(txt3);
        tv4.setText(txt4);
        tv5.setText(txt5);
        tv6.setText(txt6);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder al =  new AlertDialog.Builder(Settings_View.this);
                //View mview = getLayoutInflater().inflate(R.layout.alertdialog_buttonsetting,null);
                View mview = LayoutInflater.from(Settings_View.this).inflate(R.layout.alertdialog_buttonsetting,null);
                final EditText editText = (EditText) mview.findViewById(R.id.alertdialog_edittext);
                //editText.setText("hola");
                al.setView(mview).setMessage("Cambiar Button 1").setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //String r = editText.getText().toString();

                        SharedPreferences pref = getSharedPreferences("Settings",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        String res = editText.getText().toString();
                        editor.putString("button1",res);
                        editor.apply();
                        Button b = (Button) findViewById(R.id.Button1);
                       b.setText(res);
                        //tv1.setText(res);

                        //Log.d("AlertDialog","Result to save is: "+r);
                        //Toast.makeText( getApplication(),"eL TEXTO ES: "+r,Toast.LENGTH_SHORT);
                        //finish();


                    }
                }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //finish();
                    }
                });

                /*
                Button save = (Button) findViewById(R.id.alertdialog_save);
                Button cancel = (Button) findViewById(R.id.alerdialog_cancel);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences pref = getSharedPreferences("Settings",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        String res = editText.getText().toString();
                        editor.putString("button1",res);
                        editor.apply();
                        tv1.setText(res);
                        finish();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                */
                AlertDialog dialog = al.create();
                dialog.show();
            }
        });




        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setRadioOn(b);
                customcoor = b;
                if(customcoor){
                    radiobar.setEnabled(true);
                }
                else
                    radiobar.setEnabled(false);
            }
        });
    }

    public void saveInfo(){
        //crear
        SharedPreferences sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putBoolean("Noradio",false);


        editor.putString("button1","Desayuno");
        editor.putString("button2","Bar");
        editor.putString("button3","Cafe");
        editor.putString("button4","Cenaduria");
        editor.putString("button5","Italiana");
        editor.putString("button6","Tacos");

        editor.apply();


    }

    public void setRadioOn(boolean b){
        SharedPreferences sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Noradio",b);

    }
    public void getInfo(){

        SharedPreferences sharedPref = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        customcoor = sharedPref.getBoolean("Noradio",false);
        txt1 = sharedPref.getString("button1","");
        txt2 = sharedPref.getString("button2","");
        txt3 = sharedPref.getString("button3","");
        txt4 = sharedPref.getString("button4","");
        txt5 = sharedPref.getString("button5","");
        txt6 = sharedPref.getString("button6","");

    }
}
