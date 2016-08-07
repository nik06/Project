package com.khurana.nikhil.tuhub;

import com.khurana.nikhil.tuhub.TabsMain;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class EventClick extends FragmentActivity implements
        ActionBar.TabListener {

    private ViewPager viewPager;
    MyDatabase db;
    SQLiteDatabase sql1;
    private TabsMain mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Event", "Society Details" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_society);

        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsMain(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_event_click, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_example:



                SharedPreferences sh=EventClick.this.getSharedPreferences("Data", Context.MODE_PRIVATE);
                String name=sh.getString("name", "");
                String society=sh.getString("society","");
                String date=sh.getString("date","");
                String venue=sh.getString("venue","");
                SharedPreferences shr=EventClick.this.getSharedPreferences("Data", Context.MODE_PRIVATE);
                SharedPreferences.Editor e=shr.edit();
                e.clear();
                e.commit();
                //Toast.makeText(EventClick.this,name+society+date+venue,Toast.LENGTH_SHORT).show();
                db=new MyDatabase(EventClick.this);
                sql1=db.getWritableDatabase();
                ContentValues cv=new ContentValues();
                cv.put("name",name);
                cv.put("society",society);
                cv.put("date",date);
                cv.put("venue",venue);
                long result=sql1.insert("mytable","fdfh",cv);
                if (result>0)
                {
                    Toast.makeText(EventClick.this,"Event added as favourite",Toast.LENGTH_SHORT).show();


                }
                else
                Toast.makeText(EventClick.this,"Failed to add as favourite",Toast.LENGTH_SHORT).show();




                return true;
            case R.id.action_settings:
                SharedPreferences shrd=EventClick.this.getSharedPreferences("Details", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=shrd.edit();
                ed.clear();
                ed.commit();
                Toast.makeText(EventClick.this, "You have successfully logged out.", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(EventClick.this,LoginScreen.class);
                startActivity(i);
                finish();


                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

}