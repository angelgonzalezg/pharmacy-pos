package mx.angelgo.pharmacypos.model;

import java.time.LocalDate;

public class Miscellaneous extends Product {

    public Miscellaneous(long id, String name, String brand, double price, boolean status, LocalDate expiryDate) {
        super(id, name, brand, price, status, expiryDate, "MISCELANEO");
    }

    @Override
    public String getDetails() {
        return "Producto miscelaneo";
    }
}
