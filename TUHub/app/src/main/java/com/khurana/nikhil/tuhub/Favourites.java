package com.khurana.nikhil.tuhub;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Favourites extends Fragment {
    View v;
    MyDatabase db;

    SQLiteDatabase sql1;
    ListView lv;
    ArrayList<String> a1=new ArrayList<String>();
    ArrayList<String> b1=new ArrayList<String>();
    ArrayList<String> c1=new ArrayList<String>();
    ArrayList<String> d1=new ArrayList<String>();
    String[] na = new String[50];
    String[] so=new String[50];
    String[] da=new String[50];
    String[] ve=new String[50];
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_favourites,container,false);

        db=new MyDatabase(getActivity());
        lv = (ListView)v.findViewById(R.id.listView);

        a1.clear();
        b1.clear();
        c1.clear();
        d1.clear();

        sql1=db.getWritableDatabase();
        Cursor c=sql1.rawQuery("Select * from mytable",null);
        if(c.getCount()<1)
        {
            Toast.makeText(getActivity(),"No favourite events",Toast.LENGTH_SHORT).show();

        }
        else
        {


            while (c.moveToNext())
            {
                String name=c.getString(c.getColumnIndex("name"));
                String society=c.getString(c.getColumnIndex("society"));
                String date=c.getString(c.getColumnIndex("date"));
                String venue=c.getString(c.getColumnIndex("venue"));
               // tv.setText(name+society+date+venue);
                a1.add(name);
                b1.add(society);
                c1.add(date);
                d1.add(venue);



            }
            String[] s = new String[a1.size()];
            s=a1.toArray(s);
            na = s;



            String[] soc = new String[b1.size()];
            soc=b1.toArray(soc);
            so = soc;

            String[] ven = new String[c1.size()];
            ven=c1.toArray(ven);
            da = ven;

            String[] dat = new String[d1.size()];
            dat=d1.toArray(dat);
            ve = dat;
            CustomFav cad = new CustomFav(getActivity(),na,so,da,ve);
            lv.setAdapter(cad);

            cad.notifyDataSetChanged();





        }





        return v;
    }

}
