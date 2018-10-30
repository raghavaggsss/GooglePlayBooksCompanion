package models;

import java.util.ArrayList;
import java.util.HashSet;

public interface WordList {
    public HashSet<Word> getWords();

    public void insertWord(Word word);

    public ArrayList<Word> getLongWords();
}
