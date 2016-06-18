package com.example.ap.twitterclient.communication;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1RequestToken;

/**
 * Created by romybeugeling on 06-06-16.
 */
public class TwitterAPI extends DefaultApi10a
{
    private static TwitterAPI ourInstance;

    public static TwitterAPI getInstance() {
        if (ourInstance == null){
            ourInstance = new TwitterAPI();
        }
        return ourInstance;
    }


    private String request_token;
    private String access_token;
    private String authorization_url;

    private TwitterAPI() {
    }

    @Override
    public String getRequestTokenEndpoint() {
        return request_token = "https://api.twitter.com/oauth/request_token";
    }

    @Override
    public String getAccessTokenEndpoint() {
        return access_token = 	"https://api.twitter.com/oauth/access_token";
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return authorization_url = "https://api.twitter.com/oauth/authorize" + requestToken;
    }



}
