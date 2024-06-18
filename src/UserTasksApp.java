import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class UserTasksApp extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {


        // Define a List of Tasks, a Pane and a View
        ArrayList<String> userTasks = new ArrayList<>();
        Pane aPane = new Pane();
        UserTasksAppView view = new UserTasksAppView();

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


        view.getButtons().getSaveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String filename = javax.swing.JOptionPane.showInputDialog("Enter a filename: ");

                try {
                    saveToFile(userTasks, filename);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });

        view.getButtons().getLoadButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                // Clear the current list of tasks
                userTasks.clear();
                // Ask for a filename
                String filename = javax.swing.JOptionPane.showInputDialog("Enter filename: ");
                userTasks.addAll(loadFromFile(filename));
                view.getUserTasks().setItems(FXCollections.observableArrayList(userTasks));
                if (!userTasks.isEmpty()){
                    view.getButtons().getSaveButton().setDisable(false);
                } // End if


            }
        });



    }

    public boolean saveToFile(ArrayList<String> tasks, String filename) throws IOException{
        try {

            // Create an ObjectOutputStream, write the list of tasks and close the Stream.
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(tasks);
            out.close();
        } catch (IOException e) {
            return false;
        } // End try

        return true;
    } // boolean saveToFile(ArrayList<String>, String)

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
    }


}
