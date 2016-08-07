package com.khurana.nikhil.tuhub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;

/**
 * Created by Mukul on 19-07-2015.
 */
public class ForumThread extends Fragment{

    ListView lv;
    String mydata,type,user;
    Button btn3,btn4;
  //  ArrayList<String> title = new ArrayList<String>();
   // ArrayList<String> time = new ArrayList<String>();
    //ArrayList<String> posted=new ArrayList<String>();
    public String[] str1 = new String[50];
    public String[] str2 = new String[50];
    private SwipeRefreshLayout swipeRefreshLayout;
    public String[] str3=new String[50];
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_forum_thread,container,false);
        lv = (ListView)v.findViewById(R.id.listView);
        btn3 = (Button)v.findViewById(R.id.button1);
        btn4 = (Button)v.findViewById(R.id.button2);
        SharedPreferences shrd=getActivity().getSharedPreferences("Details", Context.MODE_PRIVATE);
        user=shrd.getString("name","");
        type=shrd.getString("type","");
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type.matches("guest"))
                {
                    Toast.makeText(getActivity(),"You must be logged in as user to post",Toast.LENGTH_SHORT).show();
                }

                else {
                Intent in=new Intent(getActivity(),NewPost.class);
                startActivity(in);

                }

            }
        });
        Firebase ref=new Firebase("https://blazing-fire-3495.firebaseio.com/comments");
        FirebaseListAdapter<Comment> adapter = new FirebaseListAdapter<Comment>(getActivity(), Comment.class, R.layout.customlist, ref) {
            @Override
            protected void populateView(View view, Comment c, int i) {
                TextView posted=(TextView)view.findViewById(R.id.posted);
                TextView com=(TextView)view.findViewById(R.id.tv1);
                TextView time=(TextView)view.findViewById(R.id.tv2);
                posted.setText(c.getTitle());
                com.setText(c.getTime());
                time.setText(c.getPosted());

            }


        };
        lv.setAdapter(adapter);

      /*  swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light);
                swipeRefreshLayout.setRefreshing(true);
                ConnectionDetector cd = new ConnectionDetector(getActivity());
                Boolean isInternetPresent = cd.isConnectingToInternet();
                if (isInternetPresent) {


    //                new mydata().execute();
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }

        });

*/
  //      new mydata().execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), Comments.class);

                intent.putExtra("position", position);

                startActivity(intent);

            }
        });



return v;




    }



/*    public class mydata extends AsyncTask<String, String, String>{


        @Override
        protected String doInBackground(String... params) {

            getData();

            return null;
        }

        @Override
        protected void onPreExecute() {
            swipeRefreshLayout.setRefreshing(true);

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(str1.length>40)
            {

                lv.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
            else
            {

                CustomThread custadp = new CustomThread(getActivity(), str1,str2,str3);
            lv.setAdapter(custadp);
            custadp.notifyDataSetChanged();

            }swipeRefreshLayout.setRefreshing(false);

        }
    }
*/
/*    public void getData(){

        try {

            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost("http://collegeevents.esy.es/mukul.php");
            HttpResponse response = httpClient.execute(httpPost);
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

            time.clear();
            title.clear();
            posted.clear();

            for(int i=0; i<object.length(); i++){


                JSONObject jsonObject = object.getJSONObject(i);
                title.add(i, jsonObject.getString("Title"));
                time.add(i,jsonObject.getString("Time"));
                posted.add(i,jsonObject.getString("posted"));

            }

            String[] strings = new String[title.size()];
            strings = title.toArray(strings);
            str1 = strings;


            String[] strings1 = new String[time.size()];
            strings1 = time.toArray(strings1);
            str2 =strings1;

            String[] strings3=new String[posted.size()];
            strings3 = posted.toArray(strings3);
            str3=strings3;



        }catch(Exception e){

        }

    }


*/

}
