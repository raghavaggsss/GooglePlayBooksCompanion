package models;

import java.util.ArrayList;

public abstract class Words implements WordList, WordListPrinters {
    private static final int longWordLength = 6;
    protected ArrayList<Word> words;


    public Words() {
        words = new ArrayList<>();
    }

    // EFFECT: Return the words of a book
    public ArrayList<Word> getWords() {
        return words;
    }

    public void printWords() {
        for (Word w : words) {
            System.out.println(w.getWord());
        }
    }

    // MODIFIES: this
    // EFFECT: insert a word into the book
    public void insertWord(Word word) {
        words.add(word);
    }

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
        for (Word word : words) {
            System.out.println(word.getWordMeaning());
        }
    }

    abstract public void printBookTitle();


}
