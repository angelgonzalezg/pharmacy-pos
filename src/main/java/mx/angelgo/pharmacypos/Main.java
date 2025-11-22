package mx.angelgo.pharmacypos;

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

        UserDao uDao = new UserDao(db_conn);

        Scanner scan = new Scanner(System.in);

        System.out.print("\n=== PHARMACY ANGELGO ===");

//        System.out.print("Email: ");
//        String email = scan.nextLine();
//
//        System.out.print("Pw: ");
//        String passwd = scan.nextLine();

        String email = "angel.glez.g@gmail.com";
        String passwd = "1234";

        User u = uDao.login(email, passwd);

        if (u == null) {
            System.out.println("Credenciales incorrectas!");
            return;
        }

        Console.clear();

        System.out.println("Usuario encontrado");


    }
}