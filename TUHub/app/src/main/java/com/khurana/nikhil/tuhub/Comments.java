package com.khurana.nikhil.tuhub;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class Comments extends Activity implements View.OnClickListener {
    ListView l1;
    Button btn,btn1;
    EditText edt;
    String str;
    ImageButton btn2;
    int x;
    ArrayList<String> arl=new ArrayList<String>();
    ArrayList<String> a2=new ArrayList<String>();
    ArrayList<String> po=new ArrayList<String>();

    public String[] s4=new String[50];
    public String[] s5=new String[50];
    public String[] s6=new String[50];
    String mydata,user,type;
    private SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Intent in=getIntent();
        x=in.getIntExtra("position", 0);

        SharedPreferences shrd=Comments.this.getSharedPreferences("Details", Context.MODE_PRIVATE);
        user=shrd.getString("name","");
        type=shrd.getString("type", "");

       // new get().execute();
        l1 = (ListView) findViewById(R.id.ll);
        btn = (Button) findViewById(R.id.button);
        btn1=(Button)findViewById(R.id.ref1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //          new get().execute();

            }
        });

        edt = (EditText) findViewById(R.id.editText);
        edt.setBackgroundResource(R.drawable.edittext_outline);
        btn.setOnClickListener(this);




    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                str = edt.getText().toString();
                ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
                Boolean isInternetPresent = cd.isConnectingToInternet();
                Firebase ref=new Firebase("https://blazing-fire-3495.firebaseio.com/comments");
                if (isInternetPresent && !str.matches(""))
                {
                    //setText(str);
                    if(type.matches("guest"))
                    {
                        Toast.makeText(Comments.this,"You must be logged in as user to comment",Toast.LENGTH_SHORT).show();
                        edt.setText("");
                    }
                    else {
                        //    new send().execute();

                        edt.setText("");
                    }
                }

                else if (str.matches(""))
                {
                    Toast.makeText(Comments.this,"Invalid comment",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(Comments.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
                }

                break;



        }


    }
    /*public void getData(){

        try {

            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://collegeevents.esy.es/mukul1.php");

            HttpResponse response=httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            InputStream inp = httpEntity.getContent();
            BufferedReader read = new BufferedReader(new InputStreamReader(inp,"utf-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while((line =read.readLine()) != null){

                stringBuilder.append(line);

            }

            inp.close();

            mydata = stringBuilder.toString();

            JSONArray object = new JSONArray(mydata);
            arl.clear();
            a2.clear();
            po.clear();

            for(int i=0; i<object.length(); i++){

                JSONObject jsonObject = object.getJSONObject(i);

                String s1 = jsonObject.getString("position");
                String k=""+x;
                if(s1.matches(k))
                {
                    String s2=jsonObject.getString("comment");
                    String s9=jsonObject.getString("date");
                    arl.add(s2);
                    a2.add(s9);
                          po.add(jsonObject.getString("posted"));           //change here to date
                }

            }


        }catch(Exception e){
        }

    }

    public class get extends AsyncTask<String,String,String>
    {
        final ProgressDialog pg=new ProgressDialog(Comments.this);
        @Override
        protected String doInBackground(String... params) {
            getData();
            return null;
        }

        @Override
        protected void onPreExecute() {

            pg.setMessage("Refreshing...");
            pg.setIndeterminate(false);
            pg.setCancelable(true);

            pg.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            pg.dismiss();

            String[] p = new String[arl.size()];
            p=arl.toArray(p);
            s4=p;

            String[] q=new String[a2.size()];
            q = a2.toArray(q);
            s5=q;

            String[] x=new String[po.size()];
            x=po.toArray(x);
            s6=x;

            CustomComment cc=new CustomComment(Comments.this,s4,s5,s6);
            l1.setAdapter(cc);
            cc.notifyDataSetChanged();
            super.onPostExecute(s);
        }
    }


    public void sendData()
    {

        try
        {
            List<NameValuePair> list=new ArrayList<NameValuePair>();
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://collegeevents.esy.es/register.php");
            list.add(new BasicNameValuePair("position", "" + x));
            list.add(new BasicNameValuePair("comment", str));   //add date here and in php file and database
            String ss= DateFormat.getDateInstance().format(new Date());
            GregorianCalendar gcalendar = new GregorianCalendar();
            String hr=gcalendar.get(Calendar.HOUR)+"";
            String min=gcalendar.get(Calendar.MINUTE)+"";
            list.add(new BasicNameValuePair("date",ss+"  "+hr+":"+min));
            list.add(new BasicNameValuePair("posted",user));
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response=httpClient.execute(httpPost);






        }
        catch (Exception e)
        {

        }



    }
    public class send extends AsyncTask<String,String,String>
    { final ProgressDialog pg=new ProgressDialog(Comments.this);

        @Override
        protected String doInBackground(String... params) {
            sendData();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            pg.dismiss();
            new get().execute();
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            pg.setMessage("Posting your comment..");
            pg.setIndeterminate(false);
            pg.setCancelable(true);

            pg.show();
            super.onPreExecute();
        }
    }*/





}
