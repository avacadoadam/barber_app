import UI.CustomController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        CustomController.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        Scene scene = new Scene(root, 300, 275);
        stage.setTitle("Choose Type");
        stage.setScene(scene);
        stage.show();
    }

}
