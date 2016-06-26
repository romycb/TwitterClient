package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    private TweetModel model = TweetModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TweetAdapter adapterTweet = new TweetAdapter(this, R.layout.tweet_list_item, model.getTweets());
        adapterTweet.clear();

        OAuthUserTask userTask = new OAuthUserTask();
        userTask.execute();
        User user = model.getUser();

        while (user == null) {
            try {
                Thread.sleep(1000);
                user = model.getUser();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        OAuthUserTimelineTask userTimelineTask = new OAuthUserTimelineTask(adapterTweet);
        userTimelineTask.execute();


        TextView name = (TextView) findViewById(R.id.profile_name);
        TextView screen_name = (TextView) findViewById(R.id.profile_screen_name);
        TextView description = (TextView) findViewById(R.id.profile_description);
        ImageView profile_banner = (ImageView) findViewById(R.id.profile_banner);
        ImageView profile_image = (ImageView) findViewById(R.id.profile_image);
        ListView lv_user_statuses = (ListView) findViewById(R.id.profile_statuses);
        TextView followers_count = (TextView) findViewById(R.id.profile_followers);
        TextView friends_count = (TextView) findViewById(R.id.profile_following);
        TextView statuses_count = (TextView) findViewById(R.id.profile_amount_tweets);

        if (!user.getProfile_image_url().isEmpty()) {
            Picasso.with(this).load(user.getProfile_image_url()).fit().into(profile_image);
        }
        if (!user.getProfile_banner_url().isEmpty()) {
            Picasso.with(this).load(user.getProfile_banner_url()).fit().into(profile_banner);
        }


        lv_user_statuses.setAdapter(adapterTweet);
        name.setText(user.getName());
        screen_name.setText("@" + user.getScreen_name());
        description.setText(user.getDescription());
        followers_count.setText(user.getFollowers_count() + " followers");
        friends_count.setText(user.getFriends_count() + " following");
        statuses_count.setText(user.getStatuses_count() + " tweets");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
