package ui;

import models.Book;
import models.Character;
import models.POSTags;
import models.Word;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PlayingWithWords {


    public static List<String> readWordMeanings() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("inputfile.txt"));
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {

//        String filename="test.txt";
//        Path pathToFile = Paths.get(filename);
//        System.out.println(pathToFile.toAbsolutePath().toString());

        Word genial = new Word("genial", "friendly and cheerful");
        Word innocuous = new Word("innocuous", "not harmful or offensive");
        Word vehement = new Word("vehement", "showing strong feeling");
        // System.out.println(genial.getWordMeaning());

        Book theBookThief = new Book("The Book Thief");
        theBookThief.insertWord(genial);
        theBookThief.insertWord(innocuous);
        theBookThief.insertWord(vehement);

        List<String> lines = readWordMeanings();

        for (String line : lines) {
            String[] parts = line.split(":");
            theBookThief.insertWord(new Word(parts[0], parts[1]));
        }


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
        Character c = new Character("Liesel");
        c.insertWord(genial);

        c.printWords();

        //NOT COMMITTED FROM HERE
        POSTags posTags = new POSTags();
        genial.setPosTag(posTags.adj);
    }
}
