package com.exercise.restapi.entites;

import java.io.Serializable;

public class Product implements Serializable {

    private final long id;
    private final String name;
    private final float price;

    public Product(long id, String name, float price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public float getPrice(){
        return price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}
