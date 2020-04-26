package com.example.infs3634_group77.LearningFightScore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.infs3634_group77.R;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ScoreScreenFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "com.example.infs3634_group77.MESSAGE";
    private static final String TAG = "ScoreScreenFragment";

    //Received bundle arraylists
    private ArrayList<String> correctWords;
    private ArrayList<String> incorrectWords;


    public ScoreScreenFragment() { }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            correctWords = bundle.getStringArrayList("correct words");
            incorrectWords = bundle.getStringArrayList("incorrect words");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the row layout for this fragment
        View v = inflater.inflate(R.layout.fragment_score_screen, container, false);

        //elements
        TextView tvscorePercentage = v.findViewById(R.id.scorePercentage);
        TextView tvcorrectCount = v.findViewById(R.id.tvCorrectNo);
        TextView tvincorrectCount = v.findViewById(R.id.tvIncorrectNo);
        TextView tvcorrectList = v.findViewById(R.id.tvCorrectList);
        TextView tvincorrectList = v.findViewById(R.id.tvIncorrectList);

        //calculations
        int correctCount = correctWords.size();
        int incorrectCount = incorrectWords.size();
        int totalQuestionsAttempted = incorrectCount  + correctCount;
        double scoreDecimal;
        double scorePercentage;
        if(correctCount>0 && totalQuestionsAttempted>0){
            scoreDecimal = correctCount/(totalQuestionsAttempted);
            scorePercentage = scoreDecimal * 100;
        }else{
            scorePercentage = 0;
        }


        //formatting
        String fullCorrectListFormatted = "";
        for(int i = 0; i < correctWords.size(); i++){
            String mCorrectWord = correctWords.get(i);
            fullCorrectListFormatted = fullCorrectListFormatted
                    + "\n"
                    + mCorrectWord;
        }

        String fullIncorrectListFormatted = "";
        for(int i = 0; i < incorrectWords.size() ; i++){
            String mIncorrectWord = incorrectWords.get(i);
            fullIncorrectListFormatted = fullIncorrectListFormatted
                    + "\n"
                    + mIncorrectWord;
        }


        //setText
        tvscorePercentage.setText("Score: " + scorePercentage + "%");
        tvcorrectCount.setText("Correct: " + String.valueOf(correctCount));
        tvincorrectCount.setText("Incorrect: " + String.valueOf(incorrectCount));
        tvcorrectList.setText(fullCorrectListFormatted);
        tvincorrectList.setText(fullIncorrectListFormatted);


        return v;
    }
}