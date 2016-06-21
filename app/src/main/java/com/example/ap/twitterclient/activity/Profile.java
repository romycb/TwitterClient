package com.example.ap.twitterclient.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.view.TweetAdapter;

import java.util.List;

public class Profile extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



    }
}
