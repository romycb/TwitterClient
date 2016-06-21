package com.example.ap.twitterclient.communication;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

/**
 * Created by romybeugeling on 06-06-16.
 */
public class ServiceTask extends AsyncTask<String, Void, String> {

    private OAuth1RequestToken reqToken;
    private TweetModel model = TweetModel.getInstance();
//    private TwitterAPI api = TwitterAPI.getInstance();
//
//
//    private OAuth10aService authService = new ServiceBuilder().apiKey(model.getApiKey())
//            .apiSecret(model.getApiSecret()).callback(model.getCallbackUrl()).build(TwitterAPI.getInstance());

    private String authurl;
//    private OAuth1AccessToken accessToken;
//    private OAuthRequest request;

    private TwitterAPI api = TwitterAPI.getInstance();
    private OAuth10aService authService = model.getAuthService();


    @Override
    protected String doInBackground(String... params) {

        reqToken = authService.getRequestToken();
        api.setReqToken(reqToken);
        Log.d("reqtoken", "doInBackground: " + reqToken);
        authurl = authService.getAuthorizationUrl(reqToken);
        api.setAuthurl(authurl);
        Log.d("authurl", "doInBackground:" + authurl);


//        accessToken = authService.getAccessToken(reqToken, api.getVerifier());
//        request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json", authService);
//
//        authService.signRequest(accessToken, request);
//        final Response response = request.send();
//        System.out.println(response.getBody());

        api.setUrl(authurl);

        return null;
    }

//    @Override
//    protected void onPostExecute(String s) {
//        api.setUrl("https://" + s);
//
//    }
//    @Override
//    protected void onPostExecute(String s) {
//        TwitterAPI api = TwitterAPI.getInstance();
//        api.getAuthorizationUrl(reqToken);
//
//        super.onPostExecute(s);
//    }
}
