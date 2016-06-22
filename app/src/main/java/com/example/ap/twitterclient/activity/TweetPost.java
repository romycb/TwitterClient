package com.example.ap.twitterclient.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.OAuthPostTask;

public class TweetPost extends AppCompatActivity {
    OAuthPostTask postTask;
    private Button post_button;
    private EditText post_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_post);
        post_button = (Button) findViewById(R.id.post);
        post_et = (EditText) findViewById(R.id.post_et);

        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postTask = new OAuthPostTask();

                postTask.execute(post_et.getText().toString());

            }
        });




    }
}
