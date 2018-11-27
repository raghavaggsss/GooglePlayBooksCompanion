package ui;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import models.*;
import models.Button;

import models.exceptions.InvalidBookTitleException;
import models.exceptions.InvalidStringException;
import models.exceptions.NoMeaningFound;

import javax.imageio.IIOException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static models.InputOutput.*;

public class WordsUI extends Application {

    POS[] pos_tags = POS.values();

    ArrayList<Book> books;
    private Stage window;
    private Scene home;
    private Scene book;
    private Scene character;

    public static void main(String[] args) {
        launch(args);
    }

    public Stage modalInit() {
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);
        return modal;
    }

    public void messageBox(String message) {
        Stage meaningWindow = modalInit();

        //meaningWindow.setTitle(word.getWord());

        Text text = new Text(message);

        VBox meaning = new VBox(20);
        meaning.setPadding(new Insets(20,20,20,20));
        meaning.setAlignment(Pos.CENTER);
        meaning.getChildren().add(text);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            meaningWindow.close();
        });
        meaning.getChildren().add(backButton);

        Scene meaningScene = new Scene(meaning);
        meaningWindow.setScene(meaningScene);

        meaningWindow.showAndWait();

    }

    public void addWord(Book book) {
        Stage addWordStage = modalInit();
        addWordStage.setTitle("Add a new word to "+ book.getBookTitle());

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Scene s = new Scene(grid);

        Label wordLabel = new Label("Word");
        GridPane.setConstraints(wordLabel, 0, 0);

        TextField wordInput = new TextField();
        wordInput.setPromptText("Word");
        GridPane.setConstraints(wordInput, 1, 0);

        Label meaningLabel = new Label("Meaning");
        GridPane.setConstraints(meaningLabel, 0, 1);

        TextField meaningInput = new TextField();
        meaningInput.setPromptText("Meaning");
        GridPane.setConstraints(meaningInput, 1, 1);

        Button addWord = new Button("Add Word");
        addWord.setOnAction(e -> {
            try {
                book.insertWord(new Word(wordInput.getText() ,meaningInput.getText()));
                messageBox(wordInput.getText() + " successfully added!");
            }
            catch (InvalidStringException e1) {
                messageBox("Invalid Word. Please try again");
                System.out.println("Invalid Word");
            }
        });

        GridPane.setConstraints(addWord, 1, 3);

        grid.getChildren().addAll(wordLabel, wordInput, meaningInput, meaningLabel, addWord);

        addWordStage.setScene(s);
        addWordStage.showAndWait();
        refreshHome();
    }

    public void addBook() {
        Stage addBookStage = modalInit();
        addBookStage.setTitle("Add a new book");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Scene s = new Scene(grid);

        Label titleLabel = new Label("Book Title");
        GridPane.setConstraints(titleLabel, 0, 0);

        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");
        GridPane.setConstraints(titleInput, 1, 0);

        Label authorLabel = new Label("Book Author");
        GridPane.setConstraints(authorLabel, 0, 1);

        TextField authorInput = new TextField();
        authorInput.setPromptText("Optional");
        GridPane.setConstraints(authorInput, 1, 1);

        Label playBookDocLabel = new Label("Play Book");
        GridPane.setConstraints(playBookDocLabel, 0, 2);

        TextField playBookDocInput = new TextField();
        playBookDocInput.setPromptText("Optional");
        GridPane.setConstraints(playBookDocInput,1,2 );



        Button addBook = new Button("Add Book");
        // TODO: Popup for bookAdditionResult. Currently on the console.
        addBook.setOnAction(e -> {
            Label bookAdditionResult = new Label();
            try {
                Book inputBook = new Book(titleInput.getText(), authorInput.getText());
                if (!books.contains(inputBook)) {
                    books.add(inputBook);

                    String playBooksDoc = playBookDocInput.getText();
                    if (!playBooksDoc.equals("")) {
                        loadGooglePlayBookData(playBooksDoc);
                        String playBooksTxt = playBooksDoc.split("\\.")[0] + ".txt";

                        try {
                            IDictionary dict = openDictionary();
                            WordPreProcess wordPreProcess = new WordPreProcess(dict);

                            try {
                                List<String> lines = readWordMeanings("PlayBooks/"+ playBooksTxt);
                                for (String line : lines) {
                                    String[] parts = line.split(":");
                                    String word = parts[0];
                                    //String meaning = parts[1];
                                    try {
                                        Pair<String, POS> pair = wordPreProcess.stemmer(word);
                                        String wordStem = pair.getKey();
                                        POS wordPOS = pair.getValue();

                                        //Find meaning here
                                        try {
                                            String meaning = meaningFinder(dict, word, wordPOS);

                                            Word newWord = new Word(wordStem, meaning);
                                            newWord.setPosTag(wordPOS);

                                            inputBook.insertWord(newWord);
                                        }
                                        catch (NoMeaningFound np) {
                                            System.out.println("No meaning found for " + word);
                                        }
                                    } catch (InvalidStringException f) {
                                        System.out.println("Invalid Word or Meaning");
                                    }
                                }
                            } catch (IOException f) {
                                System.out.println("INPUT FILE NOT FOUND");
                            }

                        }

                        catch (IOException g) {
                            System.out.println("WORDNET NOT FOUND");
                        }
                    }

                    bookAdditionResult.setText(titleInput.getText() + " added successfully!");
                } else {
                    bookAdditionResult.setText(titleInput.getText() + " already exists!");
                }
            } catch (InvalidBookTitleException f) {
                bookAdditionResult.setText("Invalid Book Title");
            } finally {
                messageBox(bookAdditionResult.getText());
                System.out.println(bookAdditionResult.getText());
            }

        });
        GridPane.setConstraints(addBook, 1, 3);

        grid.getChildren().addAll(titleLabel, titleInput, authorInput, authorLabel,
                addBook, playBookDocInput, playBookDocLabel);

        addBookStage.setScene(s);
        addBookStage.showAndWait();
        refreshHome();
    }


    public void wordToMeaning(Word word) {
        Stage meaningWindow = modalInit();
        meaningWindow.setTitle(word.getWord());

        Text text = new Text(word.getMeaning());

        VBox meaning = new VBox(20);
        meaning.setPadding(new Insets(20,20,20,20));
        meaning.setAlignment(Pos.CENTER);
        meaning.getChildren().add(text);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            meaningWindow.close();
        });
        meaning.getChildren().add(backButton);

        Scene meaningScene = new Scene(meaning);
        meaningWindow.setScene(meaningScene);

        meaningWindow.showAndWait();

    }

    public Scene bookToWords(Book b) {
        VBox wordsLayout = new VBox(20);
        wordsLayout.setAlignment(Pos.CENTER);
        ////
        ListView<Word> listView = new ListView<>();

//        ArrayList<Button> buttons = new ArrayList<>();
//        for (Word word : b.getWords()) {
//            Button wordButton = new Button(word.getWord());
//            wordButton.setOnAction(e -> wordToMeaning(word));
//            //wordsLayout.getChildren().add(wordButton);
//            buttons.add(wordButton);
//        }
        //wordsLayout.getChildren().addAll(buttons);

        /////
        listView.getItems().addAll(b.getWords());
        wordsLayout.getChildren().add(listView);

        listView.setOnMouseClicked(e -> wordToMeaning((listView.getSelectionModel().getSelectedItems().get(0))));

        book = new Scene(wordsLayout, 200, 200);
        return book;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        books = loadBooks();

        Book theBookThief = new Book("The Book Thief");


        theBookThief.insertWord(new Word("vehement", "YO testing"));
        theBookThief.insertWord(new Word("immoral", "not moral"));
        //books.add(theBookThief);

        Book book2 = new Book("Book II");
        //books.add(book2);

        window = primaryStage;
        window.setOnCloseRequest(e -> {
            saveBooks(books);
            window.close();});

        refreshHome();
        window.show();

    }

    public TreeItem<WordTree> makeBranch(WordTree book, TreeItem<WordTree> parent) {
        TreeItem<WordTree> item = new TreeItem<>(book);
        parent.getChildren().add(item);
        return item;
    }

    public static void loadGooglePlayBookData(String filename){
        try {
        Process p = Runtime.getRuntime().exec("python3 PlayBooks/googlePlayBooks.py "+ filename); }
        catch (IOException e) {
            System.out.println("Problem with loading file");
        }
    }

    public VBox homeLayoutBuilder() {
        VBox layout = new VBox();

        Menu menu = new Menu("Books");
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);
        MenuItem addBook = new MenuItem("Add Book");
        addBook.setOnAction(e -> addBook());
        menu.getItems().add(addBook);
        MenuItem removeBook = new MenuItem("Remove Book");
        //TODO: Implement remove book and call here
        removeBook.setOnAction(e -> {
        });
        menu.getItems().add(removeBook);

        layout.getChildren().add(menuBar);

        TreeItem<WordTree> root = new TreeItem<>();
        TreeView<WordTree> bookTree = new TreeView<>(root);
        bookTree.setShowRoot(false);


        for (Book book : books) {
            TreeItem<WordTree> bookItem = makeBranch(book, root);

            ImageView ig = new ImageView("book_small.png");
            bookItem.setGraphic(ig);

            for (Word word : book.getWords()) {
                TreeItem<WordTree> wordItem = makeBranch(word, bookItem);
            }
        }

        bookTree.getSelectionModel().selectedItemProperty().addListener(
                ((v, oldValue, newValue) ->
                {
                    WordTree wt = newValue.getValue();
                    if (wt instanceof Word) {
                        wordToMeaning((Word) wt);
                    }

                    else if (wt instanceof Book) {
                        addWord((Book) wt);
                    }
                })
        );

        layout.getChildren().add(bookTree);
        return layout;
    }

    public void refreshHome() {
        home = new Scene(homeLayoutBuilder());
        home.getStylesheets().add("Garage.css");
        window.setScene(home);
    }

    public String meaningFinder(IDictionary dict, String word, POS wordPOS) throws NoMeaningFound {
        String meaning = "";

        try {
            IIndexWord idxWord = dict.getIndexWord(word, wordPOS);
            IWordID wordID = idxWord.getWordIDs().get(0);
            meaning = dict.getWord(wordID).getSynset().getGloss();
        } catch (NullPointerException e) {
            for (POS pos: pos_tags) {
                try {
                    IIndexWord idxWord = dict.getIndexWord(word, pos);
                    IWordID wordID = idxWord.getWordIDs().get(0);
                    meaning = dict.getWord(wordID).getSynset().getGloss();
                    break;
                }
                catch (NullPointerException f) {
                }
            }
        }
        finally {
            if (meaning.equals("")) {
                throw new NoMeaningFound();
            }
            return meaning;
        }

    }
}
