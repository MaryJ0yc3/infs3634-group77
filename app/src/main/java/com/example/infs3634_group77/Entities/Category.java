package com.example.infs3634_group77.Entities;

import com.example.infs3634_group77.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Category {

    //stores word objects. is an array list? or just a list?
    String categoryName;
    String desc;
    ArrayList<String> wordsList;
    int icon;
    String bestScore;

    public Category(){}


    public Category(String categoryName, String desc, int icon, ArrayList<String> words, String bestScore) {
        this.categoryName = categoryName;
        this.desc = desc;
        this.icon = icon;
        this.wordsList = words;
        this.bestScore = bestScore;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDesc() {return desc; }

    public void setDesc(String desc) { this.desc = desc; }

    public ArrayList<String> getWords() { return wordsList; }

    public void setWordList(ArrayList<String> words) {wordsList = words;}

    public ArrayList<String> getWordList() {return wordsList;};

    public int getIcon() { return icon; }

    public void setIcon(int icon) {this.icon = icon;}

    public String getBestScore() {return bestScore;}

    public void setBestScore(Double score) {
        NumberFormat format = NumberFormat.getPercentInstance();
        if (score > Double.valueOf(bestScore)) {
            this.bestScore = format.format(score);
        }
    }

    //get categories method for the categories recycler view
    public static List<Category> getCategories(){
        List<Category> categories= new ArrayList<>();
        categories.add(new Category(
                "University", "Key words needed to get by at University",
                R.drawable.category_icon1, new ArrayList<String> (Arrays.asList(
                "Describe", "Explain", "Discuss", "Assignment", "Quiz", "Exam")),
                "0"));
        categories.add(new Category(
                "Transport", "The main forms of getting around in Sydney",
                R.drawable.category_icon2, new ArrayList <String> (Arrays.asList(
                "Train","Taxi","Bus","Ferry","Car","Truck","Aeroplane")),
                "0"));
        categories.add(new Category(
                "Shopping", "Types of shops and important vocabulary",
                R.drawable.category_icon3, new ArrayList <String> (Arrays.asList(
                "Supermarket", "Grocery", "Kiosk", "Restaurant", "Cafe", "Retail", "Convenience store")),
                "0"));
        categories.add(new Category(
                "Society", "Australian society vocabulary",
                R.drawable.category_icon4, new ArrayList <String> (Arrays.asList(
                "Compulsory", "Election", "Legislation", "Government", "Subsidy", "Tax", "Rent", "Obligation", "Regulations")),
                "0"));
        categories.add(new Category("Adjectives", "Challenging adjectives",
                R.drawable.category_icon5, new ArrayList <String> (Arrays.asList(
                "Tenacious", "Imperative", "Extraordinary", "Eloquent", "Diligent", "Innate", "Significant", "Controversial")),
                "0"));
        categories.add(new Category("Verbs", "Challenging verbs",
                R.drawable.category_icon6, new ArrayList <String> (Arrays.asList(
                "Abhor", "Oppose", "Appease", "Quarrel", "Consult", "Resolve", "Discuss", "Regret")),
                "0"));
        return categories;
    }

    public static Category getCategory(int position) {
        return getCategories().get(position);
    }

}