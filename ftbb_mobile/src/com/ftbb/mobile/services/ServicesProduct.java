/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.ftbb.mobile.entities.Product;
import com.ftbb.mobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author PC
 */
public class ServicesProduct {
    
    
    //var
    boolean resultOK;
    ConnectionRequest req;
    static ServicesProduct instance;
    ArrayList<Product> products = new ArrayList<>();
    
    
    //constructor
    private ServicesProduct() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static ServicesProduct getInstance(){
        
        if (instance == null) {
            instance = new ServicesProduct();
        }
        
        return instance;
    }
    
    
    //ADD PRODUCT
    public boolean addProduct(Product p){ //Créer la méthode addProduct
        
        String url = Statics.BASE_URL+"/mobile/product/formulaire_ajout_admin?photo="+p.getPhoto()+"&category="+p.getCategory()+"&stock="+p.getStock()+"&price="+p.getPrice()+"&name="+p.getName()+"&id_admin="+p.getId_admin()+"&details="+p.getDetails();//Créerl'url
        ConnectionRequest req = new ConnectionRequest(url); //Créer la requête de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>(){ //Ajouter une actoin à la reception de la réponse
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode()==200; //Vérifier le status de la réponse //Code HTTP 200 OK
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     //PARSE PRODUCTS JSON : convert JSON to java objects
    public ArrayList<Product> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> productsListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> productsList = (ArrayList<Map<String,Object>>) productsListJson.get("root");
            
            for (Map<String, Object> obj : productsList) {
                
                Product p = new Product();
                p.setRef_product((int) Integer.parseInt(obj.get("ref_product").toString()));
                p.setCategory(obj.get("category").toString());
                p.setName(obj.get("name").toString());
                p.setDetails(obj.get("details").toString());
                p.setPhoto(obj.get("photo").toString());
                p.setStock((int) Integer.parseInt(obj.get("stock").toString()));
                p.setPrice((int) Integer.parseInt(obj.get("price").toString()));
                p.setId_admin((int) Integer.parseInt(obj.get("id_admin").toString()));
                
                
                products.add(p);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return products;  
    }



    //GET PRODUCTS
    public ArrayList<Product> getProducts(){
        
         String url = Statics.BASE_URL+"/mobile/product/list_product_admin/";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             products = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return products;
    }
    
}
