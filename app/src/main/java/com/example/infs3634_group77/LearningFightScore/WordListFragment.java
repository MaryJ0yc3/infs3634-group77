package com.example.infs3634_group77.LearningFightScore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Helpers.CategoryAdapter;
import com.example.infs3634_group77.Helpers.WordAdapter;
import com.example.infs3634_group77.R;

import java.util.ArrayList;


public class WordListFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "com.example.infs3634_group77.MESSAGE";
    private static final String TAG = "WordListFragment";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // Empty Constructor
    public WordListFragment (){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_word_list, container, false);
        Log.d(TAG, "on createView: finished inflating");

        RecyclerView mRecyclerView = v.findViewById(R.id.rvWordListPreLearning);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //trying to get position int of getCategories and then get WordList
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");

        ArrayList<Category> mCategoryList = Category.getCategories();
        Category mCategory = mCategoryList.get(position);

        //now get the category at that position
        mAdapter = new WordAdapter(mCategory.getWordList());

        //set Adapter
        mRecyclerView.setAdapter(mAdapter);

        return v;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


}
