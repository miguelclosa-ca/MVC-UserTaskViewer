import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class UserTasksButtonPane extends Pane {

    private Button addButton, removeButton;

    // Create buttons to save and load
    private Button saveButton, loadButton;

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

        // Define the attributes of the [Save...] Button:
        saveButton = new Button("Save...");
        saveButton.setPrefSize(100,50);
        saveButton.relocate(35,55);

        // Define the attributes of the [Load...] Button:
        loadButton = new Button("Load...");
        loadButton.setPrefSize(100,50);
        loadButton.relocate(165,55);

        buttonPane.getChildren().addAll(addButton,removeButton, saveButton, loadButton);

        getChildren().addAll(buttonPane);

    }
}
