package com.khurana.nikhil.tuhub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class EventRegister extends Fragment {
    View v;

    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10;
    ProgressDialog progressBar;
    Button bt1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_event_register,container,false);
        SharedPreferences shrd=this.getActivity().getSharedPreferences("Details", Context.MODE_PRIVATE);
        String shrsoc=shrd.getString("name", "society");


        ed1=(EditText)v.findViewById(R.id.m);
        ed2=(EditText)v.findViewById(R.id.n);
        ed3=(EditText)v.findViewById(R.id.o);
        ed4=(EditText)v.findViewById(R.id.p);
        ed5=(EditText)v.findViewById(R.id.q);
        ed6=(EditText)v.findViewById(R.id.r);
        ed7=(EditText)v.findViewById(R.id.s);
        ed8=(EditText)v.findViewById(R.id.t);
        ed9=(EditText)v.findViewById(R.id.u);
        ed10=(EditText)v.findViewById(R.id.v);
        ed2.setText(shrsoc);
        bt1=(Button)v.findViewById(R.id.reg);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectionDetector cd = new ConnectionDetector(getActivity());
                Boolean isInternetPresent = cd.isConnectingToInternet();
                if(isInternetPresent) {
                    s1 = ed1.getText().toString();
                    s2 = ed2.getText().toString();
                    s3 = ed3.getText().toString();
                    s4 = ed4.getText().toString();
                    s5 = ed5.getText().toString();
                    s6 = ed6.getText().toString();
                    s7 = ed7.getText().toString();
                    s8 = ed8.getText().toString();
                    s9 = ed9.getText().toString();
                    s10 = ed10.getText().toString();
//                    new MyData().execute();
                    Firebase eref=new Firebase("https://blazing-fire-3495.firebaseio.com/events");
                    Event e= new Event(s1,s2,s4,s3,s8,s7,s5,s6,s10,s9);
                    eref.push().setValue(e);
                    Toast.makeText(getActivity(),"Details sent for verification",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                }


            }
        });

return v;

    }
   /* public class MyData extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();



        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


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
            List<NameValuePair> list=new ArrayList<NameValuePair>();
            HttpClient httpClient=new DefaultHttpClient();

            HttpPost httpPost=new HttpPost("http://collegeevents1.esy.es/create_product.php");
            list.add(new BasicNameValuePair("name",s1));
            list.add(new BasicNameValuePair("society",s2));
            list.add(new BasicNameValuePair("date",s4));
            list.add(new BasicNameValuePair("venue",s3));
            list.add(new BasicNameValuePair("url",s8));
            list.add(new BasicNameValuePair("fee",s6));
            list.add(new BasicNameValuePair("details",s7));
            list.add(new BasicNameValuePair("phone",s9));
            list.add(new BasicNameValuePair("mail",s10));
            list.add(new BasicNameValuePair("rlink", s5));

            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response=httpClient.execute(httpPost);





        }
        catch (Exception e)
        {

        }


    }

*/


}
