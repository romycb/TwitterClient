package com.example.ap.twitterclient.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.Authorization;
import com.example.ap.twitterclient.communication.SearchTask;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.view.TweetAdapter;


public class SearchActivity extends AppCompatActivity {
    private SearchTask searchTask;
    private EditText searchField;
    private Button searchButton;
    private TweetAdapter adapterTweet;
    private TweetModel model = TweetModel.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView lv = (ListView) findViewById(R.id.listView);
        adapterTweet = new TweetAdapter(this, R.layout.tweet_list_item, model.getTweets());


        Authorization authorization = new Authorization();
        authorization.execute();

        while (model.getAccess_string() == null) {
            Log.d("access code unavailable", "onCreate: ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lv.setAdapter(adapterTweet);


        searchField = (EditText) findViewById(R.id.searchField);
        searchButton = (Button) findViewById(R.id.searchbutton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTask = new SearchTask(adapterTweet);
                searchTask.execute(searchField.getText().toString());
                searchField.setText("");
                model.clearTweetList();
            }
        });


    }


}
