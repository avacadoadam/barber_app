package Panels;

import Backend.API;
import Backend.Connect;
import Backend.ResponseFactory.GenericResponse;
import Backend.ResponseFactory.GetBarbersResponse;
import Callback.FormCallback;
import Callback.ListBarbersController;
import Dataset.ListBarber;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.HashMap;

public class Customerpanel extends CustomPanel {


    protected final ObservableList<String> Barbers = FXCollections.observableArrayList();
    HashMap<String, Integer> barbersID = new HashMap<String, Integer>();

    @FXML
    ComboBox<String> set_appointment_barberList;

    @FXML
    Label Hour_label, Minute_Label;

    @FXML
    Spinner set_appointment_minute;
    @FXML
    Spinner set_appointment_hour;

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
        Connect.getInstance().ConstructRequest(field, API.GetAppointments, new GetBarbersResponse(new ListBarbersController() {
            public void Success(ListBarber[] barbers) {
                for (ListBarber barber : barbers) {
                    Barbers.add(barber.getBarberName());
                    barbersID.put(barber.getBarberName(), barber.getBarberID());
                }
            }

            public void Fail(String error) {
                DisplayError(error);
            }
        }));

    }

    @FXML //Set 1
    public void barberlistChange() {
        if (!set_appointment_barberList.getValue().equals("Select A barber")) {
            set_appointment_hour.setVisible(true);
            set_appointment_minute.setVisible(true);
            Hour_label.setVisible(true);
            Minute_Label.setVisible(true);
            set_appointment_date.setVisible(true);
        } else {
            setAppointment.setDisable(true);
            set_appointment_hour.setVisible(false);
            set_appointment_minute.setVisible(false);
            set_appointment_date.setVisible(false);
            Hour_label.setVisible(false);
            Minute_Label.setVisible(false);
        }
    }

    @FXML //Step 2
    public void timeChange() {
        if (set_appointment_date.getValue() != null) {
            setAppointment.setDisable(false);
        } else {
            setAppointment.setDisable(true);
        }

    }

    //Step 3
    public void SetAppointment() {
        HashMap<String, Object> field = new HashMap<String, Object>();
        field.put("barbershop", 1);
        field.put("barber", set_appointment_barberList.getValue());
        field.put("date", set_appointment_date.getValue());
        String m = set_appointment_minute.getValue().toString();
        String h = set_appointment_hour.getValue().toString();
        if (m.length() == 1) m = "0" + m;
        if (h.length() == 1) h = "0" + h;
        field.put("time", h + ":" + m + ":00");
        Connect.getInstance().ConstructRequest(field, API.BookAppointment, new GenericResponse(new FormCallback() {
            public void Succes(API action) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        DisplaySucess("Success Book Appointment");
                        appointments.clear();
                        SetUpAppointments();
                    }
                });
            }

            public void Fail(final String error) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        DisplayError(error);
                    }
                });
            }
        }));
    }
}
