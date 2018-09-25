package models;

import java.util.ArrayList;

public class Word {
    private String word;
    private String meaning;
    private String posTag;
    private ArrayList<String> usages;

    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getWordMeaning() {
        String wordMeaning = word.toUpperCase() + " : " + meaning;
        return wordMeaning;
    }



    // NOT COMMITTED FROM HERE
    public void setPosTag(String posTag) {
        this.posTag = posTag;
    }
}
