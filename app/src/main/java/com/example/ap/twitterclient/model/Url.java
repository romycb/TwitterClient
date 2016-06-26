package com.example.ap.twitterclient.model;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Evi and romybeugeling on 29-4-2016.
 */
public class Url {

    private int[] indices;


    Url(JSONArray indicesArray) {
        try {
            indices = new int[2];
            for (int i = 0; i < indicesArray.length(); i++) {
                int index = indicesArray.getInt(i);
                indices[i] = index;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public int getBeginIndex() {
        return indices[0];
    }

    public int getEndIndex() {
        return indices[1];
    }
}

