package models;

import java.util.Objects;

public class Character extends Words {
    private String name;
    private Book book;

    public Character(String name) {
        super();
        this.name = name;
        //initialise book name here
    }

    @Override
    public void printWords() {
        System.out.println("The words for " + name + " are:");
        super.printWords();
    }

    public String getName() {
        return name;
    }

    public void addBook(Book b) {
        if (this.book == null || !this.book.equals(b)) {
            this.book = b;
            book.insertCharacter(this);
        }
    }

    public Book getBook() {
        return book;
    }

    @Override
    public void printBookTitle() {
        book.printBookTitle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(name, character.name) &&
                Objects.equals(book, character.getBook());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, book);
    }
}
