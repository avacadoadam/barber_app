package Dataset;


/**
 * A class to represent a object for a Appointment
 */
public class Appointment {


    private String Barbershop,Time,CustomerName,BarberName,Date;
    private int AppointmentID;

    public Appointment(int appointmentID, String barbershop, String time, String customerName, String barberName, String date) {
        AppointmentID = appointmentID;
        Barbershop = barbershop;
        Time = time;
        CustomerName = customerName;
        BarberName = barberName;
        Date = date;
    }

    public int getAppointmentID() {
        return AppointmentID;
    }

    public String getBarbershop() {
        return Barbershop;
    }

    public String getTime() {
        return Time;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getBarberName() {
        return BarberName;
    }

    public String getDate() {
        return Date;
    }
}
