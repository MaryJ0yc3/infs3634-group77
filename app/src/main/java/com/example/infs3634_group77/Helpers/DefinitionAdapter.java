package com.example.infs3634_group77.Helpers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_group77.Entities.Definition;
import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.LearningFightScore.FightScreenFragment;
import com.example.infs3634_group77.LearningFightScore.WordListFragment;
import com.example.infs3634_group77.MainActivity;
import com.example.infs3634_group77.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.WordViewHolder> {
    public String TAG = "DefinitionAdapter";
    private WordListFragment mParentActivity;
    private ArrayList<DefinitionResponse> mWords = new ArrayList<DefinitionResponse>();

    // On Click should show definition? Or go no where
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    // Constructor
    public DefinitionAdapter(WordListFragment parent, ArrayList<DefinitionResponse> wordsList) {
        mParentActivity = parent;
        mWords = wordsList;
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder{
        public TextView tvWord, tvDefinition;

        public WordViewHolder(View v){
            super(v);
            tvWord = v.findViewById(R.id.tvWord);
            tvDefinition = v.findViewById(R.id.tvDefinition);
            tvDefinition.setVisibility(v.INVISIBLE);
        }
    }

    // Set row layout in RecyclerView
    @Override
    public DefinitionAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_row, parent, false);
        return new DefinitionAdapter.WordViewHolder(v);

    }

    // Set the contents of each view (row) - invoked by Layout Manager
    @Override
    public void onBindViewHolder(DefinitionAdapter.WordViewHolder holder, int position) {
        DefinitionResponse mWord= mWords.get(position);
        holder.tvWord.setText(mWord.getWord());
        holder.tvDefinition.setText(mWord.getFirstDefinition());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mWords.size();
    }

    // Update the list of definitions
    public void addWord(DefinitionResponse definition) {
        mWords.add(definition);
    }

    // Get the list of definitions
    public ArrayList<DefinitionResponse> getWords() {
        return mWords;
    }

    public void setWords(ArrayList<DefinitionResponse> words) {
        if (mWords != null) {
            mWords.clear();
        }
        mWords.addAll(words);
    }
}
