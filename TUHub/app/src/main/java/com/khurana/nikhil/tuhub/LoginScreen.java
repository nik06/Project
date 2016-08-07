package com.khurana.nikhil.tuhub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.khurana.nikhil.tuhub.MainActivity;
import com.onesignal.OneSignal;

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


public class LoginScreen extends Activity {
    private String[] s={"Student","Society"};
    Spinner sp1;
    int s1=0;
    int f=0;
    String login,password,mydata,url;
    String email,roll,contact;
    RadioGroup rg;
    SharedPreferences shrd;
    SharedPreferences.Editor ed;
    Button bt1,bt2,bt3;
    EditText ed1,ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        shrd=getSharedPreferences("Details", Context.MODE_PRIVATE);
        if(shrd.contains("name")==true && shrd.contains("type")==true)
        {
            if(shrd.getString("type","").matches("user")){
            Intent in=new Intent(LoginScreen.this,MainActivity.class);
            startActivity(in);
            finish();}
            else
                if(shrd.getString("type","").matches("society"))
                {
                    Intent in=new Intent(LoginScreen.this,LoginSociety.class);
                    startActivity(in);
                    finish();

                }

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        OneSignal.startInit(this).init();

       // sp1=(Spinner)findViewById(R.id.spinner);

        ed1=(EditText)findViewById(R.id.editText);
        bt1=(Button)findViewById(R.id.button);
        bt2=(Button)findViewById(R.id.button2);
        ed2=(EditText)findViewById(R.id.editText2);
        bt3=(Button)findViewById(R.id.button3);
        rg=(RadioGroup)findViewById(R.id.rd);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ed=shrd.edit();
                ed.putString("type","guest");
                ed.commit();
                Intent in=new Intent(LoginScreen.this,MainActivity.class);
                startActivity(in);

            }
        });


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(LoginScreen.this,RegisterUser.class);

                startActivity(in);
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
   /*             final ProgressDialog pg=new ProgressDialog(LoginScreen.this);
                pg.setMessage("Loading..");
                pg.setIndeterminate(false);
                pg.setCancelable(false);
                pg.show();*/
                login=ed1.getText().toString();
                password=ed2.getText().toString();

                ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
                Boolean isInternetPresent = cd.isConnectingToInternet();
                if(isInternetPresent)
                {

                    if (!login.matches("") && !password.matches("")) {

                        if (s1 == 1) {
                            url = "https://blazing-fire-3495.firebaseio.com/society";
                        } else
                            url = "https://blazing-fire-3495.firebaseio.com/users";


                        ed = shrd.edit();
                        Firebase eref = new Firebase(url);

                        eref.child(login).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        if (s1 == 1) {
                                            Society s = dataSnapshot.getValue(Society.class);
                                            if (password.matches(s.getPassword())) {
                                                Intent in = new Intent(LoginScreen.this, LoginSociety.class);
                                                ed.putString("type", "society");
                                                ed.putString("name", s.getName());
                                                ed.commit();
                                                //    pg.dismiss();
                                                startActivity(in);
                                                finish();
                                            } else

                                                Toast.makeText(LoginScreen.this, "Password Does Not Match", Toast.LENGTH_LONG).show();

                                        } else {
                                            User e = dataSnapshot.getValue(User.class);
                                            if (password.matches(e.getPassword())) {
                                                Intent in = new Intent(LoginScreen.this, MainActivity.class);
                                                ed.putString("type", "user");
                                                ed.putString("name", e.getUsername());
                                                ed.putString("email", e.getEmail());
                                                ed.putString("roll", e.getRoll());
                                                ed.putString("contact", e.getContact());
                                                ed.commit();
                                                //  pg.dismiss();
                                                startActivity(in);
                                                finish();
                                            } else {
                                                Toast.makeText(LoginScreen.this, "Password Does Not Match", Toast.LENGTH_LONG).show();
                                            }
                                        }


                                    } else
                                        Toast.makeText(LoginScreen.this, "Username does not exist", Toast.LENGTH_LONG).show();
                                }
                                    @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                    Toast.makeText(LoginScreen.this, firebaseError.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });

                    }
                    else
                    {
                        Toast.makeText(LoginScreen.this,"One or more fields missing",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(LoginScreen.this,"No Network Connection",Toast.LENGTH_SHORT).show();

                }



            }
        });

    }
    public void abcd(View v)
    {

        String to = "khurananikhil30@gmail.com";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Request for society login ID password");

        startActivity(intent);

    }
    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.student:
                if (checked)

                        s1=0;
                    break;
            case R.id.soc:
                if (checked)

                        s1=1;
                    break;
        }
    }
}
