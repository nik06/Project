package com.khurana.nikhil.tuhub;

import com.firebase.client.Firebase;

/**
 * Created by nikhil on 20-04-2016.
 */
public class Cloud extends android.app.Application {

    public void onCreate()
    {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

}
