package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;

/**
 * Created by Evi on 22-6-2016.
 */
public class OAuthAccessTask extends AsyncTask<String, Void, String> {
    private TweetModel model = TweetModel.getInstance();
    private OAuth10aService authService = model.getAuthService();
    private TwitterAPI api = TwitterAPI.getInstance();

    @Override
    protected String doInBackground(String... params) {
        OAuth1AccessToken accessToken = authService.getAccessToken(api.getReqToken(), params[0]);
        api.setAccess_token(accessToken);

        return null;
    }
}