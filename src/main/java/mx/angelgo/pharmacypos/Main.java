package mx.angelgo.pharmacypos;

import mx.angelgo.pharmacypos.cli.MenuAdmin;
import mx.angelgo.pharmacypos.cli.MenuCustomer;
import mx.angelgo.pharmacypos.cli.MenuVendor;
import mx.angelgo.pharmacypos.dao.ProductDao;
import mx.angelgo.pharmacypos.dao.SalesDao;
import mx.angelgo.pharmacypos.util.DBConnection;
import mx.angelgo.pharmacypos.util.Console;
import mx.angelgo.pharmacypos.dao.UserDao;
import mx.angelgo.pharmacypos.model.User;


import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Connection db_conn = DBConnection.getConnection(
                "jdbc:postgresql://localhost:5432/pharmacy_pos_db",
                "angelgo",
                "1234"
        );

        UserDao userDao = new UserDao(db_conn);
        ProductDao productDao = new ProductDao(db_conn);
        SalesDao salesDao = new SalesDao(db_conn);


        Scanner scan = new Scanner(System.in);

        System.out.print("\n=== PHARMACY ANGELGO ===");

//        System.out.print("Email: ");
//        String email = scan.nextLine();
//
//        System.out.print("Pw: ");
//        String passwd = scan.nextLine();

        String email = "admin@admin.com";
        String passwd = "passadmin";

        User user = userDao.login(email, passwd);

        if (user == null) {
            System.out.println("\nCredenciales incorrectas!");
            return;
        } else {
            switch(user.getRole()) {
                case "ADMIN" -> new MenuAdmin(user, productDao, salesDao).start();
                case "VENDEDOR" -> new MenuVendor(user);
                case "CLIENTE" -> new MenuCustomer(user);
            }
        }
    }
}