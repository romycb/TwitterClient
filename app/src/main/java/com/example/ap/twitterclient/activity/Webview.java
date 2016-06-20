package com.example.ap.twitterclient.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.ServiceTask;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.communication.TwitterAPI;

/**
 * Created by romybeugeling on 18-06-16.
 */

public class Webview extends AppCompatActivity {
    private ServiceTask serviceTask;
    private TwitterAPI api = TwitterAPI.getInstance();
    private WebView webView;
    private TweetModel tweetModel = TweetModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        webView = (WebView) findViewById(R.id.web);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl("nu.nl");
                if (url.startsWith("http//www.erjeans.com")) {
                    Uri uri = Uri.parse(url);
                    api.setVerifier(uri.getQueryParameter("oauth_verifier"));
                    serviceTask.execute();
                    Log.d("url", "shouldOverrideUrlLoading: " + api.getUrl());
//                    webView.loadUrl(api.getUrl());

                    // Authorization granted...
                } else {
                    Log.d("fout", "shouldOverrideUrlLoading: ");
                }
                return false;
            }
        });


//
//        serviceTask = new ServiceTask();
//        serviceTask.execute("");
//
//        String URL = api.getRequestTokenEndpoint();
//        if (webView != null) {
//            webView.loadUrl(URL);
//        }
    }
}
