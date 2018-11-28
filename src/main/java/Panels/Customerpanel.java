package Panels;

import Backend.Connect;
import Callback.ListBarbersController;
import Dataset.ListBarber;
import Panels.CustomPanel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;

import java.util.HashMap;

public class Customerpanel extends CustomPanel {


    protected final ObservableList<String> Barbers = FXCollections.observableArrayList();

    @FXML
    ComboBox<String> set_appointment_barberList;

    HashMap<String, Integer> barbersID = new HashMap<String, Integer>();

    @FXML
    Spinner set_appointment_time;

    @FXML
    DatePicker set_appointment_date;
    @FXML
    Button setAppointment;

    @FXML
    protected void initialize() {
        SetUpAppointments();
        Barbers.add("Select A barber");
        set_appointment_barberList.setItems(Barbers);
        HashMap<String, Object> field = new HashMap<String, Object>();
        field.put("action", "ListBarbers");
        Connect.getInstance().GetBarbers(field, new ListBarbersController() {
            public void Success(ListBarber[] barbers) {
                for (ListBarber barber : barbers) {
                    Barbers.add(barber.getBarberName());
                    barbersID.put(barber.getBarberName(), barber.getBarberID());
                }
            }
            public void Fail(String error) {
                DisplayError(error);
            }
        });

    }

    @FXML //Set 1
    public void barberlistChange() {
        if (!set_appointment_barberList.getValue().equals("Select A barber")) {
            set_appointment_time.setVisible(true);
        } else {
            setAppointment.setDisable(true);
            set_appointment_time.setVisible(false);
            set_appointment_date.setVisible(false);
        }
    }

    @FXML //Step 2
    public void timeChange() {
        System.out.println(set_appointment_time.getValue());
        if (set_appointment_time.getValue() != null) {
            set_appointment_date.setVisible(true);
        } else {
            setAppointment.setDisable(false);
            set_appointment_date.setVisible(false);
        }
    }

    //Step 3
    public void SetAppointment() {
        HashMap<String, Object> field = new HashMap<String, Object>();
        field.put("barbershop", 1);
        field.put("barber", set_appointment_barberList.getValue());
        field.put("date", set_appointment_date.getValue());
        field.put("time", set_appointment_time.getValue());

        //Connection book appoitment
        //Send request validate input
    }
}
