package Backend;

/*
A enum class to represent the outward facing api functions and will a getter for there extension of the
base domain
 */
public enum API {

    LogIn("/LogIn"),
    Register("/CreateAccount");
    private String Action;

    API(String type) {
        this.Action = type;
    }

    public String getAction() {
        return Action;
    }
}
