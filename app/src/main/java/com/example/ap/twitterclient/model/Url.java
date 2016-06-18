package com.example.ap.twitterclient.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Evi on 29-4-2016.
 */
public class Url {

    private String url;
    private String expanded_url;
    private String display_url;
    private int[] indices;
    private int beginIndex;
    private int endIndex;

    public Url(String url, String expanded_url, String display_url, JSONArray indicesArray) {
        try {
            indices = new int[2];
            this.url = url;
            this.expanded_url = expanded_url;
            this.display_url = display_url;
            for (int i = 0; i < indicesArray.length(); i++) {
                int index = indicesArray.getInt(i);
                indices[i] = index;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String getDisplay_url() {
        return display_url;
    }


    public int getBeginIndex() {
        return this.beginIndex = indices[0];
    }

    public int getEndIndex() {
        return this.endIndex = indices[1];
    }
}

