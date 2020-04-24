package com.example.infs3634_group77.Helpers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.LearningFightScore.WordDetailFragment;
import com.example.infs3634_group77.LearningFightScore.WordListFragment;
import com.example.infs3634_group77.R;

import java.util.ArrayList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    public String TAG = "WordListAdapter";
    private ArrayList<String> mWords;
    private RecyclerViewClickListener mListener;

    // Constructor
    public WordListAdapter(ArrayList<String> wordsList, RecyclerViewClickListener listener) {
        mListener = listener;
        mWords = wordsList;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);

    }

    public static class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvWord;
        private RecyclerViewClickListener mListener;

        public WordViewHolder(View v, RecyclerViewClickListener listener){
            super(v);
            tvWord = v.findViewById(R.id.tvWord);
            mListener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
            /*Bundle arguments = new Bundle();
            arguments.putString("Word", selectedWord);
            // On click of the row, pass clicked word and change to WordDetailFragment
            WordDetailFragment fragment = new WordDetailFragment();
            fragment.setArguments(arguments);
            getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
            mParentActivity.getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment);*/

        }
    }

    // Set row layout in RecyclerView
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_row, parent, false);
        return new WordViewHolder(v, mListener);

    }

    // Set the contents of each view (row) - invoked by Layout Manager
    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String word = mWords.get(position);
        holder.tvWord.setText(mWords.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mWords.size();
    }

}
