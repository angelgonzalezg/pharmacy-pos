package mx.angelgo.pharmacypos.dao;

import mx.angelgo.pharmacypos.model.*;
import java.sql.*;
import java.util.*;
import java.time.LocalDate;

public class ProductDao {
    private final Connection db_conn;

    public ProductDao(Connection conn) {
        this.db_conn = conn;
    }

    public Product findById(long id) throws Exception {
        String sqlQuery = "SELECT * FROM products WHERE id=?";
        try(PreparedStatement ps = db_conn.prepareStatement(sqlQuery)){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        try(Statement st = db_conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM products")){
            while(rs.next()) list.add(mapRow(rs));
        }
        return list;
    }

    private Product mapRow(ResultSet rs) throws Exception {
        String prodType = rs.getString("product_type");
        LocalDate expDate = rs.getDate("expiry_date").toLocalDate();

        return switch(prodType){
            case "MEDICAMENTO" ->
                    new Medicine(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("brand"),
                            rs.getDouble("price"),
                            rs.getBoolean("status"),
                            expDate,
                            rs.getBoolean("is_antibiotic"),
                            rs.getInt("max_qty_per_recipe"),
                            rs.getString("med_type")
                    );

            case "CANDY" ->
                    new Candy(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("brand"),
                            rs.getDouble("price"),
                            rs.getBoolean("status"),
                            expDate,
                            rs.getBoolean("is_natural")
                    );

            default ->
                    new Miscellaneous(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("brand"),
                            rs.getDouble("price"),
                            rs.getBoolean("status"),
                            expDate
                    );
        };
    }

}
