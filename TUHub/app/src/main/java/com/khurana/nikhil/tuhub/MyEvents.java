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
import android.support.v4.app.Fragment;
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
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MyEvents extends Fragment {
    View v;
    TextView tv;
    ListView lv;
    ImageView img1;
    TextView tv1,tv2,tv3,tv4;

    String shrdname;

    String mydata,name,name1,society,date,venue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_my_events,container,false);

        SharedPreferences shrd=this.getActivity().getSharedPreferences("Details", Context.MODE_PRIVATE);
        String type=shrd.getString("type", "");
        shrdname=shrd.getString("name","");


        tv1=(TextView)v.findViewById(R.id.textView);
        lv = (ListView)v.findViewById(R.id.listView);

        Firebase mref=new Firebase("https://blazing-fire-3495.firebaseio.com/events");

        final FirebaseListAdapter<Event> adapter = new FirebaseListAdapter<Event>(getActivity(), Event.class, R.layout.listcustom, mref.orderByChild("society").equalTo(shrdname)) {
            @Override
            protected void populateView(View view, Event event, int i) {

                if(shrdname.matches(event.getSociety())) {
                    TextView tv = (TextView) view.findViewById(R.id.textView);
                    TextView tv11 = (TextView) view.findViewById(R.id.society);
                    TextView tv2 = (TextView) view.findViewById(R.id.venue);
                    TextView tv3 = (TextView) view.findViewById(R.id.date);
                    ImageView img = (ImageView) view.findViewById(R.id.imageview);
                    Glide.with(getActivity()).load(event.getUrl()).into(img);
                    // Picasso.with(getContext()).load(ur[position]).into(img);
                    tv.setText(event.getName());
                    tv11.setText(event.getSociety());
                    tv2.setText(event.getVenue());
                    tv3.setText(event.getDate());
                }
            }
        };
        final ProgressDialog pg=new ProgressDialog(getActivity());
        pg.setMessage("Loading..");
        pg.setIndeterminate(false);
        pg.setCancelable(false);
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

/*
    public class MyData extends AsyncTask<String,String,String>
    {
        final ProgressDialog pg=new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute() {
            pg.setMessage("Loading..");
            pg.setIndeterminate(false);
            pg.setCancelable(true);

            pg.show();
            super.onPreExecute();


        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s1.length>40) {
                pg.dismiss();
                lv.setVisibility(View.INVISIBLE);
               // tv1.setText(mydata + shrdname + " 1 " + a.size());
                Toast.makeText(getActivity(),"Network Error",Toast.LENGTH_SHORT).show();
            }
            else if(s1.length==0) {
                pg.dismiss();
                lv.setVisibility(View.INVISIBLE);
              //  tv1.setText(mydata + shrdname + " 2 " + a.size());
                Toast.makeText(getActivity(),"No Events to show",Toast.LENGTH_SHORT).show();

            }

            else {
               // tv1.setText(shrdname);
                pg.dismiss();
              //  tv1.setText(mydata+shrdname+" 3 "+a.size());
                CustomAdapter cad = new CustomAdapter(getActivity(), s1,s2,s3,s4,s5);
                lv.setAdapter(cad);

                cad.notifyDataSetChanged();}
        }

        @Override
        protected String doInBackground(String... params) {
            getData();
            return null;
        }
    }

    public void getData()
    {
        try {
            HttpClient httpClient=new DefaultHttpClient();

            HttpPost httpPost=new HttpPost("http://collegeevents.esy.es/abc.php");
            HttpResponse response=httpClient.execute(httpPost);
            HttpEntity httpEntity=response.getEntity();
            InputStream is=httpEntity.getContent();
            BufferedReader reader=new BufferedReader(new InputStreamReader(is,"utf-8"),8);
            StringBuilder strbuilder=new StringBuilder();
            String line=null;
            while ((line=reader.readLine())!=null)
            {
                strbuilder.append(line);
            }
            is.close();
            mydata=strbuilder.toString();
            JSONArray obj=new JSONArray(mydata);
              //remove

            a.clear();
            b.clear();
            c.clear();
            d.clear();
            e.clear();
            int k=0;
            for(int i=obj.length()-1;i>=0;i--)
            {

                JSONObject obj1=obj.getJSONObject(i);
                String sname=obj1.getString("society");

               if(sname.matches(shrdname))
                {
                a.add(k,obj1.getString("Name"));
                b.add(k,obj1.getString("society"));
                c.add(k,obj1.getString("venue"));
                d.add(k,obj1.getString("date"));
                e.add(k,obj1.getString("url"));

                    k++;
               }

            }
           // tv1.setText(" "+a.size());

            String[] s = new String[a.size()];
            s=a.toArray(s);
            s1 = s;



            String[] soc = new String[b.size()];
            soc=b.toArray(soc);
            s2 = soc;

            String[] ven = new String[c.size()];
            ven=c.toArray(ven);
            s3 = ven;

            String[] dat = new String[d.size()];
            dat=d.toArray(dat);
            s4 = dat;

            String[] url=new String[e.size()];
            url=e.toArray(url);
            s5=url;
        }
        catch (Exception e)
        {

        }


    }*/

}
