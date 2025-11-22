package mx.angelgo.pharmacypos.dao;

import mx.angelgo.pharmacypos.model.SaleItem;
import java.sql.*;
import java.util.List;

public class SalesDao {
    private final Connection db_conn;

    public SalesDao(Connection conn) {
        this.db_conn = conn;
    }

    public void createSale(long userId, List<SaleItem> items) throws Exception {
        String sqlSale = "INSERT INTO sales(vendor_id, total) VALUES (?, ?) RETURNING id";
        String sqlItem = "INSERT INTO sale_items(sale_id, product_id, quantity, unit_price) VALUES (?, ?, ?, ?)";

        double total = items.stream().mapToDouble(SaleItem::subtotal).sum();

        db_conn.setAutoCommit(false);
        try {
            long saleId;
            try(PreparedStatement ps = db_conn.prepareStatement(sqlSale)) {
                ps.setLong(1, userId);
                ps.setDouble(2, total);
                try(ResultSet rs = ps.executeQuery()){
                    rs.next();
                    saleId = rs.getLong(1);
                }
            }

            for(SaleItem item : items) {
                try(PreparedStatement ps = db_conn.prepareStatement(sqlItem)) {
                    ps.setLong(1, saleId);
                    ps.setLong(2, item.getProductId());
                    ps.setInt(3, item.getQty());
                    ps.setDouble(4, item.getPrice());
                    ps.executeUpdate();
                }
            }

            db_conn.commit();
        } catch(Exception e){
            db_conn.rollback();
            throw e;
        }
    }
}
