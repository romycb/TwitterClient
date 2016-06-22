package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.OAuthUserTask;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.communication.OAuthUserTimelineTask;
import com.example.ap.twitterclient.model.Tweet;
import com.example.ap.twitterclient.model.User;
import com.example.ap.twitterclient.view.TweetAdapter;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {


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
    private User user = model.getUser();
    private TweetAdapter adapterTweet;
    private OAuthUserTimelineTask userTimelineTask;
    private OAuthUserTask userTask;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        adapterTweet = new TweetAdapter(this, R.layout.tweet_list_item, model.getTweets());

        userTask = new OAuthUserTask();
        userTask.execute();
        while (model.getUser() == null){

        }
        userTimelineTask = new OAuthUserTimelineTask(adapterTweet);
        userTimelineTask.execute();

        name = (TextView) findViewById(R.id.profile_name);
        screen_name = (TextView) findViewById(R.id.profile_screen_name);
        description = (TextView) findViewById(R.id.profile_description);
        profile_banner = (ImageView) findViewById(R.id.profile_banner);
        profile_image = (ImageView) findViewById(R.id.profile_image);
        lv_user_statuses = (ListView) findViewById(R.id.profile_statuses);
        button = (Button) findViewById(R.id.button_post);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Timeline.class);
                startActivity(intent);
            }
        });
        followers_count = (TextView) findViewById(R.id.profile_followers);
        friends_count = (TextView) findViewById(R.id.profile_following);
        statuses_count = (TextView) findViewById(R.id.profile_amount_tweets);

        Picasso.with(this).load(user.getProfile_image_url()).fit().into(profile_image);
        Picasso.with(this).load(user.getProfile_banner_url()).fit().into(profile_banner);


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
}
