package mx.angelgo.pharmacypos.model;

import java.time.LocalDate;

public class Medicine extends Product {
    private boolean isAntibiotic;
    private int maxQtyPerRecipe;
    private String medicineType;

    public Medicine(long id, String name, String brand, double price, boolean status, LocalDate expiryDate, boolean isAntibiotic, int maxQtyPerRecipe, String medicineType) {

        super(id, name, brand, price, status, expiryDate, "MEDICAMENTO");
        this.isAntibiotic = isAntibiotic;
        this.maxQtyPerRecipe = maxQtyPerRecipe;
        this.medicineType = medicineType;
    }

    @Override
    public String getDetails() {
        return "Antibiotico: " + isAntibiotic +
                " Tipo: " + medicineType +
                " Max cantidad por receta: " + maxQtyPerRecipe;
    }

}
