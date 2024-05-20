import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class UserTasksButtonPane extends Pane {

    private Button addButton, removeButton;

    /**
     * Getters and Setters
     */

    public Button getAddButton() {
        return addButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public UserTasksButtonPane(){

        Pane buttonPane = new Pane();

        // Define the attributes of the [Add Task] Button:
        addButton = new Button("Add Task");
        addButton.setPrefSize(100, 50);
        addButton.relocate(35,0);


        // Define the attributes of the [Remove Task] Button:
        removeButton = new Button("Remove Task");
        removeButton.setPrefSize(100,50);
        removeButton.relocate(165,0);

        buttonPane.getChildren().addAll(addButton,removeButton);

        getChildren().addAll(buttonPane);

    }
}
