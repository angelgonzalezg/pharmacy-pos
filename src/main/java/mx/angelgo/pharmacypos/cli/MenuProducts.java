package mx.angelgo.pharmacypos.cli;

import mx.angelgo.pharmacypos.dao.ProductDao;

public class MenuProducts {
    private final ProductDao pDao;

    public MenuProducts(ProductDao pDao) {
        this.pDao = pDao;
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
