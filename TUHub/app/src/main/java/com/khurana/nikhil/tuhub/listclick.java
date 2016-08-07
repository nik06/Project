package com.khurana.nikhil.tuhub;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;




public class listclick extends Fragment {
    int s1;
    TextView tv1, tv2, tv3, tv4, tv5, tv6;
    ImageView img1;
    View v;
    String name, details;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_listclick, container, false);
        if (getActivity().getIntent().getExtras() != null) {
            s1 = getActivity().getIntent().getIntExtra("position", 0);
            Intent in = new Intent();
            name = getActivity().getIntent().getStringExtra("ref");
        }
        //Toast.makeText(getActivity(), name, Toast.LENGTH_LONG).show();
        Firebase eref = new Firebase(name);
        tv1 = (TextView) v.findViewById(R.id.a);
        tv2 = (TextView) v.findViewById(R.id.c);
        tv3 = (TextView) v.findViewById(R.id.e);
        tv4 = (TextView) v.findViewById(R.id.g);
        tv5 = (TextView) v.findViewById(R.id.i);
        tv6 = (TextView) v.findViewById(R.id.k);
        img1 = (ImageView) v.findViewById(R.id.l);
        eref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Event obj = dataSnapshot.getValue(Event.class);
                tv1.setText(obj.getName());
                tv2.setText(obj.getDetails());
                tv3.setText(obj.getRlink());
                tv4.setText(obj.getRfee());
                tv5.setText(obj.getContact());
                tv6.setText(obj.getEmail());
                Glide.with(getActivity()).load(obj.getUrl()).into(img1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), webform.class);

                in.putExtra("url", tv3.getText().toString());
                startActivity(in);

            }
        });


        return v;


    }
}


