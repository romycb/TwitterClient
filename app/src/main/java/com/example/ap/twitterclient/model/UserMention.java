package com.example.ap.twitterclient.model;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by romybeugeling on 20-05-16.
 */
public class UserMention {

    private String screen_name;
    private String name;
    private int id;
    private String id_str;
    private int[] indices = new int[2];
    private int beginIndex;
    private int endIndex;

    public UserMention(String screen_name, String name, int id, String id_str, JSONArray indicesArray) {
        try {

            this.screen_name = screen_name;
            this.name = name;
            this.id = id;
            this.id_str = id_str;
            for (int i = 0; i < indicesArray.length(); i++) {
                int index = 0;
                index = indicesArray.getInt(i);
                indices[i] = index;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getScreen_name() {
        return screen_name;
    }

    public int getBeginIndex() {
        return this.beginIndex = indices[0];
    }

    public int getEndIndex() {
        return this.endIndex = indices[1];
    }
}
