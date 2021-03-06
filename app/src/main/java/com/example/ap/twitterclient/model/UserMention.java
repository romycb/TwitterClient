package com.example.ap.twitterclient.model;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by romybeugeling on 20-05-16.
 */
public class UserMention{

    private int[] indices;

    /**
     * Constructor van UserMention
     * @param indicesArray
     */
    UserMention(JSONArray indicesArray) {
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
