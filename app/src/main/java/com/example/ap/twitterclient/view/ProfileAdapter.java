package com.example.ap.twitterclient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.example.ap.twitterclient.R;
import com.example.ap.twitterclient.model.Tweet;

import java.util.List;

/**
 * Created by Evi on 21-6-2016.
 */
public class ProfileAdapter extends ArrayAdapter<Tweet> {

    private LayoutInflater inflater = null;

    public ProfileAdapter(Context context, int resource, List<Tweet> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tweet_list_item, parent, false);
        }
        return convertView;
    }
}
