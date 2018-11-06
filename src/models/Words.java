package models;

import java.util.HashSet;

public abstract class Words implements WordList {
    protected HashSet<Word> words;
    protected WordListPrinters wordListPrinters;


    public Words() {
        words = new HashSet<>();
        wordListPrinters = new WordListPrinters(words);
    }

    // EFFECT: Return the words of a book
    public HashSet<Word> getWords() {
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


    abstract public void printBookTitle();

    public void printLongWords() {
        wordListPrinters.printLongWords();
    }

}
