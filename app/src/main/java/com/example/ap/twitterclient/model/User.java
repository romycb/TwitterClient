package com.example.ap.twitterclient.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by romybeugeling on 29-04-16.
 */
public class User {

//    private int id;

    private String id_str;
    private String name;
    private String screen_name;
    private String description;
    private String profile_image_url;


    /**
     *
     * @param object
     */
    public User(JSONObject object) {
        try {
            id_str = object.getString("id_str");
            name = object.getString("name");
            screen_name = object.getString("screen_name");
            description = object.getString("description");
            profile_image_url = object.getString("profile_image_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public String getId_str() {
        return id_str;
    }

    public String getDescription() {
        return description;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getName() {
        return name;
    }


}