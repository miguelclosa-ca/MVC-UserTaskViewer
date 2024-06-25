import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserTasksAppView extends Pane implements UserTasksView{

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

    public UserTasksAppView() throws IOException {

        // Create the buttons
        buttons = new UserTasksButtonPane();
        buttons.getRemoveButton().setDisable(true);
        buttons.getSaveButton().setDisable(true);
        buttons.relocate(0,435);


        // Create the ListView which will contain all the user's tasks
        userTasks = new ListView<>();
        userTasks.relocate(12,45);
        userTasks.setPrefSize(275,375);

        // Create the title Label
        titleLabel = new Label("My Tasks");
        titleLabel.setFont(new Font("Arial", 24));
        titleLabel.relocate(100,10);

        getChildren().addAll(buttons, titleLabel, userTasks);

        setPrefSize(300,550);

        // A debug line
//        for (int i = 0 ; i < buttons.getTranslations().size(); i++){
//            System.out.print(buttons.getTranslations().get(i));
//        }

    }

    @Override
    public void update(UserTasksAppView view, ArrayList<String> translations) {
            view.getButtons().getAddButton().setText(translations.get(0));
            view.getButtons().getRemoveButton().setText(translations.get(1));
            view.getButtons().getSaveButton().setText(translations.get(2));
            view.getButtons().getLoadButton().setText(translations.get(3));
    }
}
