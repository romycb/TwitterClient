package com.example.ap.twitterclient.communication;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Evi, romybeugeling on 23-6-2016.
 */
public class OAuthFriendshipCreate extends AsyncTask<String, Void, String> {
    private TwitterAPI api = TwitterAPI.getInstance();
    private TweetModel model = TweetModel.getInstance();
    private OAuth10aService authService = model.getAuthService();
    private OAuthRequest request;
    private OAuth1AccessToken accessToken = api.getAccess_token();
    private Activity myActivity;

    public OAuthFriendshipCreate(Activity activity) {
        myActivity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        String screen_name = params[0];
        try {
            request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/friendships/create.json?screen_name="
                    + URLEncoder.encode(screen_name, "UTF-8"), authService);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Het tekenen van het request
        authService.signRequest(accessToken, request);
        Response response = request.send();

        if (response.isSuccessful()) {
            return screen_name;
        }

        return null;
    }

    /**
     * Laat Toast bericht zien
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        Toast toast;
        if (s != null) {
            toast = Toast.makeText(myActivity, "Followed " + s, Toast.LENGTH_SHORT);
            toast.show();
        } else{
            toast = Toast.makeText(myActivity, "Error: Cannot unfollow " + s, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
