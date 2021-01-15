package com.example.flixster.models;

import android.media.Rating;
import android.util.Log;
import android.widget.RatingBar;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

@Parcel
public class Movie {

    public static final String CONFIG_URL = "https://api.themoviedb.org/3/configuration?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "Movie";

    String backdropPath;
    String posterPath;
    String title;
    String overview;
    Integer movieId;
    ArrayList<Integer> posterSizes;
    ArrayList<Integer> backdropSizes;
    double rating;

    // Empty constructor needed by the Parceler Library
    public Movie(){
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString("backdrop_path");
        posterPath = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        movieId = jsonObject.getInt("id");
        rating = jsonObject.getDouble("vote_average");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getBackdropPath() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(CONFIG_URL, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int i, Headers headers, JSON json) {
//                Log.d(TAG, "onSuccess");
//                JSONObject jsonObject = json.jsonObject;
//                try {
//                    JSONObject images = jsonObject.getJSONObject("images");
//                    Log.i(TAG, "Images: " + images.toString());
//                    backdropSizes = images.getJSONArray("poster_sizes");
//                } catch (JSONException e) {
//                    Log.e(TAG, "Hit json exception", e);
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
//
//            }
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {return rating;}

    public int getMovieid() {return movieId;}
}
