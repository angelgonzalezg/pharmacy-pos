package mx.angelgo.pharmacypos.cli;

import mx.angelgo.pharmacypos.dao.ProductDao;

import java.util.Scanner;

public class MenuProducts {
    private final ProductDao pDao;

    public MenuProducts(ProductDao pDao) {
        this.pDao = pDao;
    }

    public void start() {
        Scanner scan = new Scanner(System.in);

        while(true){
            System.out.println("\n--- MENU PRODUCTOS");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("0. Salir");
            System.out.print("Seleccion: ");

            int op = Integer.parseInt(scan.nextLine());

            switch(op){
                case 1 -> registerProductMenu();
                case 2 -> showProducts();
                case 0 -> {
                    System.out.println("Finalizando PoS...");
                    return;
                }
            }
        }
    }

    private void registerProductMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n--- Registrar Producto");
        System.out.println("1. Medicamento");
        System.out.println("2. Golosina");
        System.out.println("3. Miscelaneo");
        System.out.print("Seleccion: ");

        int prodType = scan.nextInt();
        scan.nextLine();

        System.out.print("Nombre: ");
        String name = scan.nextLine();

        System.out.print("Precio: ");
        double price = scan.nextDouble();
        scan.nextLine();

        System.out.print("Marca: ");
        String brand = scan.nextLine();

        System.out.print("Caducidad (YYYY-MM-DD): ");
        String expiry = scan.nextLine();
        System.out.println("DBG Caducidad es: " + expiry);

        System.out.print("Activo? (1 si, 0 no): ");
        boolean status = scan.nextInt() == 1;
        scan.nextLine();

        boolean isAntibiotic = false;
        int maxRecipes = 0;
        String medType = null;
        Boolean isNatural = null;

        if (prodType == 1) {
            System.out.print("Es antibiotico? (1 si, 0 no): ");
            isAntibiotic = scan.nextInt() == 1;
            scan.nextLine();

            System.out.print("Cantidad maxima por receta: ");
            maxRecipes = scan.nextInt();
            scan.nextLine();

            System.out.print("Tipo (pastilla/jarabe/supositorio): ");
            medType = scan.nextLine().toUpperCase();
        }
        else if (prodType == 2) {
            System.out.print("Es natural? (1 sí, 0 no): ");
            isNatural = scan.nextInt() == 1;
            scan.nextLine();
        }

        try {
            pDao.registerProduct(prodType, name, price, status, brand, expiry, isAntibiotic, maxRecipes, medType, isNatural);
            System.out.println("Producto registrado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void showProducts(){
        try{
            System.out.println("\n--- LISTA DE PRODUCTOS");
            pDao.findAll().forEach(p ->
                    System.out.println(p.getId()+" - "+p.getName()+" $"+p.getPrice())
            );
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
}
