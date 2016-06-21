package com.example.ap.twitterclient.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.model.User;

public class Profile extends AppCompatActivity {

    private TextView name;
    private TextView screen_name;
    private TextView description;
    private ImageView profile_banner;
    private ImageView profile_image;
    private int followers_count;
    private int friends_count;
    private int statuses_count;
    private User user = TweetModel.getInstance().getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = (TextView) findViewById(R.id.profile_name);
        screen_name = (TextView) findViewById(R.id.screen_name);
        description = (TextView) findViewById(R.id.profile_description);
        profile_banner = (ImageView) findViewById(R.id.profile_banner);
        profile_image = (ImageView) findViewById(R.id.profile_image);

        try {
            name.setText(user.getName());
            screen_name.setText(user.getScreen_name());
            description.setText(user.getDescription());
        } catch (NullPointerException npe) {
            npe.getMessage();
            Log.d("profile", "onCreate: " );
        }

    }
}
