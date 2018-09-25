package ui;

import models.Book;
import models.POSTags;
import models.Word;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayingWithWords {



    public static void main(String[] args) {
        Word genial = new Word("genial", "friendly and cheerful");
        Word innocuous = new Word("innocuous", "not harmful or offensive");
        Word vehement = new Word("vehement", "showing strong feeling");
        // System.out.println(genial.getWordMeaning());

        Book theBookThief = new Book("The Book Thief");
        theBookThief.insertWord(genial);
        theBookThief.insertWord(innocuous);
        theBookThief.insertWord(vehement);


        //getLongWords(theBookThiefWords);

        Scanner user_input = new Scanner(System.in);

        System.out.print("Enter word: ");
        String new_word = user_input.next();

        System.out.print("Enter meaning: ");
        String new_meaning = user_input.next();

        theBookThief.insertWord(new Word(new_word, new_meaning));
        theBookThief.printLongWords();

        //NOT COMMITTED FROM HERE
        POSTags posTags = new POSTags();
        genial.setPosTag(posTags.adj);
    }
}
