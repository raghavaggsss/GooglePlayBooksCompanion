package models;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.WordnetStemmer;
import models.exceptions.InvalidWordException;

import java.util.List;

public class WordPreProcess {
    POS[] pos_tags = POS.values();
    WordnetStemmer stemmer;
    private IDictionary dict;

    public WordPreProcess(IDictionary dict) {
        this.dict = dict;
        stemmer = new WordnetStemmer(dict);
    }

    public String stemmer(String word) throws InvalidWordException {
        List<String> wordStems;
        for (POS pos : pos_tags) {
            wordStems = stemmer.findStems(word, pos);
            if (wordStems.size() > 0) {
                return wordStems.get(0);
            }
        }

        throw new InvalidWordException();
    }
}


//
//    IIndexWord idxWord = dict . getIndexWord ("hello", POS.NOUN );
//    IWordID wordID = idxWord . getWordIDs ().get (0) ;
//    IWord word = dict . getWord ( wordID );
//        System .out . println ("Id = " + wordID );
//        System .out . println (" Lemma = " + word . getLemma ());
//        System .out . println (" Gloss = " + word . getSynset (). getGloss ());
//    }

