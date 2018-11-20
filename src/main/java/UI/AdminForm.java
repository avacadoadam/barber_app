package UI;

import Backend.API;
import Backend.Connect;
import Backend.Type;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class AdminForm extends CustomController implements ControllerCallback{

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
        StopLoading();
     ChangeScene("adminpanel.fxml", "Admin Panel");

    }

    public void Fail(String error) {
        StopLoading();
        DisplayError(error);
    }
}
