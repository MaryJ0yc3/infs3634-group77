package com.example.infs3634_group77.LearningFightScore;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.Helpers.DefinitionService;
import com.example.infs3634_group77.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FightScreenFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "com.example.infs3634_group77.MESSAGE";
    private static final String TAG = "FightScreenFragment";
    private String word;
    private String definition;
    private String pronunciation;
    ArrayList<String> questionWords;

    //PSEUDOCODE

    //press START button in wordlist fragment and replace with this fragment
    //bundle the word list and send to fightscreen DONE
    //cycle through String array list and execute Async Task each time to get question
                // populate question textview with the definition of each word
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
            Bundle bundle = getArguments();
            questionWords = bundle.getStringArrayList("category words");
            //populate with first question
            word = questionWords.get(0);

            //Async task execute
            new FightScreenFragment.GetDefinitionTask().execute();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fight_screen, container, false);

        //elements
        TextView question = v.findViewById(R.id.tvQuestion);
        Button submit = v.findViewById(R.id.submitBtn);
        Button skip = v.findViewById(R.id.skipBtn);




        //set onClick listener for submit
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return v;
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
            Log.d(TAG, "onPostExecute: new DefinitionResponse word is: "
                    + word.getWord()
                    + ", The definition is: "
                    + word.getFirstDefinition());
            definition = word.getDefinitions().get(0).getDefinition();
            View v = getView();
            ((TextView) v.findViewById(R.id.tvQuestion)).setText("What does this mean: "
                    + definition);
            // Can also call for example, and image
            Log.d(TAG, "onPostExecute: Added new DefinitionResponse");
        }
    }

}
