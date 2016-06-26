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
 * Created by Evi on 23-6-2016.
 */
public class OAuthFriendshipDestroy extends AsyncTask<String, Void, String> {
    private TwitterAPI api = TwitterAPI.getInstance();
    private TweetModel model = TweetModel.getInstance();
    private OAuth10aService authService = model.getAuthService();
    private OAuthRequest request;
    private OAuth1AccessToken accessToken = api.getAccess_token();
    private Activity myActivity;
    private String screen_name;
    private static final String UNFOLLOWED= "Unfollowed ";
    private static final String FAILED = "Failed to unfollow ";

    public OAuthFriendshipDestroy(Activity activity) {
        myActivity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        screen_name = params[0];
        try {
            request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/friendships/destroy.json?screen_name="
                    + URLEncoder.encode(screen_name, "UTF-8"), authService);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Het tekenen van het request
        authService.signRequest(accessToken, request);
        Response response = request.send();

        if (response.isSuccessful()){
            return UNFOLLOWED;
        }

        return FAILED;
    }

    /**
     * Laat Toast bericht zien
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        Toast toast;
        if (s.equals(UNFOLLOWED)) {
            toast = Toast.makeText(myActivity, UNFOLLOWED + screen_name, Toast.LENGTH_SHORT);
        } else {
            toast = Toast.makeText(myActivity, FAILED + screen_name, Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
