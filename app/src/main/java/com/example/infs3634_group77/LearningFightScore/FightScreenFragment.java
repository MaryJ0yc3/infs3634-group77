package com.example.infs3634_group77.LearningFightScore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.infs3634_group77.Entities.Definition;
import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.R;

import java.util.List;


public class FightScreenFragment extends Fragment {
    List<DefinitionResponse> mWordsList;

    //PSEUDOCODE

    //press START button in wordlist fragment and replace with this fragment
    //bundle the word list and send to fightscreen
    //cycle through array list and populate question textview with the definition of each word
    //optional image view (if null url, set not visible?)
    //use tab layout to insert answer. Once finished typing, hit submit (has if else statements)
    //Each time it's correct, HP of monster goes down, add to "correctWords" array list
    //Each time it's incorrect, lose one of three heart, add to "wrongWords" array list
    //if they lose all three hearts, launch score screen and display array lists
    //If they hit skip button, word is added to "last chance Arraylist"
    //once finished array list, go to last chance arraylist and cycle through. move to appropriate word list according to result
    //If last word of wordList, submit button checks last chance arraylist..
                           // if last chance array list = size 0, create bundle of correct and wrong arraylist
                            // if last change array list is >0, cycle through
            // then press submit button recognises no words left, replace fragment with score screen
            // send through bundle.
    public FightScreenFragment() { }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fight_screen, container, false);
    }

    public void setWords(List<DefinitionResponse> words) {
        if (mWordsList != null) {
            mWordsList.clear();
        }
        mWordsList.addAll(words);
    }
}
