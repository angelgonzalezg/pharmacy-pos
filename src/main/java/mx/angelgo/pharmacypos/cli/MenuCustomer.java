package mx.angelgo.pharmacypos.cli;

import mx.angelgo.pharmacypos.model.User;

import java.util.Scanner;

public class MenuCustomer {
    private final User user;

    public MenuCustomer(User u) {
        this.user = u;
    }

    public void start() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nBienvenido: " + user.getName());

        while(true){
            System.out.println("\n--- MENU PRINCIPAL");
            System.out.println("1. Ver Compras");
            System.out.println("2. Actualizar Datos");
            System.out.println("0. Salir");
            System.out.print("Seleccion: ");

            int op = Integer.parseInt(scan.nextLine());

            switch(op){
//                case 1 -> new MenuSales();
                case 2 -> new MenuUserProfiles();
                case 0 -> {
                    System.out.println("Finalizando PoS...");
                    return;
                }
            }
        }
    }
}
