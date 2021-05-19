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
import com.ftbb.mobile.entities.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.ftbb.mobile.entities.Product;
import com.ftbb.mobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author root
 */
public class ServiceCart {
    
    public static String CLIENT_ID="2";
    //var
    boolean resultOK;
    ConnectionRequest req;
    private static ServiceCart instance;
    ArrayList<Product> products = new ArrayList<>();
    
    ArrayList<Command> cmds = new ArrayList<>();
    //constructor
    private ServiceCart() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static ServiceCart getInstance(){
        
        if (instance == null) {
            instance = new ServiceCart();
        }
        
        return instance;
    }
     public ArrayList<Command> parsecmd (String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> productsListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> productsList = (ArrayList<Map<String,Object>>) productsListJson.get("root");
            
            for (Map<String, Object> obj : productsList) {
                
                Command p = new Command();
                p.setCommand_id((int) Double.parseDouble(obj.get("commandId").toString()));
                p.setDate_command(obj.get("dateCommand").toString());
                p.setStatus((int)Double.parseDouble(obj.get("status").toString()));
                p.setTotal_price((int)Double.parseDouble(obj.get("totalPrice").toString()));
                
                cmds.add(p);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        return cmds;  
    }
    public ArrayList<Command> getAllCmds(){
        cmds = new ArrayList<>();
        String url = Statics.BASE_URL+"/mobile/command/list_command_client/"+CLIENT_ID;
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             cmds = parsecmd(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return cmds;
    }
    //ADD PRODUCT
    public void addCart(Product p){ //Créer la méthode addProduct
        
        String url = Statics.BASE_URL+"/mobile/cart/add/"+p.getRef_product()+"/"+CLIENT_ID;
        ConnectionRequest req = new ConnectionRequest(url); //Créer la requête de connexion
                 req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){ //Ajouter une actoin à la reception de la réponse
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j = new JSONParser();
                Map<String, Object> list;
                try {
                    System.out.println("eeeee");
                    String vv = new String(req.getResponseData());
                    System.out.println("3" +vv);
                    list = j.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    ArrayList<Map<String,Object>> dd = (ArrayList<Map<String,Object>>) list.get("root");
                                    String val = dd.get(0).get("exists").toString();
                                    System.out.println("vvv " + val);
                                    
                    if(val.equals("1")){
                        Dialog.show("Alert", "Item already exists",null, "OK");
                    }
                } catch (IOException ex) {
                }
            }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
     //PARSE PRODUCTS JSON : convert JSON to java objects
    public ArrayList<Product> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> productsListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> productsList = (ArrayList<Map<String,Object>>) productsListJson.get("root");
            //System.out.println("pdd " + productsList.toString());
            Map<String, Object> cart = productsList.get(0);
            //System.out.println("cc " + cart);
            //System.out.println("ggg" + cart.get("cart"));
            ArrayList<Map<String,Object>> lis = (ArrayList<Map<String,Object>>) cart.get("cart");
            //System.out.println("lista " + lis.toString());
            for(Map<String, Object> en : lis){
                Product p = new Product();
                p.setDetails(en.get("numProducts").toString());
                p.setRef_product((int) Double.parseDouble(en.get("refProduct").toString()));
                p.setPrice((int) Float.parseFloat(en.get("totalPrice").toString()));
                products.add(p);
            }
            
            Map<String, Object> data = productsList.get(1);
            ArrayList<Map<String,Object>> llis = (ArrayList<Map<String,Object>>) data.get("data");
            System.out.println("dddd" + llis.toString());
            int i=0;
            for(Map<String, Object> en : llis){
                Product pro = products.get(i);
                pro.setName(en.get("name").toString());
                pro.setPhoto("http://127.0.0.1/ftbb_web/ftbb_web/public/images/prod/"+en.get("photo").toString());
                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return products;  
    }
    public ArrayList<Product> showCart(){
        products = new ArrayList<>();
        String url = Statics.BASE_URL+"/mobile/product/cart/"+CLIENT_ID;
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

    public void remove(int ref_product) {
        String url = Statics.BASE_URL+"/mobile/cart/supprimer/"+ref_product;
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 Dialog.show("Alert", "Deleted!",null, "OK");
             
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
    }
    public void passCommand(){
         String url = Statics.BASE_URL+"/mobile/command/add_new_command/"+CLIENT_ID;
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 Dialog.show("Alert", "Command passed successfully!",null, "OK");
             
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
    }
    public void addQuant(int k, int id){
        String url = Statics.BASE_URL+"/mobile/cart/quant/"+k+"/"+CLIENT_ID+"/"+id;
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
             
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
    }
}
