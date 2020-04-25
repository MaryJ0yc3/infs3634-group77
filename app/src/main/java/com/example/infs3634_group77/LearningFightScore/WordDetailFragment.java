package com.example.infs3634_group77.LearningFightScore;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.Helpers.DefinitionService;
import com.example.infs3634_group77.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordDetailFragment extends Fragment {
    private String TAG = "WordDetailFragment";
    private String word;
    private String definition;
    private String example;

    public WordDetailFragment() {
    }
    // Populates the word detail layout with all the items called from API
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            word = bundle.getString("word");
            new WordDetailFragment.GetDefinitionTask().execute();
        }
        Log.d(TAG, "onCreate: starting");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the row layout for this fragment
        View v = inflater.inflate(R.layout.fragment_word_detail, container, false);
        updateUi();

        // Setting Back Button's OnClick Methods
        Button back = v.findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Exit Button");
                Fragment fragment = new WordListFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
            }
        });
        return v;
    }

    private void updateUi(){
        View v = getView();
        if (v != null && (word != null || definition != null || example != null)) {
            ((TextView) v.findViewById(R.id.tvWord)).setText(word);
            ((TextView) v.findViewById(R.id.tvDefinition)).setText(definition);
            ((TextView) v.findViewById(R.id.tvExample)).setText(example);
        }
    }

    // Auth Token 14bffab1e7b99dd69186fac5837139842c67aab5
    private class GetDefinitionTask extends AsyncTask<Void, Void, DefinitionResponse> {
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
                Call<DefinitionResponse> call = service.getDefinitions(word, authToken);
                Log.d(TAG, "doInBackground: getDefinitions finished");
                Response<DefinitionResponse> definitionResponse = call.execute();
                Log.d(TAG, "doInBackground: definitionResponse execute");
                DefinitionResponse wordDetail = definitionResponse.body();
                wordDetail.setFirstDefinition(wordDetail.getDefinitions().get(0).getDefinition());
                wordDetail.setExample(wordDetail.getDefinitions().get(0).getExample());
                // Currently only calls API once for the first word in the list
                return wordDetail;
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
            definition = word.getFirstDefinition();
            example = word.getExample();
            updateUi();
            // Can also call for example, and image
            Log.d(TAG, "onPostExecute: Added new DefinitionResponse");
        }
    }
}
