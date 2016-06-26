package com.example.ap.twitterclient.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by romybeugeling on 29-04-16.
 */
public class User {

    private String id_str;
    private String name;
    private String screen_name;
    private String description;
    private int followers_count;
    private int friends_count;
    private int statuses_count;
    private String profile_image_url;
    private String profile_banner_url;
    private User user;
    private Boolean following;

    private User(String id_str, String name, String screen_name, String description, int followers_count,
                 int friends_count, int statuses_count, String profile_image_url, String profile_banner_url, Boolean following) {
        this.id_str = id_str;
        this.name = name;
        this.screen_name = screen_name;
        this.description = description;
        this.followers_count = followers_count;
        this.friends_count = friends_count;
        this.statuses_count = statuses_count;
        this.profile_image_url = profile_image_url;
        this.profile_banner_url = profile_banner_url;
        this.following = following;
    }

    /**
     * Haalt User op uit het JSONobject en slaat nieuwe User op
     * @param object JSONobject
     */
    public User(JSONObject object) {
        try {
            id_str = object.getString("id_str");
            name = object.getString("name");
            screen_name = object.getString("screen_name");
            description = object.getString("description");
            followers_count = object.getInt("followers_count");
            friends_count = object.getInt("friends_count");
            statuses_count = object.getInt("statuses_count");
            profile_banner_url = object.optString("profile_banner_url");
            profile_image_url = object.optString("profile_image_url");
            following = object.optBoolean("following");

            user = new User(id_str, name, screen_name, description, followers_count,
                    friends_count, statuses_count, profile_image_url, profile_banner_url, following);
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

    public Boolean getFollowing() {
        return following;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getName() {
        return name;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public String getProfile_banner_url() {
        return profile_banner_url;
    }

    public User getUser() {
        return user;
    }

}