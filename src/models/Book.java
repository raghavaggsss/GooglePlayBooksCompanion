package models;

import java.util.ArrayList;

public class Book extends Words {
    private String title;
    private String author;
    private ArrayList<Character> characters;

    // EFFECT: Create a new book object with title
    public Book(String title) {
        this.title = title;
        words = new ArrayList<>();
    }

    @Override
    public void printWords() {
        System.out.println("The words for " + title + " are:");
        super.printWords();
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void insertCharacter(Character c) {
        characters.add(c);
    }

    public void printCharacters() {
        for (Character c : characters) {
            System.out.println(c.getName());
        }
    }

    public void printBookTitle() {
        System.out.println(title);
    }




}
