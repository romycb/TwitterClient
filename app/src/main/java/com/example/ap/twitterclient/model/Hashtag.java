package com.example.ap.twitterclient.model;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by romybeugeling and Evi on 29-4-2016.
 */
public class Hashtag {

    private int[] indices;

    /**
     * Slaat de gegevens van een hashtag op
     * @param indicesArray de locatie van het begin en eind van de hashtag
     */
    Hashtag(JSONArray indicesArray) {
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