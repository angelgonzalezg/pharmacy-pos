package mx.angelgo.pharmacypos.dao;

import mx.angelgo.pharmacypos.model.User;
import java.sql.*;

public class UserDao {
    private final Connection db_conn;

    public UserDao(Connection conn) {
        this.db_conn = conn;
    }

    public User login(String email, String passwd) throws Exception {
        String sqlQuery = "SELECT * FROM users WHERE email=? AND password_hash=?";

        try (PreparedStatement ps = db_conn.prepareStatement(sqlQuery)) {
            ps.setString(1, email);
            ps.setString(2, passwd);

            try (ResultSet  rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password_hash"),
                            rs.getString("role")
                    );
                }
            }

        } catch (SQLException e) {
            // Print error message
            System.err.println("Error in User login: " + e.getMessage());

            //
            throw  new RuntimeException("Database error during User login");
        }
        // User not found
        return null;
    }

}
