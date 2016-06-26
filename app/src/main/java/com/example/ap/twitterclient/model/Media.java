package com.example.ap.twitterclient.model;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by romybeugeling on 26-06-16.
 */

public class Media {

    private int[] indices = new int[2];
    private String media_url;

    Media(JSONArray indicesArray, String media_url) {
        try {
            for (int i = 0; i < indicesArray.length(); i++) {
                int index = indicesArray.getInt(i);
                indices[i] = index;
            }

            this.media_url = media_url;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMedia_url() {
        return media_url;
    }

    public int getBeginIndex() {
        return indices[0];
    }

    public int getEndIndex() {
        return indices[1];
    }
}