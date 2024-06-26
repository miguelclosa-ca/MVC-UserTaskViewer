import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class UserTasksApp extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {


        // Define a List of Tasks, a Pane and a View
        ArrayList<String> userTasks = new ArrayList<>();
        Pane aPane = new Pane();
        UserTasksAppView view = new UserTasksAppView();

    // Create an ArrayList of Strings. This is so that the program can load a translation file.
        ArrayList<String> translations = loadTranslations("t_en");
//        boolean languageToggle = false;


        aPane.getChildren().addAll(view);

        // Tweak the GUI window attributes
        primaryStage.setResizable(false);
        primaryStage.setTitle("My Tasks");
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        // When the [Add Task] button is pressed, add a task to the list
        view.getButtons().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String task = javax.swing.JOptionPane.showInputDialog("Enter a Task:");
                if (task != null){
                    userTasks.add(task);
                    view.getUserTasks().setItems(FXCollections.observableArrayList(userTasks));
                    view.getButtons().getSaveButton().setDisable(false);
                } // End if

            }
        });

        // When any item is clicked on in the list of tasks
        view.getUserTasks().setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!view.getUserTasks().getSelectionModel().getSelectedItems().isEmpty()){
                    view.getButtons().getRemoveButton().setDisable(view.getUserTasks().getSelectionModel().getSelectedIndex() < 0);
                } // End if
            }
        });
        // When the [Remove Task] button is pressed, remove the selected item in the list
        view.getButtons().getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                userTasks.remove(view.getUserTasks().getSelectionModel().getSelectedItem());
                view.getUserTasks().setItems(FXCollections.observableArrayList(userTasks));
                view.getButtons().getRemoveButton().setDisable(true);

                // If the task list is now empty, remove the ability to save
                if (userTasks.isEmpty()){
                    view.getButtons().getSaveButton().setDisable(true);
                } // End if
            }
        });


        // When the [Save...] button is pressed,
        view.getButtons().getSaveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Save the file
                try {
                    saveToFile(userTasks);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        // When the [Load...] button is pressed,
        view.getButtons().getLoadButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // First check if the task list is empty
                // If it isn't, ask the user if they would like to save their tasks

                if (!userTasks.isEmpty()){
                    int dialogue = javax.swing.JOptionPane.showConfirmDialog(null, "Save current tasks? ");
                    if (dialogue == JOptionPane.YES_OPTION){
                        try {
                            saveToFile(userTasks);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } // End try
                    } // End if
                } // End if


                // Clear the current list of tasks
                userTasks.clear();


                // Ask for a filename
                String filename = javax.swing.JOptionPane.showInputDialog("Enter a filename: ");
                if (filename != null) {
                    // If the file does exist, load the file
                    if (fileExists(filename)) {
                        userTasks.addAll(loadFromFile(filename));
                        view.getUserTasks().setItems(FXCollections.observableArrayList(userTasks));
                        if (!userTasks.isEmpty()) {
                            view.getButtons().getSaveButton().setDisable(false);
                        } // End if

                        primaryStage.setTitle("My Tasks - " + '"' + filename + '"');

                        // Otherwise do not load the file and show a message to the user
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, '"' + filename + '"' + " does not exist!");
                    } // End if
                } // End if

            }
        });


        view.getButtons().getTrButton().setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent actionEvent) {


                // If the Translation button displays [a],
                if (view.getButtons().getTrButton().getText().equals("a")){
                    try{

                        // Change the language of the buttons
                        view.update(view, loadTranslations("t_jp"));
                        view.getButtons().getTrButton().setText("あ");

                    }catch (IOException e){
                        throw new RuntimeException(e);
                    } // End try


                    // Otherwise if the Translation button displays anything else,
                }else{
                    try {

                        // Revert back to English
                        view.update(view, loadTranslations("t_en"));
                        view.getButtons().getTrButton().setText("a");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } // End try

                } // End if
            }
        });
    } // void start(Stage)



    /**
     * Take a given ArrayList<String> and save the list to a file.
     * @param tasks Given ArrayList<String> to save
     * @return
     * @throws IOException
     */
    public boolean saveToFile(ArrayList<String> tasks) throws IOException{

        String filename = javax.swing.JOptionPane.showInputDialog("Enter a filename: ");

        try {

            // Create an ObjectOutputStream, write the list of tasks and close the Stream.
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(tasks);
            out.close();
        } catch (IOException | NullPointerException e) {
            return false;
        } // End try

        return true;
    } // boolean saveToFile(ArrayList<String>, String)

    /**
     * Load a file, and parse the object into an ArrayList<String> object.
     * @param filename The file to load
     * @return The ArrayList<String> object
     */
    public ArrayList<String> loadFromFile(String filename){
        ArrayList<String> loadedTasks;

        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            loadedTasks = (ArrayList<String>) in.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return loadedTasks;
    } // ArrayList<String> loadFromFile(String)

    /**
     * Check if a file exists in directory.
     * @param filename A file to check
     * @return If the file exists (true) or doesn't (false)
     */
    public boolean fileExists(String filename){
        File tmp = new File(filename);
        return tmp.exists();
    } // boolean fileExists(String)


    /**
     * Load a file of translations for the buttons.
     * @param filename File name to load
     * @return ArrayList of translations
     * @throws IOException
     */
    public ArrayList<String> loadTranslations(String filename) throws IOException {
        ArrayList<String> loadedTranslations = new ArrayList<>();

        // Open the file, read the first line as it contains the number of lines to read
        BufferedReader in = new BufferedReader(new FileReader(filename));
        int num = Integer.parseInt(in.readLine());

        // Read the file
        for (int i = 0 ; i < num; i++){
            loadedTranslations.add(in.readLine());
        } // End for

        // Close the file
        in.close();

        return loadedTranslations;

    } // ArrayList<String> loadTranslations(String)


}
