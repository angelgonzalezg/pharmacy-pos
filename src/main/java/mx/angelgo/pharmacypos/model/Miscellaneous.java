package mx.angelgo.pharmacypos.model;

public class Miscellaneous extends Product {

    public Miscellaneous(long id, String name, String brand, double price, boolean status, String expiryDate) {
        super(id, name, brand, price, status, expiryDate, "MISCELANEO");
    }

    public Miscellaneous(int id, String name, double price, int status, String brand, String expiry) {
    }

    @Override
    public String getDetails() {
        return "Producto miscelaneo";
    }
}
