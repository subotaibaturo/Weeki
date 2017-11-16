package com.example.alext.facebooklogintest.MenuItems.Search.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Hour.Hours;
import com.example.alext.facebooklogintest.R;

import java.util.ArrayList;

/**
 * Created by DaNN on 14/11/2017.
 */

public class HourRecyclerAdapter extends RecyclerView.Adapter<HourRecyclerAdapter.MyViewHolder> {

    Context ctx;
    ArrayList<Hours.Timeframes> listtm;
    public HourRecyclerAdapter(ArrayList<Hours.Timeframes> tm,Context ctx){
        this.listtm = tm;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        if(position>1)
            position=1;

        String d = listtm.get(position).getDays();
        String r = listtm.get(position).getListrenderedtime().get(0).getRenderedtime();


        if(d!=null||d!="")
            holder.v1.setText(d);
        else
            holder.v1.setText("");

        if(r!=null||r!="")
            holder.v2.setText(r);
        else
            holder.v2.setText("");

    }

    @Override
    public int getItemCount() {
        return listtm.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView v1,v2;
        public MyViewHolder(View itemView) {
            super(itemView);
            v1 = (TextView) itemView.findViewById(R.id.hours_textview_days);
            v2 = (TextView) itemView.findViewById(R.id.hours_textview_rangehour);

        }

    }
}
