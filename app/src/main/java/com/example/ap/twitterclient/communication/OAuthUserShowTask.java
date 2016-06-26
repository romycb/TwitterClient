package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ap.twitterclient.JsonReader;
import com.example.ap.twitterclient.model.User;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Evi on 23-6-2016.
 */
public class OAuthUserShowTask extends AsyncTask<String, Void, String> {
    private TwitterAPI api = TwitterAPI.getInstance();
    private TweetModel model = TweetModel.getInstance();
    private OAuth10aService authService = model.getAuthService();
    private OAuthRequest request;
    private OAuth1AccessToken accessToken = api.getAccess_token();
    private Response response;
    private String res;

    @Override
    protected String doInBackground(String... params) {


        try {
            request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/users/show.json?screen_name=" + URLEncoder.encode(params[0], "UTF-8"), authService);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        //Het tekenen van het request
        authService.signRequest(accessToken, request);
        response = request.send();




        //Het ophalen van de json file.
        if (response.isSuccessful()) {
            res = response.getBody();

            JsonReader jsonUser = JsonReader.getInstance();
            User user = jsonUser.getUserFromJson(res);
            model.setUserShow(user);

            return res;
        }


        return null;
    }

}