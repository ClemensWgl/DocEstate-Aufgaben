package com.exercise.restapi;

import com.exercise.restapi.control.PersistenceControl;
import com.exercise.restapi.control.ProductController;
import com.exercise.restapi.entites.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestapiApplicationTests {

    ProductController productController = new ProductController();

    @Test
    void addProductTest(){
        /** Teste Produkt hinzufügen */
        assertTrue(productController.addProduct(0, "TEST",(float)0.99));

        /** Teste ob Produkt in Liste gespeichert wurde*/
        Product product = null;
        for(Product p : productController.getProducts()){
            if(p.getId() == 0){
                product = p;
            }
        }
        assertNotNull(product);

        /** Produkt sollte nicht doppelt hinzugefügt werden können */
        assertFalse(productController.addProduct(0, "TEST",(float)0.99));

        /** Teste Produkt löschen */
        assertTrue(productController.delteProduct(0));

        /** Teste ob Produkt in Liste gelöscht wurde*/
        product = null;
        for(Product p : productController.getProducts()){
            if(p.getId() == 0){
                product = p;
            }
        }
        assertNull(product);

        /** Bereits gelöschte Produkte sollten nicht nocheinmal gelöscht werden können*/
        assertFalse(productController.delteProduct(0));
    }

}
