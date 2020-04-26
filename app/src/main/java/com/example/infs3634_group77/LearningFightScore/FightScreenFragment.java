package com.example.infs3634_group77.LearningFightScore;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3634_group77.Entities.DefinitionResponse;
import com.example.infs3634_group77.Helpers.DefinitionService;
import com.example.infs3634_group77.MainActivity;
import com.example.infs3634_group77.R;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FightScreenFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "com.example.infs3634_group77.MESSAGE";
    private static final String TAG = "FightScreenFragment";
    private String answerInput;
    private String word;
    private String definition;
    private ArrayList<String> questionWords;
    private int indexCounter = 0;


    //bundle Arraylists
    private ArrayList<String> correctWords = new ArrayList<>();
    private ArrayList<String> incorrectWords = new ArrayList<>();
    private ArrayList<String> tempSkipWords = new ArrayList<>();


    public FightScreenFragment() { }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        questionWords = ((MainActivity)getActivity()).getCategory().getWordList();
        //populate with first question
        word = questionWords.get(0);
        //Async task execute
        new FightScreenFragment.GetDefinitionTask().execute();
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
        TextInputLayout til_answer = v.findViewById(R.id.til_answer);
        EditText editText_answer = v.findViewById(R.id.editText_answer);
        Button help = v.findViewById(R.id.help);

        int originalQListHighestIndex = questionWords.size();

        //set onClick listener for submit.
        // 1) check validity and add to correct, incorrect or skip arraylist
        // 2) populate the next question by using counter

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "submit onClick: started!");
                answerInput = editText_answer.getText().toString().trim();
                if(answerInput != ""){
                    markTheAnswer(answerInput);
                    //update counter
                    int mIndexCounter = indexCounter;
                    int mQListHighestIndex = questionWords.size() - 1;

                    //populate question tv with next word
                    if(mIndexCounter < mQListHighestIndex){
                        indexCounter ++;
                        Log.d(TAG, "submit onClick: indexCounter now at element no. " + indexCounter
                                + "/" + mQListHighestIndex + ". Set new word and populate next question");
                        word = questionWords.get(indexCounter);
                        Log.d(TAG, "submit onClick: new word is at index [" + indexCounter + "] is " + word);
                        //populate the next question
                        new FightScreenFragment.GetDefinitionTask().execute();
                        //clear editText
                        editText_answer.setText("");
                    }

                    //current index is at final index. Launch score screen
                    else if (mIndexCounter == mQListHighestIndex){
                        Log.d(TAG, "submit onClick: no more elements left in questionWords array.");
                        launchScoreScreen();
                    }
                    //set animation to score screen if time


                }
                else{

                }

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //update counter
                int mIndexCounter = indexCounter;
                int mQListHighestIndex = questionWords.size() - 1;

                //case: first time skipping, add word to end to question array and continue
                if (mIndexCounter <= mQListHighestIndex && mIndexCounter < originalQListHighestIndex ){
                    questionWords.add(word);
                    Log.d(TAG, "skip onClick: the word '" + word + "' has been added to questionWords");
                    Log.d(TAG, "submit onClick: indexCounter now at element no. " + indexCounter
                            + "/" + mQListHighestIndex + " with original highest index of " + originalQListHighestIndex
                            + ". Set new word and populate next question");
                    indexCounter ++;
                    Log.d(TAG, "skip onClick: update counter to " + indexCounter);
                    word = questionWords.get(indexCounter);
                    Log.d(TAG, "submit onClick: new word is at index [" + indexCounter + "] is " + word);
                    //populate the next question
                    new FightScreenFragment.GetDefinitionTask().execute();
                    //clear editText
                    editText_answer.setText("");
                }

                //case: already skipped once and there are still words left.
                // need to move to incorrect, then go to next word
                else if(mIndexCounter >= originalQListHighestIndex && mIndexCounter < mQListHighestIndex){
                    incorrectWords.add(word + " (skipped twice)");
                    indexCounter ++;
                    Log.d(TAG, "skip onClick: update counter to " + indexCounter);
                    word = questionWords.get(indexCounter);
                    Log.d(TAG, "skip onClick: new word is " + word + "populating new question now");
                    //populate the next question
                    new FightScreenFragment.GetDefinitionTask().execute();
                    //clear editText
                    editText_answer.setText("");
                    Log.d(TAG, "skip onClick: successfully skipped");

                }

                //case: reached the end of question list. launch score screen
                else if (mIndexCounter == mQListHighestIndex) {
                    incorrectWords.add(word + " (skipped twice)");
                    Log.d(TAG, "skip onClick: indexCounter/highestIndex = " + indexCounter + "/" + mQListHighestIndex);
                    launchScoreScreen();
                }
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FancyShowCaseView fancyShowCaseView1 = new FancyShowCaseView.Builder(getActivity())
                        .title("When confident click here")
                        .titleStyle(0, Gravity.CENTER)
                        .focusOn(submit)
                        .build();
                final FancyShowCaseView fancyShowCaseView2 = new FancyShowCaseView.Builder(getActivity())
                        .title("If the question is too hard")
                        .titleStyle(0, Gravity.CENTER)
                        .focusOn(skip)
                        .build();

                final FancyShowCaseView fancyShowCaseView3 = new FancyShowCaseView.Builder(getActivity())
                        .title("Your question appears here!")
                        .titleStyle(0, Gravity.CENTER)
                        .focusOn(question)
                        .build();

                FancyShowCaseQueue mQueue = new FancyShowCaseQueue()
                        .add(fancyShowCaseView1)
                        .add(fancyShowCaseView2)
                        .add(fancyShowCaseView3);

                mQueue.show();
            }
        });


        return v;
    }

    private boolean validateAnswer(String answerInput){
        Log.d(TAG, "validateAnswer: start");
        Log.d(TAG, "validateAnswer: note that word is " + word);
        Log.d(TAG, "validateAnswer: the answerInput value is: " + answerInput);

        //cases for Answer validity to submit (must not be empty, or too long)
        if(answerInput.length() == 0){
            Log.d(TAG, "validateAnswer: answerInput value '" + answerInput + "' ...is empty");
            Toast.makeText(getActivity(),
                    "Aww, let's not submit an empty answer! Next time, skip if you're unsure.",
                    Toast.LENGTH_LONG).show();

            return false;
        }else if (answerInput.length() > 20) {
            Log.d(TAG, "validateAnswer: answerInput value: " + answerInput + "is too long");
            Toast.makeText(getActivity(),
                    "Looks like your answer is too long...",
                    Toast.LENGTH_LONG).show();
            return false;
        }else{
            Log.d(TAG, "validateAnswer: answerInput value: '" + answerInput + "' is valid");
            return true;
        }

    }


    public void markTheAnswer(String answerInput){
        boolean checkValidity = validateAnswer(answerInput.toLowerCase());
        Log.d(TAG, "submit onClick: is '" + answerInput + "' valid? " + checkValidity);
        if(checkValidity == true){
            //check correct or incorrect
            Log.d(TAG, "submit onClick: check if word is correct or incorrect");
            if(answerInput.toLowerCase().equals(word.toLowerCase().trim())){
                Log.d(TAG, "submit onClick: Correct! " + answerInput + " equals " + word);
                correctWords.add(word);
                Log.d(TAG, "submit onClick: " + word + " added to correctWords ArrayList");
                //maybetodo populate image view animation for 3 seconds then fade
            }else {
                Log.d(TAG, "submit onClick: incorrect! "
                        + answerInput
                        + " does not equal "
                        + word);
                //append incorrect answer
                word = word + " (your answer: " + answerInput + ")";
                incorrectWords.add(word);
                Log.d(TAG, "submit onClick: " + word + " added to incorrectWords ArrayList");
                //maybtodo populate image view animation for 3 seconds then fade
            }
            //if answer is not valid
        }

    }


    public void launchScoreScreen(){
        Log.d(TAG, "launchScoreScreen: no more elements left in questionWords array");
        Fragment fragment = new ScoreScreenFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("correct words", correctWords);
        bundle.putStringArrayList("incorrect words", incorrectWords);
        fragment.setArguments((bundle));
        getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
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
                    + word.getWord());
            definition = word.getDefinitions().get(0).getDefinition();
            View v = getView();
            ((TextView) v.findViewById(R.id.tvQuestion)).setText("What word means \""
                    + definition + "\"");
            // Can also call for example, and image
            Log.d(TAG, "onPostExecute: Added new DefinitionResponse");
        }
    }

}
