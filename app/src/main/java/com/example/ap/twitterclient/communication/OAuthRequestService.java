package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;
import android.util.Log;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

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

    @Override
    protected String doInBackground(String... params) {
        accessToken = authService.getAccessToken(api.getReqToken(), api.getVerifier());
        Log.d("accessToken", "authservice " + accessToken);

        token = accessToken.getToken();
        secret = accessToken.getTokenSecret();
        accessToken = new OAuth1AccessToken(token, secret);

        request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json", authService);

        authService.signRequest(accessToken, request);
        response = request.send();
        if (response.isSuccessful()) {
            String res = response.getBody();
            Log.d("response", "authservice " + res);
        }

        return null;
    }
}
