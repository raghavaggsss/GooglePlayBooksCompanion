package models;

import java.util.Observable;
import java.util.Observer;

public class WordAdditionObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        Word word = (Word) arg;
        System.out.println(word.getWordMeaning());
    }
}
