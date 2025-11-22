package mx.angelgo.pharmacypos.model;

import java.time.LocalDate;

public class Candy extends Product {

    private boolean isNatural;

    public Candy(long id, String name, String brand, double price, boolean status, LocalDate expiryDate, boolean isNatural) {
        super(id, name, brand, price, status, expiryDate, "GOLOSINA");
        this.isNatural = isNatural;
    }

    @Override
    public String getDetails() {
        return "Natural: " + isNatural;
    }
}
