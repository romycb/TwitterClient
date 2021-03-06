package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.ServiceTask;

/**
 * Created by romybeugeling on 18-06-16.
 */

public class LoginActivity extends AppCompatActivity {
    private Button authorizeBtn;
    private ImageView twitterLogo;
    private ServiceTask serviceTask = new ServiceTask();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        twitterLogo = (ImageView) findViewById(R.id.twitter_logo);
        twitterLogo.setImageResource(R.drawable.twitter_logo_correct);

        serviceTask.execute();
        authorizeBtn = (Button) findViewById(R.id.authorize_btn);


        authorizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, WebviewActivity.class);
                startActivity(intent);
            }
        });
    }

}


