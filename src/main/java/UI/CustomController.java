package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Handles Loading, Changing Scene and responses
 */
public abstract class CustomController {
    public static Stage stage;
    @FXML
    private Label Loading;
    @FXML
    private Label response;


    /**
     * Changes Scene resource is of which FXML to switch
     * Title is the title of the scene
     */
    protected void ChangeScene(String resource, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../" + resource));
            Scene scene = new Scene(root, 600, 450);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Every Scene must have a error label to display errors
     *
     * @param error
     */
    protected void DisplayError(String error) {
        this.response.setText(error);
    }

    /**
     * Shows the user that backend work is taking place
     */
    protected void StartLoading() {
        Loading.setVisible(true);
        Loading.setText("Loading");
    }

    protected void DisplaySucess(String success) {
        response.setText(success);
    }

    /**
     * Show user Backend work is finished
     */
    protected void StopLoading() {
        Loading.setVisible(false);
    }

}
