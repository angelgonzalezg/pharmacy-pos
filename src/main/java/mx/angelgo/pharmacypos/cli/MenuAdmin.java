package mx.angelgo.pharmacypos.cli;

import mx.angelgo.pharmacypos.dao.ProductDao;
import mx.angelgo.pharmacypos.dao.SalesDao;
import mx.angelgo.pharmacypos.model.User;

import java.util.Scanner;

public class MenuAdmin {
    private final User user;
    private final ProductDao pDao;
    private final SalesDao sDao;

    public MenuAdmin(User user, ProductDao pDao, SalesDao sDao) {
        this.user = user;
        this.pDao = pDao;
        this.sDao = sDao;
    }

    public void start() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nBienvenido: " + user.getName());
        System.out.println("No Empleado: " + user.getNoEmployee());

        while(true){
            System.out.println("\n--- MENU PRINCIPAL");
            System.out.println("1. Productos");
            System.out.println("2. Ventas");
            System.out.println("3. Usuarios");
            System.out.println("0. Salir");
            System.out.print("Seleccion: ");

            int op = Integer.parseInt(scan.nextLine());

            switch(op){
                case 1 -> new MenuProducts(pDao).start();
                case 2 -> new MenuSales(sDao, pDao, user.getId());
                case 3 -> new MenuUserProfiles();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Introduzca un valor valido!");
            }
        }
    }
}
