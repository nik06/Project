package com.khurana.nikhil.tuhub;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.provider.AlarmClock;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.khurana.nikhil.tuhub.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.khurana.nikhil.tuhub.R.layout.listcustom;


public class FirstScreen extends Fragment {
    View v;
    TextView tv;
    ListView lv;
    ImageView img1;
    TextView tv1, tv2, tv3, tv4;
    SharedPreferences shr;
    SharedPreferences.Editor ed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_first_screen, container, false);
        shr = this.getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        tv1 = (TextView) v.findViewById(R.id.textView);
        lv = (ListView) v.findViewById(R.id.listView);
        Firebase ref = new Firebase("https://blazing-fire-3495.firebaseio.com");
        Firebase mref = ref.child("events");
       final FirebaseListAdapter<Event> adapter = new FirebaseListAdapter<Event>(getActivity(), Event.class, R.layout.listcustom, mref) {
            @Override
            protected void populateView(View view, Event event, int i) {
                TextView tv = (TextView) view.findViewById(R.id.textView);
                TextView tv11 = (TextView) view.findViewById(R.id.society);
                TextView tv2 = (TextView) view.findViewById(R.id.venue);
                TextView tv3 = (TextView) view.findViewById(R.id.date);
                ImageView img = (ImageView) view.findViewById(R.id.imageview);
                //Glide.with(getActivity()).load(event.getUrl()).into(img);
                // Picasso.with(getContext()).load(ur[position]).into(img);
                tv.setText(event.getName());
                tv11.setText(event.getSociety());
                tv2.setText(event.getVenue());
                tv3.setText(event.getDate());
            }
        };
        final ProgressDialog pg=new ProgressDialog(getActivity());
        pg.setMessage("Loading..");
        pg.setIndeterminate(false);
        pg.setCancelable(true);
        pg.show();
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                pg.dismiss();


                super.onChanged();

            }
        });
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                          Firebase e=adapter.getRef(position);
                                          Intent in = new Intent(getActivity(),EventClick.class);

                                          String hello=e.toString();
                                          in.putExtra("ref", hello);
                                          startActivity(in);



                                      }
                                  }
        );


        return v;

    }
}