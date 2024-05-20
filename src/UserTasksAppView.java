import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class UserTasksAppView extends Pane {

    private ListView<String> userTasks;

    private Label titleLabel;

    private UserTasksButtonPane buttons;


    /**
     * Getters and Setters
     */

    public ListView<String> getUserTasks() {
        return userTasks;
    }

    public UserTasksButtonPane getButtons() {
        return buttons;
    }

    public void setUserTasks(ArrayList<String> tasksToSet) {
        userTasks.setItems(FXCollections.observableArrayList(tasksToSet));
    }

    public UserTasksAppView(){

        // Create the buttons
        buttons = new UserTasksButtonPane();
        buttons.relocate(0,435);


        // Create the ListView which will contain all the user's tasks
        userTasks = new ListView<>();
        userTasks.relocate(10,45);
        userTasks.setPrefSize(275,375);

        // Create the title Label
        titleLabel = new Label("My Tasks");
        titleLabel.setFont(new Font("Arial", 24));
        titleLabel.relocate(100,10);

        getChildren().addAll(buttons, titleLabel, userTasks);

        setPrefSize(300,500);
    }
}