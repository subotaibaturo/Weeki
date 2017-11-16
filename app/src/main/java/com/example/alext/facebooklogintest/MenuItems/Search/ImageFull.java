package com.example.alext.facebooklogintest.MenuItems.Search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.alext.facebooklogintest.R;

/**
 * Created by DaNN on 09/11/2017.
 */

public class ImageFull extends AppCompatActivity {
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullimage);
        String imageurl= getIntent().getExtras().getString("full");


        Log.d("ImageHD","la url es:" +imageurl);
        imageView = (ImageView) findViewById(R.id.imageView_fullimage);
        Glide.with(this).load(imageurl).into(imageView);

    }
}
