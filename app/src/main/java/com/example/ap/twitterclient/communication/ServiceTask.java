package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;
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
    private TweetModel model = TweetModel.getInstance();

    private OAuth10aService authService = new ServiceBuilder().apiKey(model.getApiKey())
            .apiSecret(model.getApiSecret()).callback(model.getCallbackUrl()).build(TwitterAPI.getInstance());

    private String authurl ;
    private OAuth1AccessToken accessToken;
    private OAuthRequest request;

    private OAuth1RequestToken reqToken;


    @Override
    protected String doInBackground(String... params) {

        reqToken = authService.getRequestToken();
        authurl = authService.getAuthorizationUrl(reqToken);
        accessToken = authService.getAccessToken(reqToken, "verifier you got from the user/callback");
        request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json", authService);

        final Response response = request.send();

        return authurl;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
