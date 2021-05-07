/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.entities;


/**
 *
 * @author PC
 */
public class Product {
    
    private int ref_product, stock, price, id_admin;
    private String category, name, details, photo;

    public Product(int ref_product, int stock, int price, int id_admin, String category, String name, String details, String photo) {
        this.stock = stock;
        this.price = price;
        this.id_admin = id_admin;
        this.category = category;
        this.name = name;
        this.details = details;
        this.photo = photo;
        this.ref_product = ref_product;
    }

    public Product(int stock, int price, int id_admin, String category, String name, String details, String photo) {
        this.stock = stock;
        this.price = price;
        this.id_admin = id_admin;
        this.category = category;
        this.name = name;
        this.details = details;
        this.photo = photo;
    }

    public Product() {
    }
    

    public int getRef_product() {
        return ref_product;
    }

    public void setRef_product(int ref_product) {
        this.ref_product = ref_product;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Product{" + "ref_product=" + ref_product + ", stock=" + stock + ", price=" + price + ", id_admin=" + id_admin + ", category=" + category + ", name=" + name + ", details=" + details + ", photo=" + photo + '}';
    }
    
    
    
}
