package com.example.ap.twitterclient;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.ap.twitterclient.model.Tweet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evi on 29-4-2016.
 */
public class JsonReader {


    /**
     * @return
     */
    public List<Tweet> getTweetsFromJsonString(String response) {
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
            Log.d("assets inlezen", "JsonString:" + e.getMessage());
        }
        for (int i = 0; i <tweetList.size(); i++) {
            Log.d("json tweets", "doInBackground: " + tweetList.get(i));
        }

        return tweetList;
    }

}
