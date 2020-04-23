package com.example.infs3634_group77.LearningFightScore;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.infs3634_group77.Entities.Category;
import com.example.infs3634_group77.Entities.Definition;
import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.Helpers.CategoryAdapter;
import com.example.infs3634_group77.Helpers.DefinitionAdapter;
import com.example.infs3634_group77.Helpers.DefinitionService;
import com.example.infs3634_group77.HomeScreenFragment;
import com.example.infs3634_group77.R;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WordListFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "com.example.infs3634_group77.MESSAGE";
    private static final String TAG = "WordListFragment";
    public ArrayList<DefinitionResponse> mWordsList = new ArrayList<DefinitionResponse>();
    private List<String> mCategoryWords;
    //private String currentWord;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String token = "14bffab1e7b99dd69186fac5837139842c67aab5";

    public WordListFragment (){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
            //Get position int of Category clicked and then get List of Words to use in API call
            // Making RecyclerView. need to use v. because we're inflating
        Log.d(TAG, "onCreate: starting");
        new GetWordsListTask().execute();
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
        mAdapter = new DefinitionAdapter(this, new ArrayList<DefinitionResponse>());
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
                //Bundle bundle = new Bundle();
                //fragment.setArguments((bundle));
                ((FightScreenFragment) fragment).setWords(mWordsList); // Pass DictionaryResponse list to Fight Screen
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                Log.d(TAG, "launchWordListFragment: finish");
            }
        });
        return v;
    }

    // Auth Token 14bffab1e7b99dd69186fac5837139842c67aab5
    private class GetWordsListTask extends AsyncTask<Void, Void, DefinitionResponse> {
        Bundle bundle = getArguments();
        int position = bundle.getInt("position");
        Category mCategory = new Category().getCategory(position);
        String authToken = ("Token 14bffab1e7b99dd69186fac5837139842c67aab5");
        @Override
        protected DefinitionResponse doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: Start");
            try {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://owlbot.info/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                DefinitionService service = retrofit.create(DefinitionService.class);

                //for (int i = 0; i < mCategoryWords.size(); i++) {
                    String currentWord = mCategory.getWordList().get(0);
                    Log.d(TAG, "doInBackground: made retrofit");
                    Call<DefinitionResponse> call = service.getDefinitions(currentWord, authToken);
                    Log.d(TAG, "doInBackground: getDefinitions finished");
                    Response<DefinitionResponse> definitionResponse = call.execute();
                    Log.d(TAG, "doInBackground: definitionResponse execute");
                    DefinitionResponse mWord = definitionResponse.body();
                    // Currently only calls API once for the first word in the list
                    mWord.setFirstDefinition(mWord.getDefinitions().get(0).getDefinition());
                    return mWord;
                //}
                //return null;
            } catch (IOException e) {
                Log.d(TAG, "doInBackground: API call failed");
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(DefinitionResponse word) {
            // Add to the definitions List in WordListFragment instead so can pass it on to FightScreen
            Log.d(TAG, "onPostExecute: new DefinitionResponse word is: " + word.getWord() + " with definition " + word.getFirstDefinition());
            if (word != null) {mWordsList.add(word);}
            // Not sure why but mAdapter.setWords(mWordsList) or mAdapter.addWord(word) doesnt work? but works for coinbag
            Log.d(TAG, "onPostExecute: Added new DefinitionResponse");
        }
    }
}
