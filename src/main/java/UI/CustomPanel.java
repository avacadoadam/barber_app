package UI;


import Backend.Appointments;
import Backend.Connect;
import Dataset.Appointment;
import Dataset.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.HashMap;
import java.util.List;

//A class to handle UI components that are shared between Barber admin and customer panels
public class CustomPanel extends CustomController {

    @FXML
    protected Label details_name;
    @FXML
    protected Label details_email;
    @FXML
    protected Label details_rating;
    @FXML
    protected ListView my_appointments;

    @FXML
    protected void initialize() {
        LoadDetails();
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("action", Appointments.GetMyAppointment.getAction());
        Connect.getInstance().GetAppointments(fields, new GetAppointmentsCallback() {
            public void Success(Appointment[] appointments) {

                System.out.println("Success");
            }

            public void Fail(String errorMessage) {
                System.out.println(errorMessage);
            }
        });


    }

    protected void GetAppointments() {
        HashMap<String, String> Fields = new HashMap<String, String>();
        Fields.put("action", "GetMyAppointments");
    }

    protected void LoadDetails() {
        User user = User.getInstance();
        System.out.println(user.getFname());
        details_name.setText(user.getFname() + " " + user.getLname());
        details_email.setText(user.getEmail());
        details_rating.setText(Integer.toString(user.getRating()));
    }

    protected void LoadAppointments(List<Appointment> appointments) {


    }

}

