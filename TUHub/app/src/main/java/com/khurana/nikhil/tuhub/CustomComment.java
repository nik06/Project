package com.khurana.nikhil.tuhub;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Mukul on 22-07-2015.
 */
public class CustomComment extends ArrayAdapter<String> {

    Context c1;
    String s1[],s2[],s3[];
    CustomComment(Context c,String a[],String b[],String po[])
    {
        super(c,R.layout.customcomment,a);
        this.s1=a;
        this.c1=c;
        this.s2=b;
        this.s3=po;


    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v=in.inflate(R.layout.customcomment,parent,false);

        TextView tv1=(TextView)v.findViewById(R.id.ct);
        TextView tv2=(TextView)v.findViewById(R.id.dt);
        TextView tv3=(TextView)v.findViewById(R.id.posted);
        LinearLayout l=(LinearLayout)v.findViewById(R.id.linl);
       // l.setBackground(getContext().getResources().getDrawable(Color.parseColor("#000000")));
        if(position%2==0)
        {
        //  l.setBackgroundResource(Color.parseColor("#4fc3f7"));
        tv3.setTextColor(Color.parseColor("#01579B"));


        }
        else
        tv3.setTextColor(Color.parseColor("#00897B"));

        tv1.setText(s1[position]);
        tv2.setText(s2[position]);
        tv3.setText(s3[position]);
        v.setTag(position);


        return v ;


    }
}


