package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.OAuthPostTask;
import com.example.ap.twitterclient.view.TweetAdapter;

public class TweetPostActivity extends AppCompatActivity {
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
                Intent profile = new Intent(TweetPostActivity.this, ProfileActivity.class);
                startActivity(profile);
            }
        });


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
            case R.id.profile_icon_actionbar:
                Intent profile = new Intent(this, ProfileActivity.class);
                startActivity(profile);
                break;
            case R.id.home_icon_actionbar:
                Intent home = new Intent(this, TimelineActivity.class);
                startActivity(home);
                break;
        }
        return true;
    }
}
