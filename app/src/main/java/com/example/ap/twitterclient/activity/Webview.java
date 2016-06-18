package com.example.ap.twitterclient.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Button;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.ServiceTask;
import com.example.ap.twitterclient.communication.TwitterAPI;
import com.example.ap.twitterclient.communication.TwitterService;

/**
 * Created by romybeugeling on 18-06-16.
 */

public class Webview extends AppCompatActivity {
    private ServiceTask serviceTask;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        webView = (WebView) findViewById(R.id.webview);

        serviceTask = new ServiceTask();
        serviceTask.execute("");

        String URL = "http://www.nu.nl";

        if (webView != null) {
            webView.loadUrl(URL);
        }
    }
}
