package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;

import com.github.scribejava.core.model.OAuth1RequestToken;

import com.github.scribejava.core.oauth.OAuth10aService;

/**
 * Created by romybeugeling on 06-06-16.
 */
public class ServiceTask extends AsyncTask<String, Void, String> {

    private TweetModel model = TweetModel.getInstance();
    private TwitterAPI api = TwitterAPI.getInstance();
    private OAuth10aService authService = model.getAuthService();


    @Override
    protected String doInBackground(String... params) {

        OAuth1RequestToken reqToken = authService.getRequestToken();
        api.setReqToken(reqToken);
        String authurl = authService.getAuthorizationUrl(reqToken);

        api.setUrl(authurl);

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
    }
}
