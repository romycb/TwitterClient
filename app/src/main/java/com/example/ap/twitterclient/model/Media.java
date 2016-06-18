//package com.example.ap.twitterclient.model;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
///**
// * Created by romybeugeling on 20-05-16.
// */
//public class Media {
//
//    private int id;
//    private String id_str;
//    private int[] indices = new int[2];
//    private int beginIndex, endIndex;
//    private String media_url;
//    private String media_url_https;
//    private String url;
//    private String display_url;
//    private String expanded_url;
//    private String type;
//    private int mw, mh, sw, sh, tw, th, lw, lh;
//    private String mr, sr, tr, lr;
//
//    public Media(int id, String id_str, JSONArray indicesArray, String media_url, String media_url_https, String url,
//                 String display_url, String expanded_url, String type, JSONObject sizes) {
//        try {
//            this.id = id;
//            this.id_str = id_str;
//            for (int i = 0; i < indicesArray.length(); i++) {
//                int index = indicesArray.getInt(i);
//                indices[i] = index;
//            }
//            this.media_url = media_url;
//            this.media_url_https = media_url_https;
//            this.url = url;
//            this.display_url = display_url;
//            this.expanded_url = expanded_url;
//            this.type = type;
//
//            JSONObject mediumSize = sizes.getJSONObject("medium");
//            mw = mediumSize.getInt("w");
//            mh = mediumSize.getInt("h");
//            mr = mediumSize.getString("resize");
//            JSONObject smallSize = sizes.getJSONObject("small");
//            sw = smallSize.getInt("w");
//            sh = smallSize.getInt("h");
//            sr = smallSize.getString("resize");
//            JSONObject thumbSize = sizes.getJSONObject("thumb");
//            tw = thumbSize.getInt("w");
//            th = thumbSize.getInt("h");
//            tr = thumbSize.getString("resize");
//            JSONObject largeSize = sizes.getJSONObject("large");
//            lw = largeSize.getInt("w");
//            lh = largeSize.getInt("h");
//            lr = largeSize.getString("resize");
//
//        } catch (JSONException e){
//            e.printStackTrace();
//        }
//    }
//
//    public String getMedia_url() {
//        return media_url;
//    }
//    public String getDisplay_url(){
//        return display_url;
//    }
//
//    public int getBeginIndex() {
//        return this.beginIndex = indices[0];
//    }
//
//    public int getEndIndex() {
//        return this.endIndex = indices[1];
//    }
//
//}
