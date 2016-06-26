package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ap.twitterclient.JsonReader;
import com.example.ap.twitterclient.model.Tweet;
import com.example.ap.twitterclient.view.TweetAdapter;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

import java.util.List;

/**
 * Created by Evi on 22-6-2016.
 */
public class OAuthHomeTimelineTask extends AsyncTask<String, Void, List<Tweet>> {
    private OAuthRequest request;
    private TwitterAPI api = TwitterAPI.getInstance();
    private Response response;
    private List<Tweet> tweets;
    private TweetAdapter adapterTweet;
    private OAuth1AccessToken accessToken = api.getAccess_token();
    private String res;
    private TweetModel model = TweetModel.getInstance();
    private OAuth10aService authService = model.getAuthService();


    public OAuthHomeTimelineTask(TweetAdapter adapterTweet) {
        this.adapterTweet = adapterTweet;
    }

    @Override
    protected List<Tweet> doInBackground(String... params) {
        request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/statuses/home_timeline.json", authService);

        //Het tekenen van het request
        authService.signRequest(accessToken, request);
        response = request.send();

        if (response.isSuccessful()) {
            res = response.getBody();

            JsonReader jsonReader = JsonReader.getInstance();
            tweets = jsonReader.getUserStatusesFromJson(res);

            return tweets;
        }

        return null;

    }

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        if (tweets != null) {
            for (int i = 0; i < tweets.size(); i++) {
                model.addTweets(tweets.get(i));
            }

        } else {

        }
        adapterTweet.notifyDataSetChanged();
    }
}
