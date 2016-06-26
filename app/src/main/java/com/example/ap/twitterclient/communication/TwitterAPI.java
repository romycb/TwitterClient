package com.example.ap.twitterclient.communication;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;

/**
 * Created by romybeugeling on 06-06-16.
 */
public class TwitterAPI extends DefaultApi10a {
    private static TwitterAPI ourInstance;

    public static TwitterAPI getInstance() {
        if (ourInstance == null) {
            ourInstance = new TwitterAPI();
        }
        return ourInstance;
    }

    private OAuth1AccessToken access_token;
    private String url;
    private OAuth1RequestToken reqToken;

    private TwitterAPI() {
    }

    @Override
    public String getRequestTokenEndpoint() {
        return "https://api.twitter.com/oauth/request_token";
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.twitter.com/oauth/access_token";
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {
        return "https://api.twitter.com/oauth/authorize?oauth_token=" + requestToken.getToken();
    }

    void setUrl(String s) {
        url = s;
    }

    public String getUrl() {
        return url;
    }

    public OAuth1AccessToken getAccess_token() {
        return access_token;
    }

    void setAccess_token(OAuth1AccessToken access_token) {
        this.access_token = access_token;
    }

    OAuth1RequestToken getReqToken() {
        return reqToken;
    }

    void setReqToken(OAuth1RequestToken reqToken) {
        this.reqToken = reqToken;
    }
}
