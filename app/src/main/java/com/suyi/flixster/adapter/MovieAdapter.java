package com.suyi.flixster.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suyi.flixster.R;
import com.suyi.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.VeiwHolder>{
    Context context;
    List<Movie> movies;
    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }
    @NonNull
    @Override
    public VeiwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new VeiwHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull VeiwHolder holder, int position) {
        Log.d("MovieAdapter","onBindViewHolder" +position);
        Movie movie =movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class VeiwHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverveiw;
        ImageView ivPoster;
        public VeiwHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverveiw = itemView.findViewById(R.id.tvOverveiw);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverveiw.setText(movie.getOverview());
            String imageUrl;
            if(context.getResoures().getConfiguration().orientation == Configuration.orientation_landscape){
                imageUrl =movie.getBackdropPath();
            }else {
                imageUrl =movie.getPosterPath();
            }
            Glide.with(context).load(movie.getPosterPath().into(ivPoster));
        }
    }
}
