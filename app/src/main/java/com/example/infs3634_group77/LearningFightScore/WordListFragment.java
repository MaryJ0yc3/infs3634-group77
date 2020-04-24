package com.example.infs3634_group77.LearningFightScore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.Helpers.WordListAdapter;
import com.example.infs3634_group77.HomeScreenFragment;
import com.example.infs3634_group77.R;

import java.util.ArrayList;


public class WordListFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "com.example.infs3634_group77.MESSAGE";
    private static final String TAG = "WordListFragment";
    public ArrayList<DefinitionResponse> mWordsList = new ArrayList<DefinitionResponse>();
    private ArrayList<String> mCategoryWords;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public WordListFragment (){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            int position = bundle.getInt("position");
            Category mCategory = new Category().getCategory(position);
            mCategoryWords = mCategory.getWordList();

        }
        Log.d(TAG, "onCreate: starting");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the row layout for this fragment
        View v = inflater.inflate(R.layout.fragment_word_list, container, false);
        RecyclerView mRecyclerView = v.findViewById(R.id.rvWordListPreLearning);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        WordListAdapter.RecyclerViewClickListener listener = new WordListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "onClick: starting");
                Fragment fragment = new WordDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("word", mCategoryWords.get(position));
                fragment.setArguments((bundle));
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                Log.d(TAG, "onClick: launch WordDetailFragment");
            }

        };
        mAdapter = new WordListAdapter(mCategoryWords, listener);
        mRecyclerView.setAdapter(mAdapter);

        // Setting Two Button's OnClick Methods
        Button exit = v.findViewById(R.id.exitBtn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Exit Button");
                Fragment fragment = new HomeScreenFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
            }
        });
        Button start = v.findViewById(R.id.startBtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Start Fight Button");
                Fragment fragment = new FightScreenFragment();
                ((FightScreenFragment) fragment).setWords(mCategoryWords);
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
            }
        });
        return v;
    }
}
