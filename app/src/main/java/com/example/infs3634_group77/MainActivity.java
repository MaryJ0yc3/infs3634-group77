package com.example.infs3634_group77;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Entities.Definition;
import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.LearningFightScore.FightScreenFragment;
import com.example.infs3634_group77.LearningFightScore.WordDetailFragment;
import com.example.infs3634_group77.LearningFightScore.WordListFragment;
import com.example.infs3634_group77.Settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Just here to look pretty and give impression
    //Just has 'start' button and nice graphic behind with logo.
    //can add progress bar?
    //Joyce might add animations later
    BottomNavigationView bottomNavigationView;
    Category category;
    MediaPlayer mPlayer;

    // ArrayList of word and definitions as created from repeat API call from one category
    ArrayList<DefinitionResponse> wordArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.elevator);
        mPlayer.setLooping(true);
        mPlayer.start();

        // initialising bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNav);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();


        //Navigation straight to home screen if previously haven't used app
        if (savedInstanceState == null) {
            Fragment fragment = new HomeScreenFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
        }

        // Code to change fragments depending on which menuItem is pressed
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        fragment = new HomeScreenFragment();
                        break;
                    case R.id.search:
                        fragment = new DictionaryScreenFragment();
                        break;
                    case R.id.settings:
                        fragment = new SettingsFragment();
                        break;
                }
                fragmentTransaction.replace(R.id.fragmentContainer,fragment).commit();
                return true;
            }
        });

        //softkeyboard layout changes
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private void releaseMediaPlayer(){
        if(mPlayer != null)
        {
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

    public Category getCategory() {return category;}

    public void setCategory(Category mCategory) {
        category = mCategory;
    }

    public String getCategoryScore() {return category.getBestScore();}

    public void setCategoryScore(Double score) {
        category.setBestScore(score);
    }

    /*public void resetDefinitionResponseList(ArrayList<DefinitionResponse> newList) {
        if (wordArrayList != null) { wordArrayList.clear(); }
        wordArrayList.addAll(newList);
        notifyAll();
    }

    public void addDefinitionResponseList(DefinitionResponse newResponse) {wordArrayList.add(newResponse)};
     */
}
