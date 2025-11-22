package mx.angelgo.pharmacypos.model;

import java.time.LocalDate;

public abstract class Product {
    protected long id;
    protected String name;
    protected String brand;
    protected double price;
    protected boolean status;
    protected String expiryDate;
    protected String productType;

    public Product() {}

    public Product(long id, String name, String brand, double price, boolean status, String expiryDate, String productType) {
        this.id=id;
        this.name=name;
        this.brand=brand;
        this.price=price;
        this.status=status;
        this.expiryDate=expiryDate;
        this.productType=productType;
    }

    public Product(String name, String brand, double price, boolean status, String expiryDate, String productType) {
        this.name=name;
        this.brand=brand;
        this.price=price;
        this.status=status;
        this.expiryDate=expiryDate;
        this.productType=productType;
    }

    public abstract String getDetails();

    public long getId() { return id; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public double getPrice() { return price; }
    public boolean getStatus() { return status; }
    public String getExpiryDate() { return expiryDate; }
    public String getProductType() { return productType; }

}
