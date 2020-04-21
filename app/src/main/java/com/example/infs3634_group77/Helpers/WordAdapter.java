package com.example.infs3634_group77.Helpers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Entities.Word;
import com.example.infs3634_group77.R;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private ArrayList<Word> mWords;

    public WordAdapter(){}

    public WordAdapter(ArrayList<Word> words) {
        mWords = words;
    }


    public static class WordViewHolder extends RecyclerView.ViewHolder{

        public TextView tvWord, tvDefinition;


        public WordViewHolder(View v){
            super(v);
            tvWord = v.findViewById(R.id.tvWord);
            tvDefinition = v.findViewById(R.id.tvDefinition);

        }

    }

    @Override
    public WordAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_row, parent, false);
        return new WordAdapter.WordViewHolder(v);

    }

    //set the holders depending on position
    @Override
    public void onBindViewHolder(WordAdapter.WordViewHolder holder, int position) {

        Word word = mWords.get(position);
        holder.tvWord.setText(word.getWord());
        holder.tvDefinition.setText(word.getDefinition());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mWords.size();
    }



}
