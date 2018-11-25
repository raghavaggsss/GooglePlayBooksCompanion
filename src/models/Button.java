package models;

import javafx.scene.Node;

public class Button extends javafx.scene.control.Button {


    public Button() {
    }

    public Button(String text) {
        super(text);
    }

    public Button(String text, Node graphic) {
        super(text, graphic);
    }

    @Override
    public String toString() {
        return this.getText();
    }
}