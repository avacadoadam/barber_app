package Panels;

import Backend.Connect;
import Callback.ComplaintsCallback;
import Callback.GetAppointmentsCallback;
import Callback.UnapprovedBarbers;
import Dataset.Appointment;
import Dataset.User;
import UI.CustomController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;


public class AdminPanel extends CustomController {

    private final ObservableList<String> Complaintslist = FXCollections.observableArrayList();
    private final ObservableList<String> UnApprovedBarbers = FXCollections.observableArrayList();
    private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    @FXML
    protected TableView my_appointments;
    @FXML
    TextField add_admin_email;
    @FXML
    TextField add_admin_password;
    @FXML
    Button add_admin;
    @FXML
    ListView waiting;
    @FXML
    ListView Complaints;
    @FXML
    Label details_name;
    @FXML
    Label details_email;
    private ComplaintsCallback callback = new ComplaintsCallback() {
        public void Success(String[] complaints) {
            Complaintslist.addAll(complaints);
        }

        public void fail(String error) {
            DisplayError(error);
        }
    };
    private UnapprovedBarbers Unapprovedbarberscallback = new UnapprovedBarbers() {
        public void Success(String[] barbers) {
            UnApprovedBarbers.addAll(barbers);
        }

        public void Fail(String error) {
            DisplayError(error);
        }
    };


    @FXML
    private void initialize() {
        System.out.println("Init called");
        LoadDetails();
        Complaints.setItems(Complaintslist);
        waiting.setItems(UnApprovedBarbers);
        my_appointments.setItems(appointments);
        GetComplaints();
        GetBarbersWaitngApproval();
        GetAllAppoitments();
    }

    private void GetBarbersWaitngApproval() {
        Connect.getInstance().GetUnApprovedBarbers(Unapprovedbarberscallback);
        //Send Reqeuest and make Controller
    }

    private void GetComplaints() {
        HashMap<String, Object> field = new HashMap<String, Object>();
        field.put("action", "GetBarberComplaints");
        Connect.getInstance().GetComplaints(field, callback);
        field.put("action", "GetCustomerComplaints");
        Connect.getInstance().GetComplaints(field, callback);
    }

    private void GetAllAppoitments() {
        TableColumn CustomerName = new TableColumn("CustomerName");
        CustomerName.setCellValueFactory(new PropertyValueFactory<Appointment, String>("CustomerName"));

        TableColumn Barbershop = new TableColumn("Barbershop");
        Barbershop.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Barbershop"));

        TableColumn Time = new TableColumn("Time");
        Time.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Time"));

        TableColumn Date = new TableColumn("Date");
        Date.setCellValueFactory(new PropertyValueFactory<Appointment, String>("Date"));

        my_appointments.getColumns().addAll(CustomerName, Barbershop, Time, Date);

        Connect.getInstance().AdminGetAllAppointments(new GetAppointmentsCallback() {
            public void Success(Appointment[] appointment) {
                appointments.addAll(appointment);
            }

            public void Fail(String errorMessage) {
                DisplayError(errorMessage);
            }
        });
    }

    protected void LoadDetails() {
        User user = User.getInstance();
        System.out.println(user.getFname());
        details_name.setText(user.getFname() + " " + user.getLname());
        details_email.setText(user.getEmail());

    }


}
