package ui;


import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.POS;
import javafx.util.Pair;
import models.*;
import models.Character;
import models.exceptions.InvalidStringException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static models.InputOutput.*;

public class PlayingWithWords {
//TODO: singleton design pattern

    public static void main(String[] args) throws Exception {

        Book theBookThief = new Book("The Book Thief");

        try {
            IDictionary dict = openDictionary();
            WordPreProcess wordPreProcess = new WordPreProcess(dict);

            try {
                List<String> lines = readWordMeanings(" ");
                for (String line : lines) {
                    String[] parts = line.split(":");
                    String word = parts[0];
                    String meaning = parts[1];
                    try {
                        Pair<String, POS> pair = wordPreProcess.stemmer(word);
                        String wordStem = pair.getKey();
                        POS wordPOS = pair.getValue();
                        theBookThief.insertWord(new Word(wordStem, meaning));
                    } catch (InvalidStringException f) {
                        System.out.println("Invalid Word or Meaning");
                    }
                }
            } catch (IOException e) {
                System.out.println("INPUT FILE NOT FOUND");
            }

        }

        catch (IOException e) {
            System.out.println("WORDNET NOT FOUND");
        }



//        String filename="test.txt";
//        Path pathToFile = Paths.get(filename);
//        System.out.println(pathToFile.toAbsolutePath().toString());


        POSTags posTags = new POSTags();
        Character liesel = new Character("Liesel");

        HashMap<String, String> wordMeanings = new HashMap<>();
        wordMeanings.put("genial", "friendly and cheerful");
        wordMeanings.put("innocuous", "not harmful or offensive");
        wordMeanings.put("vehement", "showing strong feeling");

        for (String word : wordMeanings.keySet()) {
            try {
                theBookThief.insertWord(new Word(word, wordMeanings.get(word)));
            } catch (InvalidStringException e) {
                System.out.println("Invalid Word");
            }
        }
        // System.out.println(genial.getWordMeaning());
        //liesel.insertWord(new Word("genial", wordMeanings.get("genial")));




// UNPACKER




        //USER INPUT STUFF
//        Scanner user_input = new Scanner(System.in);
//
//        System.out.print("Enter word: ");
//        String new_word = user_input.next();
//
//        System.out.print("Enter meaning: ");
//        String new_meaning = user_input.next();
//
//        theBookThief.insertWord(new Word(new_word, new_meaning));

//        System.out.println(theBookThief.getWords().size());
//        theBookThief.printLongWords();


        liesel.printWords();
        theBookThief.insertCharacter(liesel);


        System.out.println("Liesel's book:");
        liesel.printBookTitle();


        //NOT COMMITTED FROM HERE


// PACKER
        HashSet<Word> words = theBookThief.getWords();
        List<String> linesToWrite = new ArrayList<>();
        for (Word w : words) {
            linesToWrite.add(w.getWordMeaning());
        }
        writeWordMeanings(linesToWrite);
    }


}
