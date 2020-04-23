package com.example.infs3634_group77.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// Gets a definition object from JSON object
public class Definition {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("example")
    @Expose
    private String example;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("emoji")
    @Expose
    private Object emoji;

    // Don't know if need to set word within the Definition yet
    /*private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getEmoji() {
        return emoji;
    }

    public void setEmoji(Object emoji) {
        this.emoji = emoji;
    }

}