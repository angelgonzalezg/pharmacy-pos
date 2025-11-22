package mx.angelgo.pharmacypos.dao;

import mx.angelgo.pharmacypos.model.*;
import java.sql.*;
import java.util.*;

public class ProductDao {
    private final Connection db_conn;

    public ProductDao(Connection conn) {
        this.db_conn = conn;
    }

    public void registerProduct(
            int prodType,
            String name,
            double price,
            boolean status,
            String brand,
            String expiry,
            boolean isAntibiotic,
            int maxQtyPerRecipe,
            String medType,
            Boolean isNatural
    ) throws Exception {

        System.out.println("DBG Caducidad  en DAO es: " + expiry);

        Product p;
        switch (prodType) {
            case 1:
                p = new Medicine( 0, name, brand, price, status, expiry, isAntibiotic, maxQtyPerRecipe, medType);
                break;

            case 2:
                p = new Candy(0, name, brand, price, status, expiry, isNatural);
                break;

            case 3:
                p = new Miscellaneous(0, name, brand, price, status, expiry);
                break;

            default:
                throw new Exception("Tipo de producto no válido.");
        }

        insert(p);
    }


    public int insert(Product p) throws Exception {
        String sql = """
        INSERT INTO products(
            name,
            brand,
            price,
            status,
            expiration,
            product_type,
            is_antibiotic,
            max_qty_per_recipe,
            med_type,
            is_natural
        )
        VALUES (?, ?, ?, ?, ?, ?::product_type, ?, ?, ?::medicine_type, ?)
    """;

        try (PreparedStatement ps = db_conn.prepareStatement(sql)) {

            // Campos comunes
            ps.setString(1, p.getName());
            ps.setString(2, p.getBrand());
            ps.setDouble(3, p.getPrice());
            ps.setBoolean(4, p.getStatus());
            System.out.println("DEBUG expiration received: [" + p.getExpiryDate() + "]");
            ps.setDate(5, java.sql.Date.valueOf(p.getExpiryDate()));

            // Tipo (1=Medicamento, 2=Golosina, 3=Miscelaneo)
            ps.setString(6, p.getProductType());

            // Dependientes del tipo
            if (p instanceof Medicine m) {
                ps.setBoolean(7, m.getIsAntibiotic());
                ps.setInt(8, m.getMaxQtyPerRecipe());
                ps.setString(9, m.getMedicineType());
                ps.setNull(10, java.sql.Types.BOOLEAN);
            }
            else if (p instanceof Candy c) {
                ps.setNull(7, java.sql.Types.BOOLEAN);  // antibiotico
                ps.setNull(8, java.sql.Types.INTEGER);  // max receta
                ps.setNull(9, java.sql.Types.VARCHAR);  // tipo medicamento
                ps.setBoolean(10, c.getIsNatural());
            }
            else if (p instanceof Miscellaneous m) {
                ps.setNull(7, java.sql.Types.BOOLEAN);
                ps.setNull(8, java.sql.Types.INTEGER);
                ps.setNull(9, java.sql.Types.VARCHAR);
                ps.setNull(10, java.sql.Types.BOOLEAN);
            }

            ps.executeUpdate();
        }
        return 0;
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

        try (Statement st = db_conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM products ORDER BY id")) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }

        return list;
    }


    private Product mapRow(ResultSet rs) throws Exception {
        String prodType = rs.getString("product_type");

        String expDate = rs.getString("expiration");

        return switch (prodType) {

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
