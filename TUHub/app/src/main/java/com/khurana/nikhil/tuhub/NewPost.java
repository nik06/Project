package com.khurana.nikhil.tuhub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewPost extends Activity {

    EditText ed1;
    String user,type;
    Button bt1;
    String s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        Intent in=getIntent();
        ed1=(EditText)findViewById(R.id.edtext);
        bt1=(Button)findViewById(R.id.post);
        SharedPreferences shrd=NewPost.this.getSharedPreferences("Details", Context.MODE_PRIVATE);
         user=shrd.getString("name","");
        type=shrd.getString("type","");

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s1 = ed1.getText().toString();
                if (s1.matches("")) {
                    Toast.makeText(NewPost.this, "Please enter a valid post", Toast.LENGTH_SHORT).show();

                } else {
                    ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
                    Boolean isInternetPresent = cd.isConnectingToInternet();
                    if (isInternetPresent) {

                       // Toast.makeText(NewPost.this, "No Internet Connectionasa", Toast.LENGTH_SHORT).show();
                        Firebase ref=new Firebase("https://blazing-fire-3495.firebaseio.com/comments");
                        String ss= DateFormat.getDateInstance().format(new Date());
                        GregorianCalendar gcalendar = new GregorianCalendar();
                        String hr=gcalendar.get(Calendar.HOUR)+"";
                        String min=gcalendar.get(Calendar.MINUTE) + "";
                        Comment post=new Comment(s1,user,ss+"  "+hr+":"+min);
                        ref.push().setValue(post);
              //          new send().execute();
                    } else {
                        Toast.makeText(NewPost.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });


    }
 /*   public void sendData(){

        try {
            List<NameValuePair> list=new ArrayList<NameValuePair>();
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://collegeevents.esy.es/post.php");
            list.add(new BasicNameValuePair("Title",s1));
            String ss= DateFormat.getDateInstance().format(new Date());
            GregorianCalendar gcalendar = new GregorianCalendar();
            String hr=gcalendar.get(Calendar.HOUR)+"";
            String min=gcalendar.get(Calendar.MINUTE)+"";
            list.add(new BasicNameValuePair("Time",ss+"  "+hr+":"+min));
            list.add(new BasicNameValuePair("posted",user));
            //list.add(new BasicNameValuePair("comment",str));   add date here and in php file and database
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response=httpClient.execute(httpPost);





        }catch(Exception e){

        }






    }
    public class send extends AsyncTask<String,String,String>
    { final ProgressDialog pg=new ProgressDialog(NewPost.this);

        @Override
        protected String doInBackground(String... params) {
            sendData();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            pg.dismiss();
            Intent in=new Intent(NewPost.this,MainActivity.class);      //change here
            startActivity(in);
            finish();
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            pg.setMessage("Loading..");
            pg.setIndeterminate(false);
            pg.setCancelable(true);

            pg.show();
            super.onPreExecute();
        }
    }

*/
}
