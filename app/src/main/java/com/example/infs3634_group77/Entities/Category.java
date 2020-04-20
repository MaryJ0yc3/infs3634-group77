package com.example.infs3634_group77.Entities;

import java.util.ArrayList;

public class Category {

    //stores word objects. is an array list? or just a list?
    String categoryName;
    String desc;
    ArrayList<Word> wordList;

    public Category(){}


    public Category(String categoryName, String desc, ArrayList<Word> wordList) {
        this.categoryName = categoryName;
        this.desc = desc;
        this.wordList = wordList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    //get categories method for the categories recycler view
    public static ArrayList<Category> getCategories(){
        ArrayList<Category> categories= new ArrayList<>();
//        categories.add(new Category("University",
//                "Key words needed to get by at University",
//                ________ need to get a list of words here. ))


        return null;
    }


}
