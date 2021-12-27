package com.exercise.restapi.control;

import com.exercise.restapi.entites.Product;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

    PersistenceControl persistenceControl = new PersistenceControl();
    private List<Product> listOfProducts;

    public ProductController(){
        /** Lade gespeicherte Produkte aus Datei */;
        listOfProducts = persistenceControl.load();
    }

    @GetMapping("/product/products")
    public List<Product> getProducts(){
        System.out.println("Übergebe Produktliste");
        return listOfProducts;
    }

    @PostMapping("/product")
    public boolean addProduct(@RequestParam(value = "id") long id,@RequestParam(value = "name") String name, @RequestParam(value = "price") float price){
        boolean idOk = true;
        /** Prüfe ob Id bereits vergeben ist */
        for(Product p : listOfProducts){
            if(p.getId() == id){
                idOk = false;
                break;
            }
        }
        if(idOk){
            /** Wenn Id noch nicht vergeben ist Produkt hinzufügen */
            listOfProducts.add(new Product(id,name, price));
        }
        System.out.println("Hinzufügen war " + (idOk ? "erfolgreich" : "nicht erfolgreich"));

        /** Änderungen in die persistente Datei schreiben */
        persistenceControl.save(listOfProducts);

        return idOk;
    }

    @DeleteMapping("/product/{id}")
    public boolean delteProduct(@PathVariable long id){
        boolean deleteOk = false;
        /** Prüfe ob ein Produkt mit der Id existiert*/
        for(Product p : listOfProducts){
            if(p.getId() == id){
                listOfProducts.remove(p);
                deleteOk = true;
                break;
            }
        }
        System.out.println("Löschen war " + (deleteOk ? "erfolgreich" : "nicht erfolgreich"));

        /** Änderungen in die persistente Datei schreiben */
        persistenceControl.save(listOfProducts);

        return deleteOk;
    }
}
