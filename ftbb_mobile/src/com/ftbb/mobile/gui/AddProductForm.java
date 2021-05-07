/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.ftbb.mobile.entities.Product;
import com.ftbb.mobile.services.ServicesProduct;

/**
 *
 * @author PC
 */
public class AddProductForm extends Form{

    public AddProductForm(Form previous) {
        setTitle("Add a new product");
        //Créer l'interface d'ajout
        setLayout(BoxLayout.y());
        
        TextField tfCategory = new TextField("","Category: Vetements - Equipements - Abonnements");
        TextField tfStock = new TextField("","ProductStock");
        TextField tfName = new TextField("","ProductName");
        TextField tfPrice = new TextField("","ProductPrice");
        TextField tfDetails = new TextField("","ProductDetails");
        TextField tfIdAdmin = new TextField("","ProductIdAdmin");
        TextField tfPhoto = new TextField("","ProductPhoto");
        Button btnValider = new Button("Add product");
        
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                //Vérifier les données saisies par l'utilisateur
                if((tfCategory.getText().length()==0)||(tfName.getText().length()==0)||(tfDetails.getText().length()==0)||(tfPhoto.getText().length()==0) ){
                    Dialog.show("Alert","Please fill all the fields" , new Command("OK"));
                }
                else{
                    try{
                        Product p = new Product(333, Integer.parseInt(tfStock.getText()),Integer.parseInt(tfPrice.getText()),Integer.parseInt(tfIdAdmin.getText()),tfCategory.getText(),tfName.getText(),tfDetails.getText(),tfPhoto.getText());
                        //Invoquer la methode d'ajout 
                        if(ServicesProduct.getInstance().addProduct(p))
                        {
                            Dialog.show("Success", "connection accepted", new Command("OK"));
                        }
                        else{
                            Dialog.show("ERROR","Server error", new Command("OK"));
                        }
                    }
                    catch(NumberFormatException e){
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
                
            }
            
        });
        addAll(tfCategory,tfName,tfPrice,tfDetails,tfIdAdmin,tfPhoto,tfStock,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
