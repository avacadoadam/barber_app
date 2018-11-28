package Dataset;


import javafx.beans.property.SimpleStringProperty;

/**
 * A class to represent a object for a Appointment
 */
public class Appointment {


    private SimpleStringProperty Barbershop,Time,CustomerName,BarberName,Date;
    private SimpleStringProperty AppointmentID;

    public Appointment(int appointmentID, String barbershop, String time, String customerName, String barberName, String date) {
        AppointmentID = new SimpleStringProperty (Integer.toString(appointmentID));
        Barbershop = new SimpleStringProperty (barbershop);
        Time = new SimpleStringProperty (time);
        CustomerName = new SimpleStringProperty (customerName);
        BarberName = new SimpleStringProperty (barberName);
        Date = new SimpleStringProperty (date);
    }

    public String getBarbershop() {
        return Barbershop.get();
    }

    public SimpleStringProperty barbershopProperty() {
        return Barbershop;
    }

    public String getTime() {
        return Time.get();
    }

    public SimpleStringProperty timeProperty() {
        return Time;
    }

    public String getCustomerName() {
        return CustomerName.get();
    }

    public SimpleStringProperty customerNameProperty() {
        return CustomerName;
    }

    public String getBarberName() {
        return BarberName.get();
    }

    public SimpleStringProperty barberNameProperty() {
        return BarberName;
    }

    public String getDate() {
        return Date.get();
    }

    public SimpleStringProperty dateProperty() {
        return Date;
    }

    public String getAppointmentID() {
        return AppointmentID.get();
    }

    public SimpleStringProperty appointmentIDProperty() {
        return AppointmentID;
    }
}
