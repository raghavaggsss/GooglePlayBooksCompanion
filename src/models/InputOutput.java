package models;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
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
}
