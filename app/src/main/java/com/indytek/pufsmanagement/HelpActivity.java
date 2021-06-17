package com.indytek.pufsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        String url = "https://edu-indygroup.odoo.com/movil";
        Uri uri = Uri.parse("googlechrome://navigate?url="+ url);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

        WebView myWebView = findViewById(R.id.webView);
        myWebView.loadUrl("https://edu-indygroup.odoo.com/movil");
    }
}