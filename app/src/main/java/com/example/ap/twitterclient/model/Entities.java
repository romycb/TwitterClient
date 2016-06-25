package com.example.ap.twitterclient.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romybeugeling on 18-05-16.
 */
public class Entities {
    private List<Hashtag> hashtags;
    private List<UserMention> userMentions;
    private List<Url> urls;

    public Entities(JSONObject object) {

        hashtags = new ArrayList<>();
        userMentions = new ArrayList<>();
        urls = new ArrayList<>();


        //Hashtag ophalen en sturen naar Hashtag klasse
        try {
            JSONArray hashtagArray = object.getJSONArray("hashtags");
            for (int i = 0; i < hashtagArray.length(); i++) {
                JSONObject hashtagObject = hashtagArray.getJSONObject(i);
                Hashtag hashtag = new Hashtag(hashtagObject.getString("text"), hashtagObject.getJSONArray("indices"));
                hashtags.add(hashtag);

            }
        } catch (JSONException e) {
            Log.d("hashtag", "Entities: no hashtags found");
        }

        //URL ophalen en sturen naar URL klasse
        try {
            JSONArray URLArray = object.getJSONArray("urls");
            for (int i = 0; i < URLArray.length(); i++) {
                JSONObject URLObject = URLArray.getJSONObject(i);
                Url url = new Url(URLObject.getString("url"), URLObject.getString("expanded_url"),
                        URLObject.getString("display_url"), URLObject.getJSONArray("indices"));
                urls.add(url);

            }
        } catch (JSONException e) {
            Log.d("JsonException", "Entities: no urls found");
        }

        // user mentions ophalen en naar usermention klasse sturen
        try {
            JSONArray userMentionsArray = object.getJSONArray("user_mentions");
            for (int i = 0; i < userMentionsArray.length(); i++) {
                JSONObject userMentionObject = userMentionsArray.getJSONObject(i);
                UserMention userMention = new UserMention(userMentionObject.getString("screen_name"),
                        userMentionObject.getString("name"), userMentionObject.getInt("id"),
                        userMentionObject.getString("id_str"), userMentionObject.getJSONArray("indices"));
                userMentions.add(userMention);
            }
        } catch (JSONException e){
            Log.d("JsonException", "Entities: no user_mentions found");
        }


    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public List<UserMention> getUserMentions() {
        return userMentions;
    }


}
