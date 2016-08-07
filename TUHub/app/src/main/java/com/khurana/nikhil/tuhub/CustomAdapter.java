package com.khurana.nikhil.tuhub;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


import java.io.InputStream;
import java.net.URL;

/**
 * Created by Nikhil on 13-07-2015.
 */
public class CustomAdapter extends ArrayAdapter<String> {

    Context c1;

    String s1[],soc[],ven[],dat[],ur[];
    int s2[];
    CustomAdapter(Context c,String s[],String society[],String venue[],String date[],String url[])
    {
        super(c, R.layout.listcustom, s);
        this.c1=c;
        this.s1=s;
        this.ur=url;
        this.soc=society;
        this.ven=venue;
        this.dat=date;
    }


    @Override
    public View getView(int position, View v, ViewGroup parent) {
        LayoutInflater li=(LayoutInflater) c1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=li.inflate(R.layout.listcustom,parent,false);

        TextView tv=(TextView)v.findViewById(R.id.textView);
        TextView tv1=(TextView)v.findViewById(R.id.society);
        TextView tv2=(TextView)v.findViewById(R.id.venue);
        TextView tv3=(TextView)v.findViewById(R.id.date);
        ImageView img=(ImageView)v.findViewById(R.id.imageview);
        Glide.with(getContext()).load(ur[position]).into(img);
        // Picasso.with(getContext()).load(ur[position]).into(img);
        tv.setText(s1[position]);
        tv1.setText(soc[position]);
        tv2.setText(ven[position]);
        tv3.setText(dat[position]);

        if(position%2==0) {
            tv.setTextColor(Color.parseColor("#01579B"));
            tv3.setTextColor(Color.parseColor("#01579B"));

        }
        else{
            tv.setTextColor(Color.parseColor("#00897B"));
            tv3.setTextColor(Color.parseColor("#00897B"));

        }

        v.setTag(position);

        return v;
    }
}
