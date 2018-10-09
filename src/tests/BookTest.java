package tests;

import models.Book;
import models.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTest {
    private Book theBookThief;
    private Word genial = new Word("genial", "friendly and cheerful");
    private Word innocuous = new Word("innocuous", "not harmful or offensive");
    private Word vehement = new Word("vehement", "showing strong feeling");

    @BeforeEach
    public void setup() {
        theBookThief = new Book("The Book Thief");
    }

    // TODO: test multiple insertions
    @Test
    public void testInsertWordSingle() {
        theBookThief.insertWord(genial);
        assertEquals(1, theBookThief.getWords().size());
        assertEquals(genial, theBookThief.getWords().get(0));
    }

    @Test
    public void testInsertWordMultiple() {
        theBookThief.insertWord(genial);
        theBookThief.insertWord(innocuous);
        theBookThief.insertWord(vehement);
        assertEquals(3, theBookThief.getWords().size());
        assertTrue(theBookThief.getWords().contains(genial));
        assertTrue(theBookThief.getWords().contains(innocuous));
        assertTrue(theBookThief.getWords().contains(vehement));
    }

    @Test
    public void testGetLongWordsNoWords() {
        ArrayList<Word> longWords = theBookThief.getLongWords();
        assertEquals(0, longWords.size());
    }

    @Test
    public void testGetLongWordsNoLongWords() {
        theBookThief.insertWord(genial);
        ArrayList<Word> longWords = theBookThief.getLongWords();
        assertEquals(0, longWords.size());
    }

    @Test
    public void testGetLongWordsOnlyOneLongWord() {
        theBookThief.insertWord(innocuous);
        ArrayList<Word> longWords = theBookThief.getLongWords();
        assertEquals(1, longWords.size());
        assertEquals(innocuous, longWords.get(0));
    }

    @Test
    public void testGetLongWordsMultipleLongWordsWithoutShortWords() {
        theBookThief.insertWord(innocuous);
        theBookThief.insertWord(vehement);
        ArrayList<Word> longWords = theBookThief.getLongWords();
        assertEquals(2, longWords.size());
        assertEquals(innocuous, longWords.get(0));
        assertEquals(vehement, longWords.get(1));
    }

    @Test
    public void testGetLongWordsMultipleLongWordsWithShortWord() {
        theBookThief.insertWord(genial);
        theBookThief.insertWord(innocuous);
        theBookThief.insertWord(vehement);
        ArrayList<Word> longWords = theBookThief.getLongWords();
        assertEquals(2, longWords.size());
        assertEquals(innocuous, longWords.get(0));
        assertEquals(vehement, longWords.get(1));
    }
}
