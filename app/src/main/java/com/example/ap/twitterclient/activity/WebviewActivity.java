package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.OAuthAccessTask;
import com.example.ap.twitterclient.communication.TwitterAPI;

/**
 * Created by romybeugeling and evivanheijningen on 18-06-16.
 */

public class WebviewActivity extends AppCompatActivity {
    private TwitterAPI api = TwitterAPI.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        WebView webView = (WebView) findViewById(R.id.webview_screen);

        //Laden van de URL
        if (webView != null) {
            webView.loadUrl(api.getUrl());

            //Als er verandering van de URL plaats vindt.
            webView.setWebViewClient(new MýWebviewClient());
        }

    }

    private class MýWebviewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.startsWith("https://erjeans.com")) {
                Uri uri = Uri.parse(url);

                //Het ophalen van de oauth_verifier uit de link.
                String verifier = uri.getQueryParameter("oauth_verifier");

                //Het uitvoeren van de accessTask klasse, het ophalen van het response.
                OAuthAccessTask accessTask = new OAuthAccessTask();
                accessTask.execute(verifier);

                while(api.getAccess_token() == null){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(WebviewActivity.this, ProfileActivity.class);
                startActivity(intent);


            }
            return false;
        }

    }
}

