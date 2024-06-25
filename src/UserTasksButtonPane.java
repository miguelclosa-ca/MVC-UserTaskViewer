import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.*;
import java.util.ArrayList;

public class UserTasksButtonPane extends Pane {

    private Button addButton, removeButton;

    // Create buttons to save and load
    private Button saveButton, loadButton;

    // Create an ArrayList of Strings. This is so that the program can load a translation file.
    private ArrayList<String> translations;

    // Create a button to change the language
    private Button trButton;

    /**
     * Getters and Setters
     */

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    public ArrayList<String> getTranslations() {
        return translations;
    }

    public Button getTrButton() {
        return trButton;
    }

    public UserTasksButtonPane() throws IOException {

        Pane buttonPane = new Pane();
        translations = loadTranslations("t_en");

        // Define the attributes of the [Add Task] Button:
//        addButton = new Button("Add Task");
        addButton = new Button(translations.get(0));
        addButton.setPrefSize(100, 50);
        addButton.relocate(35,0);


        // Define the attributes of the [Remove Task] Button:
//        removeButton = new Button("Remove Task");
        removeButton = new Button(translations.get(1));
        removeButton.setPrefSize(100,50);
        removeButton.relocate(165,0);

        // Define the attributes of the [Save...] Button:
//        saveButton = new Button("Save...");
        saveButton = new Button(translations.get(2));
        saveButton.setPrefSize(100,50);
        saveButton.relocate(35,55);

        // Define the attributes of the [Load...] Button:
//        loadButton = new Button("Load...");
        loadButton = new Button(translations.get(3));
        loadButton.setPrefSize(100,50);
        loadButton.relocate(165,55);

        // Define the attributes of the [Translation] Button:
        trButton = new Button("a");
        trButton.setPrefSize(20,20);
        trButton.relocate(140, 40);

        buttonPane.getChildren().addAll(addButton,removeButton, saveButton, loadButton, trButton);

        getChildren().addAll(buttonPane);

    }

    public ArrayList<String> loadTranslations(String filename) throws IOException {
        ArrayList<String> loadedTranslations = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(filename));
        int num = Integer.parseInt(in.readLine());

        for (int i = 0 ; i < num; i++){
            loadedTranslations.add(in.readLine());
        } // End for

        in.close();

        return loadedTranslations;

    }

}
