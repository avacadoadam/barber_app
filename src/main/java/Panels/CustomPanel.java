package Panels;


import Backend.API;
import Backend.Appointments;
import Backend.Connect;
import Backend.ResponseFactory.AppointmentResponse;
import Callback.GetAppointmentsCallback;
import Dataset.Appointment;
import Dataset.User;
import UI.CustomController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;

//A class to handle UI components that are shared between Barber admin and customer panels
abstract public class CustomPanel extends CustomController {

    protected final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    @FXML
    protected Label details_name;
    @FXML
    protected Label details_email;
    @FXML
    protected Label details_rating;
    @FXML
    protected TableView my_appointments;

    /**
     * Sets up Appointments table and columns
     * Also Send request for appointments and loads then or display error message
     */
    protected void SetUpAppointments() {
        TableColumn CustomerName = new TableColumn("CustomerName");
        CustomerName.setCellValueFactory(new PropertyValueFactory<Appointment, String>("CustomerName"));

        TableColumn Barbershop = new TableColumn("Barbershop");
        Barbershop.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Barbershop"));

        TableColumn Time = new TableColumn("Time");
        Time.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Time"));

        TableColumn Date = new TableColumn("Date");
        Date.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Date"));

        my_appointments.getColumns().addAll(CustomerName, Barbershop, Time, Date);
        my_appointments.setItems(appointments);
        LoadDetails();
        HashMap<String, Object> fields = new HashMap<String, Object>();
        fields.put("action", Appointments.GetMyAppointment.getAction());

        Connect.getInstance().ConstructRequest(fields, API.GetAppointments, new AppointmentResponse(new GetAppointmentsCallback() {
            public void Success(Appointment[] appointments) {
                CustomPanel.this.appointments.addAll(appointments);
            }

            public void Fail(String errorMessage) {
                DisplayError(errorMessage);
            }
        }));


    }


    protected void LoadDetails() {
        User user = User.getInstance();
        System.out.println(user.getFname());
        details_name.setText(user.getFname() + " " + user.getLname());
        details_email.setText(user.getEmail());
        details_rating.setText(Integer.toString(user.getRating()));
    }

    @FXML
    protected void Logout() {
        Connect.getInstance().LogOut();
        ChangeScene("start.fxml", "Select Type");
    }


}

