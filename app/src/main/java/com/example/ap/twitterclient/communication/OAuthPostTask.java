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
 * Created by Evi and romybeugeling on 22-6-2016.
 */
public class OAuthPostTask extends AsyncTask<String, Void, String> {
    private TwitterAPI api = TwitterAPI.getInstance();
    private TweetModel model = TweetModel.getInstance();
    private OAuth10aService authService = model.getAuthService();
    private OAuthRequest request;
    private OAuth1AccessToken accessToken = api.getAccess_token();
    private Activity myActivity;
    private static final String RESPONSE_SUCCESFUL = "Succesfully posted tweet";
    private static final String RESPONSE_403_ERROR = "Failed: 403 Error, cannot post the same tweet twice";
    private static final String RESPONSE_FAILED = "Failed";

    public OAuthPostTask(Activity activity) {
        this.myActivity = activity;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            request = new OAuthRequest(Verb.POST, "https://api.twitter.com/1.1/statuses/update.json?status=" + URLEncoder.encode(params[0], "UTF-8"), authService);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Het tekenen van het request
        authService.signRequest(accessToken, request);
        Response response = request.send();

        if (response.isSuccessful()){
            return RESPONSE_SUCCESFUL;
        } else if (response.getCode() == 403){
            return RESPONSE_403_ERROR;
        }

        return RESPONSE_FAILED;
    }

    @Override
    protected void onPostExecute(String s) {
        Toast toast;
        switch (s) {
            case RESPONSE_SUCCESFUL:
                toast = Toast.makeText(myActivity, RESPONSE_SUCCESFUL, Toast.LENGTH_SHORT);
                break;
            case RESPONSE_403_ERROR:
                toast = Toast.makeText(myActivity, RESPONSE_403_ERROR, Toast.LENGTH_SHORT);
                break;
            default:
                toast = Toast.makeText(myActivity, RESPONSE_FAILED, Toast.LENGTH_SHORT);
                break;
        }
        toast.show();
    }
}
