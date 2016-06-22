package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ap.twitterclient.JsonReader;
import com.example.ap.twitterclient.model.Tweet;
import com.example.ap.twitterclient.model.User;
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
    private OAuth1AccessToken accessToken = api.getAccess_token();
    private Response response;
    private String res;
    private boolean b;

    @Override
    protected String doInBackground(String... params) {


        request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json", authService);
        Log.d("request", "doInBackground: " + request);

        //Het tekenen van het request
        Log.d("accessToken", "doInBackground: " + accessToken);
        authService.signRequest(accessToken, request);
        response = request.send();


        //Het ophalen van de json file.
        if (response.isSuccessful()) {
            res = response.getBody();

            Log.d("response", "authservice " + res);

            JsonReader jsonReader = JsonReader.getInstance();
            User user = jsonReader.getUserFromJson(res);
            model.addUser(user);
            Log.d("user", "doInBackground: " + user);

            return res;
        }



        return null;
    }

}
