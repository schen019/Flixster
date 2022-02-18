package com.suyi.flixster;

import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.suyi.flixster.adapter.MovieAdapter;
import com.suyi.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String  NOW_PLAYING_URL ="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed ";
    public static final String TAG ="MainActivity";

    List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById((R.id.rvMovies))
        movies = new ArrayList<>()；

        MovieAdapter movieAdapter = new MovieAdapter(this, movies);

        rvMovies.setAdapter(movieAdapter);

        rvMovies.serLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyHttpCllient();
        client.get(NOW_PLAYING_URL,new JsonHttpresponseHandler(){
            @Override
            public void onSuccess(int statusCode, Headers headers,JSON json){
                Log.d(TAG,"onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try{
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG,"Results:" + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                    Log.i(TAG,"Movie:" + movies.size());
                }catch (JSONException e){
                    Log.e(TAG,"Hit json exception",e);
                }
            }
            @Override
            public  void onFailure(int statusCode, Headers handlers, String response, Throwable throwable){
                Log.d(TAG,"onFailture");
            }
        })
    }
}