package com.example.ap.twitterclient.view;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.ap.twitterclient.R;
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

    public TweetAdapter(Context context, int resource, List<Tweet> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tweet_list_item, parent, false);
        }
        Tweet currentTweet = getItem(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM", Locale.UK);
        SpannableString tweetText = new SpannableString(currentTweet.getText());

        for (int i = 0; i < currentTweet.getEntities().getHashtags().size(); i++) {
            tweetText.setSpan(new ForegroundColorSpan(Color.rgb(0, 132, 180)), currentTweet.getEntities().getHashtags().get(i).getBeginIndex(),
                    currentTweet.getEntities().getHashtags().get(i).getEndIndex(),
                    SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);

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
        User user = currentTweet.getUser();

        TextView tvText = (TextView) convertView.findViewById(R.id.tweet);
        TextView tvCreatedAt = (TextView) convertView.findViewById(R.id.created_at);
        TextView tvScreenName = (TextView) convertView.findViewById(R.id.screen_name);
        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        ImageView ivprofileImage = (ImageView) convertView.findViewById(R.id.profile_image);
        tvScreenName.setText("@" + currentTweet.getUser().getScreen_name());
        tvName.setText(currentTweet.getUser().getName());



        tvCreatedAt.setText(sdf.format(currentTweet.getCreated_at()));
        tvText.setText(tweetText);
//        Picasso.with(getContext()).load(user.getProfile_image_url()).into(ivprofileImage);

        // TODO: media ophalen uit tweet en laten zien (plaatjes etc.)

//        Entities entity = currentTweet.getEntities();
//        String media = entity.getMedia().get(position).getDisplay_url();
//        testImage.set

//        if (currentTweet.getEntities().)
//        String media = currentTweet.getEntities().getMedia().get(position).getDisplay_url();
//        if (currentTweet.getEntities().getMedia().size() > 0){
//            media = currentTweet.getEntities().getMedia().get(position);



        return convertView;
    }
}
