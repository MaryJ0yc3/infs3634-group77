
package com.example.infs3634_group77.Entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Gets a DefinitionResponse object from the JSON object
public class DefinitionResponse {

    @SerializedName("definitions")
    @Expose
    private List<Definition> definitions = null;
    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("pronunciation")
    @Expose
    private String pronunciation;
    // Definition that will store the first Definition to show in WordListFragment for learning
    private String firstDefinition;

    public String getFirstDefinition() {
        return firstDefinition;
    }

    public void setFirstDefinition(String definition) {
        firstDefinition = definition;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

}
