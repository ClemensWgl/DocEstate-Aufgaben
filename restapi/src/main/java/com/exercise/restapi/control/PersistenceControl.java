package com.exercise.restapi.control;

import com.exercise.restapi.entites.Product;

import java.io.*;
import java.util.List;

public class PersistenceControl {
    private String saveLocation = "products.txt";
    private FileOutputStream fileOut;
    private FileInputStream fileIn;
    private ObjectOutputStream objOut;
    private ObjectInputStream objIn;

    public void save(List<Product> products){
        try{
            fileOut = new FileOutputStream(saveLocation);
            objOut = new ObjectOutputStream(fileOut);

            objOut.writeObject(products);

            fileOut.close();
            objOut.close();
            System.out.println("Speichern erfolgreich");
        } catch (FileNotFoundException e) {
           System.out.println("Datei nicht gefunden");
        } catch (IOException e) {
            System.out.println("IO-Fehler");
        }
    }

    public List<Product> load(){
        List<Product> listOfProducts =  null;

        try{
            fileIn = new FileInputStream(saveLocation);
            objIn = new ObjectInputStream(fileIn);

            Object object = objIn.readObject();

            if(object instanceof List<?>){
                listOfProducts = (List) object;
                return listOfProducts;
            }
            System.out.println("Laden erfolgreich");
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden");
        } catch (IOException e) {
            System.out.println("IO-Fehler");
        } catch (ClassNotFoundException e) {
            System.out.println("Datei ist ungültig");
        }
        return null;
    }
}
