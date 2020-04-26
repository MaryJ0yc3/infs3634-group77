package com.example.infs3634_group77;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.Helpers.DefinitionService;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class DictionaryScreenFragment extends Fragment {
    private String TAG = "DictionaryFragment";
    TextInputEditText edit;
    String pronounce;
    String example;
    String type;
    String definition;
    ImageView image;
    String input;

    // Dictionary search page which allows users to search for a word from the api and get a result
    public DictionaryScreenFragment() {
    }

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
        View v = inflater.inflate(R.layout.fragment_learning_screen, container, false);

        edit = v.findViewById(R.id.type_word);
        Button search = v.findViewById(R.id.searchbtn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = String.valueOf(edit.getText());
                if (!input.isEmpty()) {
                    new GetDefinitionTask().execute();
                    edit.getText().clear();
                }
            }
        });

        ImageButton google = v.findViewById(R.id.googlebtn);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.isEmpty()) {
                    searchDef(input);
                } else {
                    // Error message telling user to input text
                }
            }
        });
        return v;
    }

    private void searchDef(String name) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=" + input));
        startActivity(intent);
    }

    private void updateUi() {
        View v = getView();
        if (v != null && (input != null || definition != null || pronounce != null || example != null || type != null)) {
            ((TextView) v.findViewById(R.id.wordtv)).setText(input);
            ((TextView) v.findViewById(R.id.definitiontv)).setText(definition);
            ((TextView) v.findViewById(R.id.pronouncetv)).setText(pronounce);
            ((TextView) v.findViewById(R.id.exampletv)).setText(example);
            ((TextView) v.findViewById(R.id.typetv)).setText(type);
        }
    }

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
                Call<DefinitionResponse> call = service.getDefinitions(input, authToken);
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
            pronounce = word.getPronunciation();
            example = word.getExample();
            type = word.getDefinitions().get(0).getType();
            updateUi();
            edit.clearComposingText();
            // Can also call for example, and image
            Log.d(TAG, "onPostExecute: Added new DefinitionResponse");
        }
    }
}

