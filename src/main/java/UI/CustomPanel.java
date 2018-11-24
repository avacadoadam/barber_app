package UI;


import Backend.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

//A class to handle UI components that are shared between Barber admin and customer panels
public class Panel extends CustomController{

    @FXML public Label details_name;
    @FXML public Label details_email;
    @FXML public Label details_rating;


    public void LoadDetails(){
        User user  = User.getInstance();
        details_email.setText(user.getFname() + " " + user.getLname());
        details_email.setText(user.getEmail());
        details_rating.setText(Integer.toString(user.getRating()));
    }



}
