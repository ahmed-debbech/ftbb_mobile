/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Product;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author PC
 */
public class ServicesProduct {
    
    public boolean resultOK;//Créer une variable pour le resultat d'ajout
    
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
    
}
