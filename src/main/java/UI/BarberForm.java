package UI;

import Backend.API;
import Backend.Connect;
import Backend.Type;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class BarberForm extends CustomController implements ControllerCallback {

    @FXML
    public TextField reg_email;
    @FXML
    public TextField reg_passwd;
    @FXML
    public TextField reg_fname;
    @FXML
    public TextField reg_lname;
    @FXML
    public TextField login_email;
    @FXML
    public TextField login_passwd;
    private Type type = Type.Barber;

    public void Register() {
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("email", reg_email.getText());
        fields.put("passwd", reg_passwd.getText());
        fields.put("fname", reg_fname.getText());
        fields.put("lname", reg_lname.getText());
        fields.put("type", type.getType());
        Connect.getInstance().ConstructRequest(fields, API.Register, this);
        StartLoading();
    }

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
        switch (action) {
            case LogIn:
                ChangeScene("barberpanel.fxml", "Barber Panel");
                break;
            case Register:
                DisplaySucess("You have create your account you may now log In");
                break;
        }

    }

    public void Fail(String error) {

        if (error.equals("Already Logged in")) {
            Connect.getInstance().LogOut();
        }
        StopLoading();
        DisplayError(error);
    }

}
