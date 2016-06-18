package com.example.ap.twitterclient.communication;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by romybeugeling on 31-05-16.
 */
public class Authorization extends AsyncTask<Void, Void, String> {

    private TweetModel tweetModel = TweetModel.getInstance();
    private static final String CHARSET_UTF_8 = "UTF-8";



    @Override
    protected String doInBackground(Void... params) {

        URL url = null;

        try {
            //Prepare request.
            url = new URL("https://api.twitter.com/oauth2/token");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //Encode API key and secret.
            String authString = URLEncoder.encode(tweetModel.getApiKey(), CHARSET_UTF_8) + ":" + URLEncoder.encode(tweetModel.getApiSecret(), CHARSET_UTF_8);

            //Apply Base64 encoding on the encode string.
            String authStringBase64 = Base64.encodeToString(authString.getBytes(CHARSET_UTF_8), Base64.NO_WRAP);

            //Set headers
            conn.setRequestProperty("Authorization", "Basic " + authStringBase64);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            //set body
            conn.setDoOutput(true);
            byte[] body = "grant_type=client_credentials".getBytes("UTF-8");

            conn.setFixedLengthStreamingMode(body.length);
            BufferedOutputStream os = new BufferedOutputStream(conn.getOutputStream());
            os.write(body);
            os.close();

            String response = "";
            // reading in the answer with inputstream reader

            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                InputStream is = conn.getInputStream();
                response = IOUtils.toString(is);
                IOUtils.closeQuietly(is);
            }


            try {
                JSONObject tokenJSon = new JSONObject(response);
                tweetModel.setAccess_string(tokenJSon.getString("access_token"));

            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                if (conn!= null){
                    conn.disconnect();
                }
            }
            Log.d("access_token async", "doInBackground: " + tweetModel.getAccess_string());

            return tweetModel.getAccess_string();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


}

