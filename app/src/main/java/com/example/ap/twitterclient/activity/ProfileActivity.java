package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.OAuthUserTask;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.communication.OAuthUserTimelineTask;
import com.example.ap.twitterclient.model.User;
import com.example.ap.twitterclient.view.TweetAdapter;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {


    private TextView name;
    private TextView screen_name;
    private TextView description;
    private ImageView profile_banner;
    private ImageView profile_image;
    private ListView lv_user_statuses;
    private TextView followers_count;
    private TextView friends_count;
    private TextView statuses_count;
    private TweetModel model = TweetModel.getInstance();
    private User user;
    private TweetAdapter adapterTweet;
    private OAuthUserTimelineTask userTimelineTask;
    private OAuthUserTask userTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        adapterTweet = new TweetAdapter(this, R.layout.tweet_list_item, model.getTweets());
        adapterTweet.clear();

        userTask = new OAuthUserTask();
        userTask.execute();
        user = model.getUser();

        while (user == null) {
            try {
                Thread.sleep(1000);
                user = model.getUser();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d("user not empty", "oncreate" + user);
        userTimelineTask = new OAuthUserTimelineTask(adapterTweet);
        userTimelineTask.execute();

        name = (TextView) findViewById(R.id.profile_name);
        screen_name = (TextView) findViewById(R.id.profile_screen_name);
        description = (TextView) findViewById(R.id.profile_description);
        profile_banner = (ImageView) findViewById(R.id.profile_banner);
        profile_image = (ImageView) findViewById(R.id.profile_image);
        lv_user_statuses = (ListView) findViewById(R.id.profile_statuses);
        followers_count = (TextView) findViewById(R.id.profile_followers);
        friends_count = (TextView) findViewById(R.id.profile_following);
        statuses_count = (TextView) findViewById(R.id.profile_amount_tweets);

        if (!user.getProfile_image_url().isEmpty()) {
            Picasso.with(this).load(user.getProfile_image_url()).fit().into(profile_image);
        }
        if (!user.getProfile_banner_url().isEmpty()) {
            Picasso.with(this).load(user.getProfile_banner_url()).fit().into(profile_banner);
        }

        try {
            lv_user_statuses.setAdapter(adapterTweet);
            name.setText(user.getName());
            screen_name.setText("@" + user.getScreen_name());
            description.setText(user.getDescription());
            followers_count.setText(user.getFollowers_count() + " followers");
            friends_count.setText(user.getFriends_count() + " following");
            statuses_count.setText(user.getStatuses_count() + " tweets");
        } catch (NullPointerException npe) {
            npe.getMessage();
            Log.d("profile", "onCreate: ");
        }

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
            case R.id.home_icon_actionbar:
                Intent home = new Intent(this, TimelineActivity.class);
                startActivity(home);
                break;
        }
        return true;
    }
}