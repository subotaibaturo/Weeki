package com.example.alext.facebooklogintest.MenuItems.Search.Adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alext.facebooklogintest.MenuItems.Search.JsonObjects.VenuesObjects.Hour.Hours;
import com.example.alext.facebooklogintest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaNN on 14/11/2017.
 */

public class HoursAdapter extends ArrayAdapter<Hours.Timeframes>{
    ArrayList<Hours.Timeframes> listtime;
    Context context;
    int layout_resource;

    public HoursAdapter(Context context, int resource, ArrayList<Hours.Timeframes> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listtime = objects;
        this.layout_resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.hour_item,null);
        TextView days = (TextView) v.findViewById(R.id.hours_textview_days);
        TextView range = (TextView) v.findViewById(R.id.hours_textview_rangehour);
        int temp = position;
        if(position>=1)
            temp=1;
        String d = listtime.get(temp).getDays();
        String r = listtime.get(temp).getListrenderedtime().get(0).getRenderedtime();


        //LayoutInflater inflater = LayoutInflater.from(context);
        //convertView = inflater.inflate(layout_resource, parent,false);

        //TextView days = (TextView) convertView.findViewById(R.id.hours_textview_days);
       // TextView range = (TextView) convertView.findViewById(R.id.hours_textview_rangehour);
        if(position<=1) {
            if (d != null || d != "")
                days.setText(d);
            else
                days.setText("");

            if (r != null || r != "")
                range.setText(r);
            else
                range.setText("");

        }
        else {
            days.setText("");
            range.setText("");
        }
            return v ;
    }

/*
    View v = View.inflate(context, R.layout.hour_item,null);
    TextView days = (TextView) v.findViewById(R.id.hours_textview_days);
    TextView range = (TextView) v.findViewById(R.id.hours_textview_rangehour);

    String d = hours.getTimeframes().get(i).getDays();
    String r = hours.getTimeframes().get(i).getListrenderedtime().get(i).getRenderedtime();

        if(d!=null||d!="")
            days.setText(d);
        else
                days.setText("");

        if(r!=null||r!="")
            range.setText(r);
        else
                range.setText("");

    */
}
