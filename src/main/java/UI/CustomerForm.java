package UI;

import Backend.API;
import Backend.Connect;
import Backend.ResponseFactory.LogInResponse;
import Backend.ResponseFactory.RegisterResponse;
import Backend.Type;
import Callback.FormCallback;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class CustomerForm extends CustomController implements FormCallback {

    @FXML
    private TextField reg_email;
    @FXML
    private TextField reg_passwd;
    @FXML
    private TextField reg_fname;
    @FXML
    private TextField reg_lname;
    @FXML
    private TextField login_email;
    @FXML
    private TextField login_passwd;
    private Type type = Type.Customer;


    public void Login() {
        StartLoading();
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("email", login_email.getText());
        fields.put("passwd", login_passwd.getText());
        fields.put("type", type.getType());
        Connect.getInstance().ConstructRequest(fields, API.LogIn, new LogInResponse(this));
    }

    public void Register() {
        StartLoading();
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("email", reg_email.getText());
        fields.put("passwd", reg_passwd.getText());
        fields.put("fname", reg_fname.getText());
        fields.put("lname", reg_lname.getText());
        fields.put("type", type.getType());
        Connect.getInstance().ConstructRequest(fields, API.Register, new RegisterResponse(this));

    }

    public void Succes(final API action) {
        StopLoading();
        Platform.runLater(new Runnable() {
            public void run() {
                switch (action) {
                    case LogIn:
                        ChangeScene("customerpanel.fxml", "Customer CustomPanel");
                        break;
                    case Register:
                        DisplaySucess("You have create your account you may now log In");
                        break;
                }
            }
        });
    }

    public void Fail(final String error) {

        if (error.equals("Already Logged in")) {
            Connect.getInstance().LogOut();
        }
        Platform.runLater(new Runnable() {
            public void run() {
                StopLoading();
                DisplayError(error);
            }
        });
    }


}
