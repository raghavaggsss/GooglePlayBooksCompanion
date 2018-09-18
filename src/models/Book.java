package models;

import java.util.ArrayList;

public class Book {
    private String title;
    private String author;
    private ArrayList<Word> words;

    public Book(String title) {
        this.title = title;
        words = new ArrayList<>();
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void insertWord(Word word) {
        words.add(word);
    }


}
