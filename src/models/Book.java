package models;

import java.util.ArrayList;

public class Book {
    private String title;
    private String author;
    private ArrayList<Word> words;
    private ArrayList<String> characters;
    private static final int longWordLength = 6;


    // EFFECT: Create a new book object with title
    public Book(String title) {
        this.title = title;
        words = new ArrayList<>();
    }

    // EFFECT: Return the words of a book
    public ArrayList<Word> getWords() {
        return words;
    }


    // MODIFIES: this
    // EFFECT: insert a word into the book
    public void insertWord(Word word) {
        words.add(word);
    }

//    public ArrayList<String> getCharacters() {
//        return characters;
//    }

    // EFFECT: return list of long words in the book
    public ArrayList<Word> getLongWords() {
        ArrayList<Word> longWords = new ArrayList<>();
        for (Word word : words) {
            if (word.getWord().length() > longWordLength) {
                longWords.add(word);
            }
        }
        return longWords;
    }

    // EFFECT: prints all the long words (helper for getLongWords)
    public void printLongWords() {
        ArrayList<Word> words = this.getLongWords();
        for (Word word: words) {
            System.out.println(word.getWordMeaning());
        }
    }




}
