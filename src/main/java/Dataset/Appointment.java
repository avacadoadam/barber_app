package Dataset;


/**
 * A class to represent a object for a Appointment
 */
public class Appointment {


    private String Date,Time,BarberID,BarberName;

    public Appointment(String barberName) {
        BarberName = barberName;
    }

    public String getBarberName() {
        return BarberName;
    }
}
