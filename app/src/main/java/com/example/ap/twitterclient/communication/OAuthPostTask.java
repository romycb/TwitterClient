package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;
import android.util.Log;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Evi on 22-6-2016.
 */
public class OAuthPostTask extends AsyncTask<String, Void, String> {
    private TwitterAPI api = TwitterAPI.getInstance();
    private TweetModel model = TweetModel.getInstance();
    private OAuth10aService authService = model.getAuthService();
    private OAuthRequest request;
    private OAuth1AccessToken accessToken = api.getAccess_token();
    private Response response;

    @Override
    protected String doInBackground(String... params) {

        try {
            request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/statuses/update.json?status=" + URLEncoder.encode(params[0], "UTF-8"), authService);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.d("request", "doInBackground: " + request);

        //Het tekenen van het request
        Log.d("accessToken", "doInBackground: " + accessToken);
        authService.signRequest(accessToken, request);
        response = request.send();
        Log.d("post response", "doInBackground: " + response);


        return null;
    }
}
