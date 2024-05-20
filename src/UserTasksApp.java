import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserTasksApp extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane aPane = new Pane();
        UserTasksAppView view = new UserTasksAppView();

        aPane.getChildren().addAll(view);

        // Tweak the GUI window attributes
        primaryStage.setResizable(false);
        primaryStage.setTitle("My Tasks");
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

    }
}
