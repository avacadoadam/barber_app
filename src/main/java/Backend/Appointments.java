package Backend;

public enum Appointments {

    GetMyAppointment("GetMyAppointment"),
    BarberFreeTime("BarberFreeTime"),
    ListBarberShops("ListBarberShops"),
    CancelAppointment("CancelAppointment"),
    ListBarbers("ListBarbers");

    private String Action;

    Appointments(String type) {
        this.Action = type;
    }

    public String getAction() {
        return Action;
    }
}
