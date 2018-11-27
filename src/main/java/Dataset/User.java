package Dataset;

import Backend.Type;

public class User {

    private static User instance = null;
    private String lname, fname, email;
    private int id, rating;
    private Type type;
    private User(String lname, String fname, String email, int id, Type type, int rating) {
        this.lname = lname;
        this.fname = fname;
        this.email = email;
        this.id = id;
        this.type = type;
        this.rating = rating;
    }

    synchronized public static User CreateInstance(String lname, String fname, String email, int id, String type, int rating) {
        Type type1 = null;
        if (type.equals("Admin")) type1 = Type.Admin;
        if (type.equals("Customer")) type1 = Type.Customer;
        if (type.equals("Barber")) type1 = Type.Barber;
        if (instance == null)
            instance = new User(lname, fname, email, id, type1, rating);
        return instance;
    }

    public static User getInstance() {
        return instance;
    }

    public void Logout() {
        User.instance = null;
    }

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public int getRating() {
        return rating;
    }

    public Type getType() {
        return type;
    }
}
