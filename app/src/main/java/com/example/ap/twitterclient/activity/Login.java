package com.example.ap.twitterclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.communication.ServiceTask;

/**
 * Created by romybeugeling on 18-06-16.
 */

public class Login extends AppCompatActivity {
    private Button testButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        testButton = (Button) findViewById(R.id.buttonTestBrowser);

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Webview.class);
                startActivity(intent);

            }
        });
    }


}

