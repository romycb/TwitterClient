package com.example.ap.twitterclient.communication;

import android.util.Log;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

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


    private String request_token;
    private String authurl;
    private OAuth1AccessToken access_token;
    private String authorization_url = "https://api.twitter.com/oauth/authorize?oauth_token=";
    private String verifier;
    private String url;
    private OAuth1RequestToken reqToken;

    private TwitterAPI() {

    }

    @Override
    public String getRequestTokenEndpoint() {
        return request_token = "https://api.twitter.com/oauth/request_token";
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.twitter.com/oauth/access_token";
    }

    @Override
    public String getAuthorizationUrl(OAuth1RequestToken requestToken) {

        return authorization_url + requestToken.getToken();
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public void setUrl(String s) {
        url = s;
    }

    public String getUrl() {
        return url;
    }


    public OAuth1AccessToken getAccess_token() {
        return access_token;
    }

    public void setAccess_token(OAuth1AccessToken access_token) {
        this.access_token = access_token;
    }


    public OAuth1RequestToken getReqToken() {
        return reqToken;
    }

    public void setReqToken(OAuth1RequestToken reqToken) {
        this.reqToken = reqToken;
    }

    public String getAuthurl() {
        return authurl;
    }

    public void setAuthurl(String authurl) {
        this.authurl = authurl;
    }
}
