package com.example.infs3634_group77.Entities;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;

public class Word {

    private String word;
    private String definition;
        @SerializedName("image_url")
    private String wordImageUrl;


    public Word(){}

    public Word(String word, String definition, String wordImageUrl){
        this.word = word;
        this.definition = definition;
        this.wordImageUrl = wordImageUrl;
    }

    public String getWord() {return word;}

    public void setWord(String word) { this.word = word;}

    public String getDefinition() { return definition; }

    public void setDefinition(String definition) { this.definition = definition;}

    public String getWordImageUrl() {return wordImageUrl;}

    public void setWordImageUrl(String wordImageUrl) { this.wordImageUrl = wordImageUrl;}

    public static ArrayList<Word> getDummyWordList(){
        ArrayList<Word> dummyWords= new ArrayList<>();
        dummyWords.add(new Word("word1", "definition1",
                "https://media.owlbot.info/dictionary/images/photo-1523262941688-" +
                        "de5d977e2399.jpeg.400x400_q85_box-38,263,582,806_crop_detail.jpg"));
        dummyWords.add(new Word("word2", "definition2", null));
        dummyWords.add(new Word("word3", "definition3", null));
        dummyWords.add(new Word("word4", "definition4", null));
        dummyWords.add(new Word("word5", "definition5", null));
        dummyWords.add(new Word("word6", "definition6", null));
        dummyWords.add(new Word("word7", "definition7", null));
        dummyWords.add(new Word("word8", "definition8", null));

        return dummyWords;

    }
}
