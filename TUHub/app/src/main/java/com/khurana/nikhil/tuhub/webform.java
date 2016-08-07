package com.khurana.nikhil.tuhub;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.view.View;
import android.view.ViewManager;
import android.webkit.WebViewClient;
import android.widget.TextView;


public class webform extends Activity {

    WebView wv;
    TextView tv;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webform);
        Intent in=getIntent();
        url=in.getStringExtra("url");

        wv=(WebView)findViewById(R.id.webview);
        wv.setWebViewClient(new MyBrowser());
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        tv=(TextView)findViewById(R.id.textView2);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());
        Boolean isInternetPresent = cd.isConnectingToInternet(); // true or false

        if(isInternetPresent) {
            tv.setText("");

            wv.loadUrl(url);
        }else
        {
            tv.setText("No Internet Connection");
        }

    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
