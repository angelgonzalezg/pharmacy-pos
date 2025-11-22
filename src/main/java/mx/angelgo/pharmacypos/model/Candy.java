package mx.angelgo.pharmacypos.model;

public class Candy extends Product {

    private boolean isNatural;

    public Candy(long id, String name, String brand, double price, boolean status, String expiryDate, boolean isNatural) {
        super(id, name, brand, price, status, expiryDate, "GOLOSINA");
        this.isNatural = isNatural;
    }

    public Candy(int id, String name, double price, int status, String brand, String expiry, Boolean isNatural) {
    }

    @Override
    public String getDetails() {
        return "Natural: " + isNatural;
    }

    public boolean getIsNatural() { return isNatural; }
}
