package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;


import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.OAuthRequestService;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.communication.TwitterAPI;
import com.example.ap.twitterclient.view.TweetAdapter;


/**
 * Created by romybeugeling and evivanheijningen on 18-06-16.
 */

public class Webview extends AppCompatActivity {
    private TwitterAPI api = TwitterAPI.getInstance();
    private WebView webView;
    OAuthRequestService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);


        webView = (WebView) findViewById(R.id.webview_screen);
        //Laden van de URL.
        webView.loadUrl(api.getUrl());
        //Als er verandering van de URL plaats vindt.
        webView.setWebViewClient(new MýWebviewClient());
    }

    private class MýWebviewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.startsWith("https://erjeans.com")) {
                Log.d("url", "shouldOverrideUrlLoading:" + url);
                Uri uri = Uri.parse(url);
                //Het ophalen van de oauth_verifier uit de link.
                api.setVerifier(uri.getQueryParameter("oauth_verifier"));

                //Het uitvoeren van de service klasse, het ophalen van het response.
                service = new OAuthRequestService();
                service.execute();

            } else {
                Log.d("fout", "shouldOverrideUrlLoading: ");
            }
            return false;
        }
    }

}

