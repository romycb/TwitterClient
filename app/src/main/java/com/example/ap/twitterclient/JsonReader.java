package com.example.ap.twitterclient;

import com.example.ap.twitterclient.model.Tweet;
import com.example.ap.twitterclient.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romybeugeling on 29-4-2016.
 */
public abstract class JsonReader {

    /**
     * Haalt bij het zoeken de tweets uit Json bestand.
     * @param response JSON bestand
     * @return List<Tweet> tweetList;
     */
    public static List<Tweet> getTweetsFromSearch(String response) {
        List<Tweet> tweetList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray tweetArray = jsonObject.getJSONArray("statuses");

            for (int i = 0; i < tweetArray.length(); i++) {
                JSONObject tweetObj = tweetArray.getJSONObject(i);
                Tweet tweet = new Tweet(tweetObj);
                tweetList.add(tweet);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweetList;
    }

    /**
     * Haalt tweets op uit JSON bestand
     * @param response JSON bestand
     * @return List<Tweet> userTweets;
     */
    public static List<Tweet> getStatusesFromJson(String response) {
        List<Tweet> userTweets = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject tweetObj = jsonArray.getJSONObject(i);
                Tweet tweet = new Tweet(tweetObj);
                userTweets.add(tweet);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userTweets;

    }

    /**
     * Haalt een User uit het JSON bestand
     * @param response JSON bestand
     * @return User;
     */
    public static User getUserFromJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            return new User(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
