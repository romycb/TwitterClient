package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.OAuthHomeTimeline;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.view.TweetAdapter;

public class Timeline extends AppCompatActivity {
    private TweetAdapter adapterTweet;
    private TweetModel model = TweetModel.getInstance();
    private OAuthHomeTimeline homeTimelineTask;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        adapterTweet = new TweetAdapter(this, R.layout.tweet_list_item, model.getTweets());
        homeTimelineTask = new OAuthHomeTimeline(adapterTweet);
        homeTimelineTask.execute();

        lv = (ListView) findViewById(R.id.listViewHomeTimeline);

        lv.setAdapter(adapterTweet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.search_button_actionbar:
                Intent intent = new Intent(this, Search.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}
