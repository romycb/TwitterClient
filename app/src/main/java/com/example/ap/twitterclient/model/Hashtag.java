package com.example.ap.twitterclient.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Evi on 29-4-2016.
 */
public class Hashtag {

    private String text;
    private int[] indices = new int[2];
    private int beginIndex;
    private int endIndex;

    public Hashtag(String text, JSONArray indicesArray) {
        try {
            this.text = text;
            for (int i = 0; i < indicesArray.length(); i++) {
                int index = indicesArray.getInt(i);
                indices[i] = index;
            }
            beginIndex = indices[0];
            endIndex = indices[1];

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getText() {
        return text;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}