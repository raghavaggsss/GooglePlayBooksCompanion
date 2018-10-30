package ui;

import models.Book;
import models.Character;
import models.POSTags;
import models.Word;
import models.exceptions.InvalidStringException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PlayingWithWords {


    public static List<String> readWordMeanings() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("inputfile1.txt"));
        return lines;

    }

    public static void writeWordMeanings(List<String> lines) {
        try {
            PrintWriter writer = new PrintWriter("outputfile.txt", "UTF-8");
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("DONE!!");
        }

    }

    public static void main(String[] args) {

//        String filename="test.txt";
//        Path pathToFile = Paths.get(filename);
//        System.out.println(pathToFile.toAbsolutePath().toString());


        POSTags posTags = new POSTags();
        Book theBookThief = new Book("The Book Thief");
        Character liesel = new Character("Liesel");

        HashMap<String, String> wordMeanings = new HashMap<>();
        wordMeanings.put("genial", "friendly and cheerful");
        wordMeanings.put("innocuous", "not harmful or offensive");
        wordMeanings.put("vehement", "showing strong feeling");

        try {
            for (String word : wordMeanings.keySet()) {
                theBookThief.insertWord(new Word(word, wordMeanings.get(word)));
            }

            liesel.insertWord(new Word("genial", wordMeanings.get("genial")));
        } catch (InvalidStringException e) {
            System.out.println("Invalid Word");
        }
        // System.out.println(genial.getWordMeaning());

        try {
            List<String> lines = readWordMeanings();
            for (String line : lines) {
                String[] parts = line.split(":");
                try {
                    theBookThief.insertWord(new Word(parts[0], parts[1]));
                } catch (InvalidStringException f) {
                    System.out.println("Invalid Word or Meaning");
                }
            }
        } catch (IOException e) {
            System.out.println("INPUT FILE NOT FOUND");
        }


// UNPACKER


        //getLongWords(theBookThiefWords);


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


        //theBookThief.printLongWords();


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
