package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;

import com.example.ap.twitterclient.JsonReader;
import com.example.ap.twitterclient.model.User;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;


/**
 * Created by Evi and romybeugeling on 20-6-2016.
 */
public class OAuthUserTask extends AsyncTask<String, Void, String> {
    private TwitterAPI api = TwitterAPI.getInstance();
    private TweetModel model = TweetModel.getInstance();
    private OAuth10aService authService = model.getAuthService();
    private OAuth1AccessToken accessToken = api.getAccess_token();

    @Override
    protected String doInBackground(String... params) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json", authService);

        //Het tekenen van het request
        authService.signRequest(accessToken, request);
        Response response = request.send();

        //Het ophalen van de json file.
        if (response.isSuccessful()) {
            String res = response.getBody();
            User user = JsonReader.getUserFromJson(res);
            model.addUser(user);
        }

        return null;
    }

}
