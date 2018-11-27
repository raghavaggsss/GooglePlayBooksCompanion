package models;

import edu.mit.jwi.item.POS;
import models.exceptions.InvalidMeaningException;
import models.exceptions.InvalidStringException;
import models.exceptions.InvalidWordException;

import java.io.Serializable;
import java.util.ArrayList;

public class Word implements WordTree, Serializable {
    private String word;
    private String meaning;
    private POS posTag;
    private ArrayList<String> usages;

    public POS getPosTag() {
        return posTag;
    }

    public void setPosTag(POS posTag) {
        this.posTag = posTag;
    }

    public Word(String word, String meaning) throws InvalidStringException {
        if (!(word.matches("[a-zA-Z]+"))) {
            throw new InvalidWordException();
        }

        this.word = word;
        this.meaning = formatMeaning(meaning);
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

    public String formatMeaning(String meaning) {
        StringBuilder sb = new StringBuilder(meaning);

        int i = 0;
        while ((i = sb.indexOf(" ", i + 30)) != -1) {
            sb.replace(i, i + 1, "\n");
        }
        return sb.toString();
    }
}
