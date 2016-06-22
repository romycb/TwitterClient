package com.example.ap.twitterclient.communication;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

/**
 * Created by romybeugeling on 06-06-16.
 */
public class ServiceTask extends AsyncTask<String, Void, String> {

    private OAuth1RequestToken reqToken;
    private TweetModel model = TweetModel.getInstance();
    private String authurl;
    private TwitterAPI api = TwitterAPI.getInstance();
    private OAuth10aService authService = model.getAuthService();


    @Override
    protected String doInBackground(String... params) {

        reqToken = authService.getRequestToken();
        api.setReqToken(reqToken);
        Log.d("reqtoken", "doInBackground: " + reqToken);
        authurl = authService.getAuthorizationUrl(reqToken);
        api.setAuthurl(authurl);
        Log.d("authurl", "doInBackground:" + authurl);


        api.setUrl(authurl);

        return null;
    }

}
