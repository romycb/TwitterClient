package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;

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
 * Created by romybeugeling on 22-06-16.
 */

public class OAuthUserTimelineTask extends AsyncTask<String, Void, List<Tweet>> {
    private TwitterAPI api = TwitterAPI.getInstance();
    private TweetAdapter adapterTweet;
    private OAuth1AccessToken accessToken = api.getAccess_token();
    private TweetModel model = TweetModel.getInstance();
    private OAuth10aService authService = model.getAuthService();


    public OAuthUserTimelineTask(TweetAdapter adapterTweet) {
        this.adapterTweet = adapterTweet;
    }

    @Override
    protected List<Tweet> doInBackground(String... params) {

        String id_Str = "";
        if (params.length == 1) id_Str = "?user_id=" + params[0];

        OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.twitter.com/1.1/statuses/user_timeline.json" + id_Str, authService);

        //Het tekenen van het request
        authService.signRequest(accessToken, request);
        Response response = request.send();

        if (response.isSuccessful()) {
            String res = response.getBody();

            return JsonReader.getStatusesFromJson(res);
        }

        return null;

    }

    @Override
    protected void onPostExecute(List<Tweet> tweets) {
        if (tweets != null) {
            for (int i = 0; i < tweets.size(); i++) {
                model.addTweets(tweets.get(i));
            }
        }

        adapterTweet.notifyDataSetChanged();
    }
}
