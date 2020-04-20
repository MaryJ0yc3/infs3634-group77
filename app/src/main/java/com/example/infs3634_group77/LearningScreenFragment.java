package com.example.infs3634_group77;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class LearningScreenFragment extends Fragment {

    //has recycler view with words (using word adapter to blow up).
    // call from category object which has hardcoded words.
//    words object must call using service
    //need to


    public LearningScreenFragment() {}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learning_screen, container, false);
    }
}
