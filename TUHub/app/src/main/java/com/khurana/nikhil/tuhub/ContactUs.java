package com.khurana.nikhil.tuhub;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ContactUs extends Fragment {
    View v;
    ImageView img1,img2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_contact_us,container,false);
        img1=(ImageView)v.findViewById(R.id.gmail);
        img2=(ImageView)v.findViewById(R.id.fb);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = "khurananikhil30@gmail.com";
                Intent intent = new Intent(Intent.ACTION_SEND);
                 intent.setData(Uri.parse("mailto:"));
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{to});

                startActivity(intent);


            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.facebook.com";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);



            }
        });
        return v;
    }


}
