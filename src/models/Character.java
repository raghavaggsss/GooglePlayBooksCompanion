package models;

public class Character extends Words {
    private String name;

    public Character(String name) {
        super();
        this.name = name;
    }

    @Override
    public void printWords() {
        System.out.println("The words for " + name + " are:");
        super.printWords();
    }

    public String getName() {
        return name;
    }

    public void printBookTitle() {
        System.out.println("Not known how to get this working");
    }

}
