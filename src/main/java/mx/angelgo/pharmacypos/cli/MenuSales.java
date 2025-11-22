package mx.angelgo.pharmacypos.cli;

import mx.angelgo.pharmacypos.dao.*;
import mx.angelgo.pharmacypos.model.*;
import java.util.*;

public class MenuSales {
    private final SalesDao sDao;
    private final ProductDao pDao;
    private final long userId;

    private final Scanner scan = new Scanner(System.in);

    public MenuSales(SalesDao sDao, ProductDao pDao, long userId) {
        this.sDao = sDao;
        this.pDao = pDao;
        this.userId = userId;
    }

    public void show() {
        List<SaleItem> items = new ArrayList<>();

        while(true){
            System.out.println("\n--- MENU VENTAS");
            System.out.println("1. Agregar producto");
            System.out.println("2. Finalizar venta");
            System.out.println("0. Volver");
            int op = Integer.parseInt(scan.nextLine());

            switch(op){
                case 1 -> addItems(items);
                case 2 -> finishSale(items);
                case 0 -> { return; }
            }
        }
    }

    private void addItems(List<SaleItem> items){
        try{
            System.out.print("ID producto: ");
            long id = Long.parseLong(scan.nextLine());

            var p = pDao.findById(id);
            if(p == null){
                System.out.println("No existe.");
                return;
            }

            System.out.print("Cantidad: ");
            int cant = Integer.parseInt(scan.nextLine());

            items.add(new SaleItem(id, cant, p.getPrice()));
            System.out.println("Agregado.");
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    private void finishSale(List<SaleItem> items){
        try{
            sDao.createSale(userId, items);
            System.out.println("Venta completada.");
            items.clear();
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
}
