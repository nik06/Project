package com.khurana.nikhil.tuhub;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyProfile extends Fragment {
    View v;
    TextView tv1,tv2,tv3,tv4;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_my_profile,container,false);
        tv1=(TextView)v.findViewById(R.id.name);
        tv2=(TextView)v.findViewById(R.id.roll);
        tv3=(TextView)v.findViewById(R.id.phone);
        tv4=(TextView)v.findViewById(R.id.mail);
        SharedPreferences shrd=this.getActivity().getSharedPreferences("Details", Context.MODE_PRIVATE);
        String type=shrd.getString("type","");
        if(type.matches("guest"))
        {
            tv1.setText("Guest");
            tv2.setText(" - ");
            tv3.setText("    - ");
            tv4.setText("    - ");
        }
        else
        if(type.matches("society"))
        {
            tv1.setText(shrd.getString("name","    -"));
            tv2.setText("    - ");
            tv3.setText("    - ");
            tv4.setText("    - ");
        }
        else
        {
            tv1.setText(shrd.getString("name","    -"));
            tv2.setText(shrd.getString("roll","    -"));
            tv3.setText(shrd.getString("contact","    -"));
            tv4.setText(shrd.getString("email","    -"));


        }


        return v;
    }
}
