package com.example.ap.twitterclient.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.OAuthRequestService;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.communication.TwitterAPI;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

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

        Log.d("webview url", "oncreate" + api.getUrl());
        webView = (WebView) findViewById(R.id.webview_screen);
        webView.loadUrl(api.getUrl());
        webView.setWebViewClient(new MýWebviewClient());
    }

    private class MýWebviewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.startsWith("https://erjeans.com")) {
                Log.d("url", "shouldOverrideUrlLoading:" + url);
                Uri uri = Uri.parse(url);
                api.setVerifier(uri.getQueryParameter("oauth_verifier"));

                Log.d("verifier", "shouldOverrideUrlLoading:" + api.getVerifier());
                Log.d("accessToken", "authservice " + api.getReqToken().toString() + api.getVerifier());

                service = new OAuthRequestService();
                service.execute();

            } else {
                Log.d("fout", "shouldOverrideUrlLoading: ");
            }
            return false;
        }
    }

}

