package com.example.ap.twitterclient.communication;

import android.util.Log;

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
public class TwitterService {
    private static TwitterService ourInstance;

    public static TwitterService getInstance() {
        if (ourInstance == null){
            ourInstance = new TwitterService();
        }
        return ourInstance;
    }

    private TweetModel model = TweetModel.getInstance();

    private OAuth10aService authService = new ServiceBuilder().apiKey(model.getApiKey())
            .apiSecret(model.getApiSecret()).callback(model.getCallbackUrl()).build(TwitterAPI.getInstance());

    private OAuth1RequestToken reqToken = authService.getRequestToken();

    private String authurl = authService.getAuthorizationUrl(reqToken);
    private final OAuth1AccessToken accessToken = authService.getAccessToken(reqToken, "verifier you got from the user/callback");
    private final OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/account/verify_credentials.json",
            authService);

     // the access token from step 4

    final Response response = request.send();


    private TwitterService() {
        ServiceTask serviceTask = new ServiceTask();
        serviceTask.execute(authurl);
//        authService.signRequest(accessToken, request);

//        String authorize = "https://api.twitter.com/oauth/authorize?oauth_token=" + request;


    }



    public TweetModel getModel() {
        return model;
    }

    public OAuth10aService getAuthService() {
        return authService;
    }

    public OAuth1RequestToken getReqToken() {
        return reqToken;
    }

    public String getAuthurl() {
        return authurl;
    }

    public OAuth1AccessToken getAccessToken() {
        return accessToken;
    }

    public OAuthRequest getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }
}
