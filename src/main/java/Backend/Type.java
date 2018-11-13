package Backend;

public enum Type {
    Customer("Customer"), Barber("Barber"), Admin("Admin");
    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
