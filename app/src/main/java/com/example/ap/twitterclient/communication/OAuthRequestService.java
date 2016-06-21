package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ap.twitterclient.JsonReader;
import com.example.ap.twitterclient.model.Tweet;
import com.example.ap.twitterclient.view.TweetAdapter;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evi on 20-6-2016.
 */
public class OAuthRequestService extends AsyncTask<String, Void, String> {
    private TwitterAPI api = TwitterAPI.getInstance();
    private TweetModel model = TweetModel.getInstance();
    private OAuth10aService authService = model.getAuthService();
    private OAuthRequest request;
    private String token;
    private String secret;
    private OAuth1AccessToken accessToken;
    private Response response;
    private String res;

    @Override
    protected String doInBackground(String... params) {

        //De accestoken ophalen.
        accessToken = authService.getAccessToken(api.getReqToken(), api.getVerifier());
        Log.d("accessToken", "authservice " + accessToken);

        token = accessToken.getToken();
        secret = accessToken.getTokenSecret();
        accessToken = new OAuth1AccessToken(token, secret);

        request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json", authService);

        //Het tekenenen van het request
        authService.signRequest(accessToken, request);
        response = request.send();

        //Het ophalen van de json file.
        if (response.isSuccessful()) {
            res = response.getBody();
            Log.d("response", "authservice " + res);
        }



        return null;
    }

}
