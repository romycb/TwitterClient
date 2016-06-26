package com.example.ap.twitterclient.communication;

import android.util.Log;

import com.example.ap.twitterclient.model.Tweet;
import com.example.ap.twitterclient.model.User;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evi and romybeugeling on 3-6-2016.
 */
public class TweetModel {

    private static final String API_KEY = "eHc0tKg9NLwyrCHfZrxsFYU58";
    private static final String API_SECRET = "B1QHXI7AC5SUPxZFGE1cFsaf88FvjMsurJ3mx05VYZjmzTco0M";
    private static final String CALLBACK_URL = "https://erjeans.com";
    private String access_string;
    private List<Tweet> tweets = new ArrayList<>();
    private User user;
    private User userShow;

    private OAuth10aService authService = new ServiceBuilder().
            apiKey(getApiKey()).
            apiSecret(getApiSecret()).
            callback(getCallbackUrl()).
            build(TwitterAPI.getInstance());

    private static TweetModel instance;

    private TweetModel() {
    }

    public static TweetModel getInstance() {
        if (instance == null) {
            instance = new TweetModel();
        }
        return instance;
    }

    public OAuth10aService getAuthService() {
        return authService;
    }

    static String getApiKey() {
        return API_KEY;
    }

    static String getApiSecret() {
        return API_SECRET;
    }

    public String getAccess_string() {
        return access_string;
    }

    void setAccess_string(String access_string) {
        this.access_string = access_string;
    }

    void addTweets(Tweet tweet) {
        tweets.add(tweet);
    }

    public List<Tweet> getTweets() {
        Log.d("TweetModel", "getTweets: " + tweets);
        return tweets;
    }

    private static String getCallbackUrl() {
        return CALLBACK_URL;
    }

    void addUser(User u) {
        this.user = u;
    }

    void setUserShow(User u) {
        this.userShow = u;

    }

    public User getUserShow() {
        return userShow;
    }

    public User getUser() {
        return user;
    }
}
