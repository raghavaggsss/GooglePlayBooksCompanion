package models;

import edu.mit.jwi.item.POS;
import models.exceptions.InvalidMeaningException;
import models.exceptions.InvalidStringException;
import models.exceptions.InvalidWordException;

import java.util.ArrayList;

public class Word {
    private String word;
    private String meaning;
    private POS posTag;
    private ArrayList<String> usages;

    public Word(String word, String meaning) throws InvalidStringException {
        if (!(word.matches("[a-zA-Z]+"))) {
            throw new InvalidWordException();
        }

        if (!(meaning.matches("([a-zA-Z]\\s?)+"))) {
            throw new InvalidMeaningException();
        }


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
        String wordMeaning = word + " : " + meaning;
        return wordMeaning;
    }

    @Override
    public String toString() {
        return this.getWord();
    }
}
