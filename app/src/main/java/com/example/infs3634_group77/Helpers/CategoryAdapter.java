package com.example.infs3634_group77.Helpers;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Entities.CategoryData;
import com.example.infs3634_group77.R;

import org.w3c.dom.Text;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private String[] mDataset;

    // Constructor
    public CategoryAdapter(String[] dataSet) {
        mDataset = dataSet;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Creates the items on the layout
        private TextView category;
        public TextView score;
        public ImageView icon;
        public View view;

        // Viewholder method to assign to xml widgets
        public CategoryViewHolder(View v) {
            super(v);
            view = v;
            category = (TextView) v.findViewById(R.id.tvCategory);
            score = (TextView) v.findViewById(R.id.tvScore);
            icon = (ImageView) v.findViewById(R.id.ivCategory);
        }

        // Method to replace items with desired data
        public void bindView(int position) {
            category.setText(mDataset[position]);
            icon.setImageResource(CategoryData.picturePath[position]);
        }

        @Override
        public void onClick(View v) {
            // On click will go to the learning activity and populate recyclerview there with corresponding words
        }
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row, parent, false);
        CategoryViewHolder vh = new CategoryViewHolder(v);
        return null;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
