package ui;

import models.Book;
import models.Word;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayingWithWords {

    private static final int longWordLength = 6;

    private static void printMeaningsOfLongWords(ArrayList<Word> words) {
        for (Word word: words) {
            if (word.getWord().length() > longWordLength) {
                System.out.println(word.getWordMeaning());
            }
        }

    }

    public static void main(String[] args) {
        Word genial = new Word("genial", "friendly and cheerful");
        Word innocuous = new Word("innocuous", "not harmful or offensive");
        Word vehement = new Word("vehement", "showing strong feeling");
       // System.out.println(genial.getWordMeaning());

        Book theBookThief = new Book("The Book Thief");
        theBookThief.insertWord(genial);
        theBookThief.insertWord(innocuous);
        theBookThief.insertWord(vehement);

        ArrayList<Word> theBookThiefWords = theBookThief.getWords();

        printMeaningsOfLongWords(theBookThiefWords);

        Scanner user_input = new Scanner(System.in);

        System.out.print("Enter word: ");
        String new_word = user_input.next();

        System.out.print("Enter meaning: ");
        String new_meaning = user_input.next();

        theBookThief.insertWord(new Word(new_word, new_meaning));
        printMeaningsOfLongWords(theBookThief.getWords());

    }
}
