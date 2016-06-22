package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.ap.twitterclient.JsonReader;
import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.OAuthRequestService;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.communication.TwitterAPI;
import com.example.ap.twitterclient.communication.UserTimelineTask;
import com.example.ap.twitterclient.view.TweetAdapter;

import com.github.scribejava.core.model.OAuth1RequestToken;

/**
 * Created by romybeugeling and evivanheijningen on 18-06-16.
 */

public class Webview extends AppCompatActivity {
    private TwitterAPI api = TwitterAPI.getInstance();
    private TweetModel model = TweetModel.getInstance();
    private WebView webView;
    private TweetAdapter adapterTweet;
    private OAuthRequestService service;
    private UserTimelineTask task;

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

                while(model.getUser() == null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(Webview.this, Profile.class);
                startActivity(intent);



            } else {
                Log.d("fout", "shouldOverrideUrlLoading: ");
            }
            return false;
        }

    }
}

