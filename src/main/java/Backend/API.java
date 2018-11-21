package Backend;

/*
A enum class to represent the outward facing api functions and will a getter for there extension of the
base domain
 */
public enum API {

    LogIn("/LogIn"),
    Register("/CreateAccount"),
    Logout("/LogOut"),
    Admin("/Admin"),
    BookAppointment("/BookAppointment"),
    CreateAccount("/CreateAccount"),
    GetAppointments("/GetAppointments");


    private String Action;

    API(String type) {
        this.Action = type;
    }

    public String getAction() {
        return Action;
    }
}
