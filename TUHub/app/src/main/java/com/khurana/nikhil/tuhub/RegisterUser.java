package com.khurana.nikhil.tuhub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;


public class RegisterUser extends Activity {

    String s1, s2, s3, s4, s5, s6;
    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        final Firebase eref = new Firebase("https://blazing-fire-3495.firebaseio.com");
        ed1 = (EditText) findViewById(R.id.username);
        ed2 = (EditText) findViewById(R.id.email);
        ed3 = (EditText) findViewById(R.id.roll);
        ed4 = (EditText) findViewById(R.id.contact);
        ed5 = (EditText) findViewById(R.id.password);
        ed6 = (EditText) findViewById(R.id.confirm);
        bt1 = (Button) findViewById(R.id.reg);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = ed1.getText().toString();
                s2 = ed2.getText().toString();
                s3 = ed3.getText().toString();
                s4 = ed4.getText().toString();
                s5 = ed5.getText().toString();
                s6 = ed6.getText().toString();

                    if (s1.matches("") || s2.matches("") || s3.matches("") || s4.matches("") || s5.matches("") || s6.matches("")) {
                        Toast.makeText(RegisterUser.this, "One or more fields missing", Toast.LENGTH_SHORT).show();
                    } else if (!s5.matches(s6)) {
                        Toast.makeText(RegisterUser.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                    else

                    {

                        eref.child("users").child(s1).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Boolean flag = true;
                                if (dataSnapshot.getValue(User.class) == null) {
                                    User e = new User(s1, s2, s3, s4, s5, s6);
                                    flag = false;
                                    eref.child("users").child(s1).setValue(e, new Firebase.CompletionListener() {
                                        @Override
                                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                            if (firebaseError != null) {
                                                Toast.makeText(RegisterUser.this, "Data could not be saved. " + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(RegisterUser.this, "Data saved successfully.", Toast.LENGTH_LONG).show();
                                                finish();
                                            }
                                        }
                                    });

                                } else if (flag)
                                    Toast.makeText(RegisterUser.this, "Email Already exists" , Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });



                    }



                    }


        });


    }
}

