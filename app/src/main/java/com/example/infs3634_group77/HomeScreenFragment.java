package com.example.infs3634_group77;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Entities.Definition;
import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.Helpers.CategoryAdapter;
import com.example.infs3634_group77.Helpers.DefinitionService;
import com.example.infs3634_group77.LearningFightScore.WordListFragment;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


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

        // Making RecyclerView. need to use v. because we're inflating
        RecyclerView mRecyclerView = v.findViewById(R.id.rvHome);
        Log.d(TAG, "on createView: finished finding rvHome");
        mRecyclerView.setHasFixedSize(true);
        Log.d(TAG, "on createView: setHAsFixedSize true");

        mLayoutManager = new LinearLayoutManager(getContext());
        Log.d(TAG, "on createView: finished mLayoutManager .... get Context instead of this");
        mRecyclerView.setLayoutManager(mLayoutManager);
        Log.d(TAG, "on createView: finished setting mRecyclerView as mLayoutManager");

        // Change to word list fragment when clicked and bundles the position clicked
        CategoryAdapter.RecyclerViewClickListener listener = new CategoryAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "onClick: starting");
                Fragment fragment = new WordListFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                fragment.setArguments((bundle));
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                Log.d(TAG, "launchWordListFragment: finish");
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
}
