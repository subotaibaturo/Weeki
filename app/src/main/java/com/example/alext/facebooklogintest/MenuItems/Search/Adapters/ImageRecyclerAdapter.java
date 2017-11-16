package com.example.alext.facebooklogintest.MenuItems.Search.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Photos.PhotoItems;
import com.example.alext.facebooklogintest.R;

import java.util.ArrayList;

/**
 * Created by DaNN on 07/11/2017.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.MyViewHolder> {
    ArrayList<PhotoItems> listPhotos = new ArrayList<>();
    Activity activity;

    public ImageRecyclerAdapter(ArrayList<PhotoItems> arrayList, Context ctx){
            this.listPhotos=arrayList;
            activity = (Activity) ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String imageUrlPath = listPhotos.get(position).getIMG("200","200");
        Glide.with(activity).load(imageUrlPath).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listPhotos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.image_itemView);

        }
    }
}
