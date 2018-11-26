package models;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;


import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputOutput {
    public static IDictionary openDictionary() throws IOException {
        URL url = new URL("file", null, "dict");
        IDictionary dict = new Dictionary(url);
        dict.open();
        return dict;
    }


    public static List<String> readWordMeanings(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
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

    public static void saveBooks(ArrayList<Book> books) {
        try {
            FileOutputStream fos = new FileOutputStream(new File("books.ser"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.close();
        }
        catch (FileNotFoundException e1) {
            System.out.println("File Not found");
        }
        catch (IOException e2) {
            System.out.println("Messed up");
        }
    }

    public static ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("books.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            books = (ArrayList<Book>) ois.readObject();
            ois.close();
        }
        catch (FileNotFoundException e1) {
            System.out.println("File Not found");
        }
        catch (IOException e2) {
            System.out.println("Error in loading the file");
        }
        catch (ClassNotFoundException e3) {
            System.out.println("Books class has been mutated since last save");
        }
        finally {
            return books;
        }
    }

//    public static void saveJSON(Books books) {
//        Gson gson = new Gson();
//        try (Writer writer = new FileWriter("books.json")){
//            gson.toJson(books, writer);
//        }
//        catch (IOException e){
//            System.out.println(e);
//        }
//    }
//
//    public static Books loadJSON() {
//        Books books = new Books();
//        Gson gson = new Gson();
//        try(BufferedReader reader = new BufferedReader(new FileReader("books.json"))){
//            books = gson.fromJson(reader, Books.class);
//            System.out.println("HERE COMES");
//            System.out.println(books.books);
//        }
//        catch (IOException e){
//            System.out.println("DASDAS");
//        }
//        catch (JsonSyntaxException e1) {
//            System.out.println("JSON Syntax");
//        }
//        catch (JsonIOException e2) {
//            System.out.println("JSON IO");
//        }
//        finally  {
//            return books;
//        }
//    }
}
