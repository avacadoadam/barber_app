package UI;


import Dataset.Appointment;
import Dataset.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

//A class to handle UI components that are shared between Barber admin and customer panels
public class CustomPanel extends CustomController{

    @FXML protected Label details_name;
    @FXML protected Label details_email;
    @FXML protected Label details_rating;
    @FXML protected ListView my_appointments;


    protected void LoadDetails(){
        User user  = User.getInstance();
        details_email.setText(user.getFname() + " " + user.getLname());
        details_email.setText(user.getEmail());
        details_rating.setText(Integer.toString(user.getRating()));
    }

    protected void LoadAppointments(List<Appointment> appointments){

        appointments = new ArrayList<Appointment>();
        appointments.add(new Appointment("asdfasd"));
        appointments.add(new Appointment("asdfasd"));
        appointments.add(new Appointment("asdfasd"));



    }

}

class CustomerCell extends ListCell<Appointment>{


    @Override
    protected void updateItem(Appointment item, boolean empty) {
        super.updateItem(item, empty);
        this.setText(item.getBarberName());
        setGraphic(null);

    }
}
