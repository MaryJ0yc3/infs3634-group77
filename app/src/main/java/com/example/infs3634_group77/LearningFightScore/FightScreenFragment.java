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
    private int counter = 0;
    private TextView question;
    private Button submit;
    private Button skip;
    private Button help;
    private TextInputLayout til_answer;
    private EditText editText_answer;

    //bundle Arraylists
    private ArrayList<String> correctWords = new ArrayList<>();
    private ArrayList<String> incorrectWords = new ArrayList<>();
    private ArrayList<String> tempSkipWords = new ArrayList<>();
    private ArrayList<String> finalSkipWords = new ArrayList<>();


    //PSEUDOCODE


    //optional image view (if null url, set not visible?)
    //use tab layout to insert answer. Once finished typing, hit submit (has if else statements)
        //Each time it's correct,  add to "correctWords" array list, happy animation
        //Each time it's incorrect, add to "wrongWords" array list, sad animation
    //if they have three incorrect words, launch score screen and display array lists with results
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
        TextInputLayout til_answer = v.findViewById(R.id.til_answer);
        EditText editText_answer = v.findViewById(R.id.editText_answer);
        Button help = v.findViewById(R.id.help);

        int originalQListSize = questionWords.size();



        //score screen bundle components



        //set onClick listener for submit.
        // 1) check validity and add to correct, incorrect or skip arraylist
        // 2) populate the next question by using counter

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "submit onClick: start");
                answerInput = editText_answer.getText().toString().trim();
                boolean checkValidity = validateAnswer(answerInput.toLowerCase());
                Log.d(TAG, "submit onClick: is '" + answerInput + "' valid? " + checkValidity);
                if(checkValidity == true){
                    Log.d(TAG, "submit onClick: check if word is correct or incorrect");
                    if(answerInput.equals(word.toLowerCase().trim())){
                        Log.d(TAG, "submit onClick: Correct! "
                                        + answerInput
                                        + " equals "
                                        + word);
                        correctWords.add(word);
                        Log.d(TAG, "submit onClick: " + word + " added to correctWords ArrayList");

                        //todo populate image view animation for 3 seconds then fade

                    }else{
                        Log.d(TAG, "submit onClick: incorrect! "
                                + answerInput
                                + " does not equal "
                                + word);
                        //append incorrect answer
                        word = word + " (your answer: " + answerInput + ")";
                        incorrectWords.add(word);
                        Log.d(TAG, "submit onClick: " + word + " added to incorrectWords ArrayList");

                        //todo populate image view animation for 3 seconds then fade
                    }
                }else{
                    Toast.makeText(getActivity(),
                            "Aww, there's been an error. Your answer is not valid.",
                            Toast.LENGTH_LONG).show();
                }



                //populate question tv with next word
                if(counter < questionWords.size() - 1){
                    Log.d(TAG, "submit onClick: counter is at element no. "
                            + counter
                            + "/"
                            + questionWords.size());
                    counter ++;
                    Log.d(TAG, "submit onClick: update counter to " + counter);
                    word = questionWords.get(counter);
                    Log.d(TAG, "submit onClick: new word is " + word);
                    //populate the next question
                    new FightScreenFragment.GetDefinitionTask().execute();
                    //clear editText
                    editText_answer.setText("");
                } else if (counter == questionWords.size()- 1){
                    Log.d(TAG, "submit onClick: no more elements left in questionWords array");
                    if(tempSkipWords.size()==0){
                        for(int i = 0; i < tempSkipWords.size() - 1; i++){
                            //add all skip words to questionWords
                            Log.d(TAG, "submit onClick: adding to questionWords " + tempSkipWords.get(i));
                            questionWords.add(tempSkipWords.get(i));
                            Log.d(TAG, "submit onClick: finished add to questionWords " + tempSkipWords.get(i));
                            //delete all from Skip words
                        }

                    }else{
                        //launch result screen
                        Log.d(TAG, "final submit Click: open Fight Button");
                        Fragment fragment = new ScoreScreenFragment();
                        Bundle bundle = new Bundle();
                        bundle.putStringArrayList("correct words", correctWords);
                        bundle.putStringArrayList("incorrect words", incorrectWords);
                        bundle.putStringArrayList("final skip words", incorrectWords);
                        fragment.setArguments((bundle));
                        getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();

                    }
                }

                //set animation to score screen if time

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < originalQListSize - 1 ){
                    tempSkipWords.add(word);
                    //tempSkipWords will be added to questionWords array original questions are cycled through
                    Log.d(TAG, "skip onClick: counter is at element no. "
                            + counter
                            + "/"
                            + questionWords.size());
                    counter ++;
                    Log.d(TAG, "skip onClick: update counter to " + counter);
                    word = questionWords.get(counter);
                    Log.d(TAG, "skip onClick: new word is " + word);
                    //populate the next question
                    new FightScreenFragment.GetDefinitionTask().execute();
                    //clear editText
                    editText_answer.setText("");
                    Log.d(TAG, "skip onClick: successfully skipped");
                } else if (counter == questionWords.size() - 1) {
                    //no more words left to skip
                    Log.d(TAG, "skip onClick: no more elements left in questionWords array");
                    Fragment fragment = new ScoreScreenFragment();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("correct words", correctWords);
                    bundle.putStringArrayList("incorrect words", incorrectWords);
                    bundle.putStringArrayList("final skip words", incorrectWords);
                    fragment.setArguments((bundle));
                    getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
                } else{
                    finalSkipWords.add(word);
                    //finalSkipWords to be displayed in score screen
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

                FancyShowCaseQueue mQueue = new FancyShowCaseQueue()
                        .add(fancyShowCaseView1)
                        .add(fancyShowCaseView2);

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
                    "Aww, let's not submit an empty answer! Skip if you're unsure.",
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
