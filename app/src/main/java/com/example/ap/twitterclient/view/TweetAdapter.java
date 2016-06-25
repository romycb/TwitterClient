package com.example.ap.twitterclient.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.activity.ProfileActivity;
import com.example.ap.twitterclient.activity.UserAccountActivity;
import com.example.ap.twitterclient.communication.OAuthUserShowTask;
import com.example.ap.twitterclient.communication.TweetModel;
import com.example.ap.twitterclient.model.Tweet;
import com.example.ap.twitterclient.model.User;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Evi on 12-5-2016.
 */
public class TweetAdapter extends ArrayAdapter<Tweet> {

    private LayoutInflater inflater = null;
    private TweetModel model = TweetModel.getInstance();
    private  OAuthUserShowTask userShowTask;
    private String screen_name;
    private Context context;
    private int position;
    private User user;
    private User user2;



    public TweetAdapter(Context context, int resource, List<Tweet> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tweet_list_item, parent, false);

        }
        this.position = position;
        Tweet currentTweet = getItem(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM", Locale.UK);
        SpannableString tweetText = new SpannableString(currentTweet.getText());

        for (int i = 0; i < currentTweet.getEntities().getHashtags().size(); i++) {
            try {
                tweetText.setSpan(new ForegroundColorSpan(Color.rgb(0, 132, 180)), currentTweet.getEntities().getHashtags().get(i).getBeginIndex(),
                        currentTweet.getEntities().getHashtags().get(i).getEndIndex(),
                        SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
            } catch (IndexOutOfBoundsException ioobe){
                ioobe.printStackTrace();
            }

        }

        for (int i = 0; i < currentTweet.getEntities().getUrls().size(); i++) {
            try {
                tweetText.setSpan(new ForegroundColorSpan(Color.rgb(0, 132, 180)),
                        currentTweet.getEntities().getUrls().get(i).getBeginIndex()
                        , currentTweet.getEntities().getUrls().get(i).getEndIndex(),
                        SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
            } catch (IndexOutOfBoundsException ioobe) {
                ioobe.printStackTrace();
            }
        }

        for (int i = 0; i < currentTweet.getEntities().getUserMentions().size(); i++) {
            try {
                tweetText.setSpan(new ForegroundColorSpan(Color.rgb(0, 132, 180)),
                        currentTweet.getEntities().getUserMentions().get(i).getBeginIndex()
                        , currentTweet.getEntities().getUserMentions().get(i).getEndIndex(),
                        SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
            }catch (IndexOutOfBoundsException ioobe){
                ioobe.printStackTrace();
            }

        }
        user = currentTweet.getUser();
        user2= model.getUser();


        TextView tvText = (TextView) convertView.findViewById(R.id.tweet);
        TextView tvCreatedAt = (TextView) convertView.findViewById(R.id.created_at);
        TextView tvScreenName = (TextView) convertView.findViewById(R.id.screen_name);
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        ImageButton ivprofileImage = (ImageButton) convertView.findViewById(R.id.profile_image_timeline);
        tvScreenName.setText("@" + currentTweet.getUser().getScreen_name());
        tvName.setText(currentTweet.getUser().getName());

        tvCreatedAt.setText(sdf.format(currentTweet.getCreated_at()));
        tvText.setText(tweetText);
        Picasso.with(getContext()).load(user.getProfile_image_url()).into(ivprofileImage);

        ivprofileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                screen_name = model.getTweets().get(position).getUser().getScreen_name();

                userShowTask = new OAuthUserShowTask();
                userShowTask.execute(screen_name);

                Log.d("name", "position" + screen_name);

                if (!screen_name.equals(user2.getScreen_name())){
                    Intent intent = new Intent(context, UserAccountActivity.class);
                    context.startActivity(intent);


                }else {
                    Intent intent2 = new Intent(context, ProfileActivity.class);
                    context.startActivity(intent2);
                }

            }
        });


        return convertView;
    }
}
