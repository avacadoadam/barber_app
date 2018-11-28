package UI;

import Backend.API;
import Callback.ControllerCallback;
import Backend.Connect;
import Backend.Type;
import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class AdminForm extends CustomController implements ControllerCallback {

    public TextField login_email;
    public TextField login_passwd;
    private Type type = Type.Admin;

    public void Login() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("email", login_email.getText());
        fields.put("passwd", login_passwd.getText());
        fields.put("type", type.getType());
        Connect.getInstance().ConstructRequest(fields, API.LogIn, this);
        StartLoading();

    }


    public void Succes(API action) {
        Platform.runLater(new Runnable() {
            public void run() {

                StopLoading();
                ChangeScene("adminpanel.fxml", "Admin CustomPanel");

            }
        });
    }

    public void Fail(final String error) {
        Platform.runLater(new Runnable() {
            public void run() {
                StopLoading();
                DisplayError(error);

                if (error.equals("Already Logged in")) {
                    Connect.getInstance().LogOut();
                }
            }
        });

    }
}
