package com.example.infs3634_group77.Helpers;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.R;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private String TAG = "CategoryAdapter";
    private List<Category> mCategories;
    private RecyclerViewClickListener mListener;

    public CategoryAdapter(){}

    //constructor
    public CategoryAdapter(List<Category> categories, RecyclerViewClickListener listener) {
        mCategories = categories;
        mListener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvCategory, tvDesc, tvBestScore, tvScore;
        public ImageView ivCategory;

        private RecyclerViewClickListener mListener;

        public CategoryViewHolder(View v, RecyclerViewClickListener listener){
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            tvCategory = v.findViewById(R.id.tvCategory);
            tvDesc = v.findViewById(R.id.tvDesc);
            tvBestScore = v.findViewById(R.id.tvBestScore);
            tvScore = v.findViewById(R.id.tvScore);
            ivCategory = v.findViewById(R.id.ivCategory);
        }

        @Override
        public void onClick(View view) {
            // Pass the category
            mListener.onClick(view, getAdapterPosition());
        }

    }

    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row, parent, false);
        return new CategoryViewHolder(v, mListener);
    }

    // Set the holders depending on position
    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category category = mCategories.get(position);
        holder.tvCategory.setText(category.getCategoryName());
        holder.tvDesc.setText(category.getDesc());
        holder.tvBestScore.setText(category.getBestScore());
        holder.ivCategory.setImageResource(category.getIcon());

        if (category.getBestScore() > 0) {
            holder.tvBestScore.setVisibility(View.VISIBLE);
            holder.tvScore.setVisibility(View.VISIBLE);
        }
    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCategories.size();
    }
}
