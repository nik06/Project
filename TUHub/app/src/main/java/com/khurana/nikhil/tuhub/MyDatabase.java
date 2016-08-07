package com.khurana.nikhil.tuhub;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nikhil on 23-06-2015.
 */
public class MyDatabase extends SQLiteOpenHelper {

    public MyDatabase(Context context) {
        super(context,"mydatabse",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table mytable(_id Integer Primary key autoincrement,name Text,society Text,date Text,venue Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}

