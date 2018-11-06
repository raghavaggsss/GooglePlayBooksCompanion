package models;


import java.util.ArrayList;
import java.util.Set;

public class WordListPrinters {
    private static final int longWordLength = 6;
    private Set<Word> words;

    public WordListPrinters(Set<Word> words) {
        this.words = words;
    }

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
}
