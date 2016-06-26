package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ap.twitterclient.JsonReader;
import com.example.ap.twitterclient.model.Tweet;
import com.example.ap.twitterclient.view.TweetAdapter;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by romybeugeling on 24-05-16.
 */
public class SearchTask extends AsyncTask<String, Void, List<Tweet>> {
    private TweetAdapter adapterTweet;
    private List<Tweet> tweets;
    private TweetModel model = TweetModel.getInstance();

    public SearchTask(TweetAdapter adapterTweet) {
        this.adapterTweet = adapterTweet;
    }

    @Override
    protected List<Tweet> doInBackground(String... params) {
        HttpURLConnection conn = null;

        try {

            URL url = new URL("https://api.twitter.com/1.1/search/tweets.json?q=" + URLEncoder.encode(params[0], "UTF-8"));


            conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

            String access_token = model.getAccess_string();
            if (access_token != null) {
                conn.addRequestProperty("Authorization", "Bearer " + access_token);
            }

            conn.setDoInput(true);

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                String response = IOUtils.toString(is, "UTF-8");
                IOUtils.closeQuietly(is);

                JsonReader jsonReader = JsonReader.getInstance();
                tweets = jsonReader.getTweetsFromJsonString(response);

                return tweets;
            } else {

                InputStream is = conn.getErrorStream();
                String response = IOUtils.toString(is, "UTF-8");
                IOUtils.closeQuietly(is);

            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
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
