package models;

import java.util.HashSet;

public interface WordList {
    public HashSet<Word> getWords();

    public void insertWord(Word word);

}
