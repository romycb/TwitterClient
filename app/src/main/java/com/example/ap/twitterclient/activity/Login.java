package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.ServiceTask;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.communication.TwitterAPI;

/**
 * Created by romybeugeling on 18-06-16.
 */

public class Login extends AppCompatActivity {
    private Button testButton;
    private ServiceTask serviceTask = new ServiceTask();
    private TwitterAPI api = TwitterAPI.getInstance();
    private TweetModel tweetModel = TweetModel.getInstance();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        testButton = (Button) findViewById(R.id.buttonTestBrowser);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Webview.class);
                startActivity(intent);

            }
        });
    }

}

