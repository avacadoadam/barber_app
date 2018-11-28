package Dataset;

public class ListBarber {

    private String BarberName;
    private int BarberID;
    private int rating;

    public ListBarber(String barberName, int barberID, int rating) {
        BarberName = barberName;
        BarberID = barberID;
        this.rating = rating;
    }

    public String getBarberName() {
        return BarberName;
    }

    public int getBarberID() {
        return BarberID;
    }

    public int getRating() {
        return rating;
    }
}
