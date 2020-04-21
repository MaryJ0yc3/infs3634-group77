package com.example.infs3634_group77;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Entities.Word;
import com.example.infs3634_group77.Helpers.CategoryAdapter;
import com.example.infs3634_group77.LearningFightScore.WordListFragment;

import java.util.ArrayList;


public class HomeScreenFragment extends Fragment {

    //has nice visuals in the bg
    //has help button that goes to tutorial

    public static final String EXTRA_MESSAGE = "com.example.infs3634_group77.MESSAGE";
    private static final String TAG = "HomeScreenFragment";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public HomeScreenFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "on createView: starting");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_screen, container, false);
        Log.d(TAG, "on createView: finished inflating");

        //find elements. need to use v. because we're inflating
        RecyclerView mRecyclerView = v.findViewById(R.id.rvHome);
        Log.d(TAG, "on createView: finished finding rvHome");

        mRecyclerView.setHasFixedSize(true);
        Log.d(TAG, "on createView: setHAsFixedSize true");

        mLayoutManager = new LinearLayoutManager(getContext());
        Log.d(TAG, "on createView: finished mLayoutManager .... get Context instead of this");
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.d(TAG, "on createView: finished setting mRecyclerView as mLayoutManager");


        CategoryAdapter.RecyclerViewClickListener listener = new CategoryAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "onClick: starting");
                launchWordListFragment(position);
            }

        };

        mAdapter = new CategoryAdapter(Category.getCategories(), listener);
        Log.d(TAG, "on createView: finished creating CategoryAdapter");
        mRecyclerView.setAdapter(mAdapter);
        Log.d(TAG, "on createView: finished setting Adapter");

        Log.d(TAG, "on createView: starting return v");
        return v;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    //sends over the position in a bundle to word list fragment
    private void launchWordListFragment(int position){
        Log.d(TAG, "launchWordListFragment: starting");
        FragmentManager manager = getParentFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment wordListFragment= new WordListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        wordListFragment.setArguments((bundle));
        transaction.replace(android.R.id.content, wordListFragment);
        transaction.commit();
        Log.d(TAG, "launchWordListFragment: finish");
    }

}
