package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.OAuthHomeTimelineTask;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.view.TweetAdapter;

public class TimelineActivity extends AppCompatActivity {
    private TweetAdapter adapterTweet;
    private TweetModel model = TweetModel.getInstance();
    private OAuthHomeTimelineTask homeTimelineTask;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        adapterTweet = new TweetAdapter(this, R.layout.tweet_list_item, model.getTweets());
        homeTimelineTask = new OAuthHomeTimelineTask(adapterTweet);
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
                Intent search = new Intent(this, SearchActivity.class);
                startActivity(search);
                break;
            case R.id.post_tweet_actionbar:
                Intent postTweet = new Intent(this, TweetPostActivity.class);
                startActivity(postTweet);
                break;
            case R.id.profile_icon_actionbar:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
                break;
        }
        return true;
    }
}
