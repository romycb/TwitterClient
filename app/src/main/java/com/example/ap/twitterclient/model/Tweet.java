package com.example.ap.twitterclient.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Evi on 29-4-2016.
 */
public class Tweet {
    private String text;
    private String id_str;
    private Date created_at;
    private User user;
    private Entities entities;

    /**
     * @param object
     */
    public Tweet(JSONObject object) {
        try {
            text = object.getString("text");
            id_str = object.getString("id_str");
            SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
            created_at = df.parse(object.getString("created_at"));
            user = new User(object.getJSONObject("user"));
            entities = new Entities(object.getJSONObject("entities"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public String getId_str() {
        return id_str;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Entities getEntities() {
        return entities;
    }
}
