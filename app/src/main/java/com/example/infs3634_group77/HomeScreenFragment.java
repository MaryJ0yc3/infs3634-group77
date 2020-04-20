package com.example.infs3634_group77;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3634_group77.Helpers.CategoryAdapter;

import java.util.ArrayList;


public class HomeScreenFragment extends Fragment {
    //has category recycler view horizontal. if lazy just do buttons but
    // can get marked down coz it limits the cards we can add later

    //has nice visuals in the bg
    //has help button that goes to tutorial
    public HomeScreenFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_row, container, false);
        RecyclerView mRecyclerView = view.findViewById(R.id.rvHome);

        CategoryAdapter mAdapter = new CategoryAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }
}
