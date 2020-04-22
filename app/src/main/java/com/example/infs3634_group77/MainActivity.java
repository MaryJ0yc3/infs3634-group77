package com.example.infs3634_group77;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Entities.Word;
import com.example.infs3634_group77.Settings.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Just here to look pretty and give impression
    //Just has 'start' button and nice graphic behind with logo.
    //can add progress bar?
    //Joyce might add animations later
    BottomNavigationView bottomNavigationView;
    Category category;

    // ArrayList of word and definitions as created from repeat API call from one category
    ArrayList<Word> wordArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialising bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNav);

        //Navigation straight to home screen if previously haven't used app
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeScreenFragment()).commit();
        }

        // Code to change fragments depending on which menuItem is pressed
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                return true;
            }
        });
    }
}
