package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Book;
import models.Word;

import java.util.ArrayList;

public class WordsUI extends Application {

    Stage window;
    Scene home;
    Scene book;
    Scene character;

    public static void main(String[] args) {
        launch(args);
    }

    public Stage modalInit() {
        Stage modal = new Stage();
        modal.initModality(Modality.APPLICATION_MODAL);
        return modal;
    }

    public void addBook() {
        Stage addBookStage = modalInit();
        addBookStage.setTitle("Add a new book");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label titleLabel = new Label("Book Title");
        GridPane.setConstraints(titleLabel, 0, 0);

        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");
        GridPane.setConstraints(titleInput, 1, 0);

        Label authorLabel = new Label("Book Author");
        GridPane.setConstraints(authorLabel, 0, 1);

        TextField authorInput = new TextField();
        authorInput.setPromptText("Author");
        GridPane.setConstraints(authorInput, 1, 1);

        Button addBook = new Button("Add Book");
        GridPane.setConstraints(addBook, 1, 3);

        Scene s = new Scene(grid);
        grid.getChildren().addAll(titleLabel, titleInput, authorInput, authorLabel, addBook);

        addBookStage.setScene(s);
        addBookStage.showAndWait();

    }

    public void wordToMeaning(Word word) {
        Stage meaningWindow = modalInit();
        meaningWindow.setTitle(word.getWord());

        Text text = new Text(word.getMeaning());

        System.out.println(text);
        VBox meaning = new VBox(20);
        meaning.setAlignment(Pos.CENTER);
        meaning.getChildren().add(text);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            meaningWindow.close();
        });
        meaning.getChildren().add(backButton);

        Scene meaningScene = new Scene(meaning, 200, 200);
        meaningWindow.setScene(meaningScene);

        meaningWindow.showAndWait();

    }

    public Scene bookToWords(Book b) {
        VBox wordsLayout = new VBox(20);
        wordsLayout.setAlignment(Pos.CENTER);

        ArrayList<Button> buttons = new ArrayList<>();
        for (Word word : b.getWords()) {
            Button wordButton = new Button(word.getWord());
            wordButton.setOnAction(e -> wordToMeaning(word));
            //wordsLayout.getChildren().add(wordButton);
            buttons.add(wordButton);
        }
        wordsLayout.getChildren().addAll(buttons);

        book = new Scene(wordsLayout, 200, 200);
        return book;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Book theBookThief = new Book("The Book Thief");
        theBookThief.insertWord(new Word("vehement", "MEaning herer ddue"));
        theBookThief.insertWord(new Word("immoral", "not moral"));

        window = primaryStage;
        window.setOnCloseRequest(e -> window.close());

        Button bookButton = new Button(theBookThief.getBookTitle());


        VBox layout = new VBox();
        layout.getChildren().add(bookButton);

        // Add a button for adding new book
        Button addBookButton = new Button("Add a new book");
        addBookButton.setOnAction(e -> addBook());
        layout.getChildren().add(addBookButton);

        home = new Scene(layout, 200, 200);

        window.setScene(home);

        window.show();

        bookButton.setOnAction(e -> {
            window.setScene(bookToWords(theBookThief));
            window.setTitle(theBookThief.getBookTitle());
        });

    }
}
