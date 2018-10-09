package models;

import java.util.ArrayList;

public interface WordList {
    public ArrayList<Word> getWords();

    public void insertWord(Word word);

    public ArrayList<Word> getLongWords();
}
