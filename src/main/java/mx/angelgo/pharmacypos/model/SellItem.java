package mx.angelgo.pharmacypos.model;

public class SellItem {
    private long productId;
    private int qty;
    private double price;

    public SellItem(long productId, int qty, double price) {
        this.productId = productId;
        this.qty = qty;
        this.price = price;
    }

    public double subtotal() {
        return qty * price;
    }

    public long getProductId() { return productId; }
    public int getQty() { return qty; }
    public double getPrice() { return price; }

}
