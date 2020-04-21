package com.example.infs3634_group77.Entities;

import com.example.infs3634_group77.R;

import java.util.ArrayList;

public class Category {

    //stores word objects. is an array list? or just a list?
    String categoryName;
    String desc;
    ArrayList<Word> wordList;
    int icon;

    public Category(){}


    public Category(String categoryName, String desc, int icon, ArrayList<Word> wordList) {
        this.categoryName = categoryName;
        this.desc = desc;
        this.icon = icon;
        this.wordList = wordList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDesc() {return desc; }

    public void setDesc(String desc) { this.desc = desc; }

    public ArrayList<Word> getWordList() { return wordList; }

    public void setWordList(ArrayList<Word> wordList) {this.wordList = wordList;}

    public int getIcon() { return icon; }

    public void setIcon(int icon) {this.icon = icon;}

    //get categories method for the categories recycler view
    public static ArrayList<Category> getCategories(){
        ArrayList<Category> categories= new ArrayList<>();
        categories.add(new Category("University","Key words needed to get by at University",
                R.drawable.category_icon1, Word.getDummyWordList()));
        categories.add(new Category("Transport", "The main forms of getting around in Sydney",
                R.drawable.category_icon2, Word.getDummyWordList()));
        categories.add(new Category("Shopping", "Types of shops and important vocabulary",
                R.drawable.category_icon3, Word.getDummyWordList()));
        categories.add(new Category("Society", "Australian society vocabulary",
                R.drawable.category_icon4, Word.getDummyWordList()));
        categories.add(new Category("Expanding vocabulary: Adjectives", "Challenging adjectives",
                R.drawable.category_icon5, Word.getDummyWordList()));
        categories.add(new Category("Expanding vocabulary: Verbs", "Challenging verbs",
                R.drawable.category_icon6, Word.getDummyWordList()));



        return categories;
    }


}
