package models;

public class Word {
    private String word;
    private String meaning;

    public Word(String word, String meaning) {
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
        String wordMeaning = word.toUpperCase() + " : " + meaning;
        return wordMeaning;
    }
}
