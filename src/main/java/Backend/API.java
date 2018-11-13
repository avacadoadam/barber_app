package Backend;

/*
A enum class to represent the outward facing api functions and will a getter for there extension of the
base domain
 */
public enum API {

    LogIn("/LogIn"), GetAppointments("Barber"), Admin("Admin");
    private String type;

    API(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
