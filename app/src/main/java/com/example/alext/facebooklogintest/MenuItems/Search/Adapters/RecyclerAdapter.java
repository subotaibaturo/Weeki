package com.example.alext.facebooklogintest.MenuItems.Search.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alext.facebooklogintest.MenuItems.Search.Query_DetailView;
import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Venues;
import com.example.alext.facebooklogintest.R;

import java.util.ArrayList;

/**
 * Created by DaNN on 18/10/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private ArrayList<Venues> listvenues;
    private Context context;

    public RecyclerAdapter(ArrayList<Venues> venues, Context ctx){

        this.listvenues = venues;
        this.context = ctx;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view, context, listvenues);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.Name.setText(listvenues.get(position).getName());
            holder.city.setText(listvenues.get(position).getLocationA().city);
            holder.address.setText(listvenues.get(position).getLocationA().getAddress());
            holder.distance.setText(listvenues.get(position).getLocationA().distance +" m");
            holder.category.setText(listvenues.get(position).getCategories().getName());
    }


    @Override
    public int getItemCount() {
        return listvenues.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Name,city,address,distance,category;
        Context ctx;
        ArrayList<Venues> venues =  new ArrayList<Venues>();

        public MyViewHolder(View itemView, Context ctx, ArrayList<Venues> venues) {
            super(itemView);

            this.venues = venues;
            this.ctx = ctx;
            Name = (TextView) itemView.findViewById(R.id.r_texviewName);
            city =(TextView) itemView.findViewById(R.id.r_texViewCity);
            address = (TextView) itemView.findViewById(R.id.r_textViewAdrress);
            distance = (TextView) itemView.findViewById(R.id.r_textViewDistance);
            category = (TextView) itemView.findViewById(R.id.r_textViewCategory);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int count = getAdapterPosition();
            Intent intent = new Intent(this.ctx, Query_DetailView.class);
            intent.putExtra("iditem",listvenues.get(count).getId());
            Log.d("INTENT","THE ID IS : "+ listvenues.get(count).getId());
            this.ctx.startActivity(intent);

        }
    }


}
