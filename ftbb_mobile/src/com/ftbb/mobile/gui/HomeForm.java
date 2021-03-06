/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author PC
 */
public class HomeForm extends Form{
    Form current;
    public HomeForm(){
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddProduct = new Button("Add Product");
        Button btnListProduct = new Button("List Product");
        
        btnAddProduct.addActionListener(e-> new AddProductForm(current).show());
        btnListProduct.addActionListener(e-> new ListProductForm(current).show());
        addAll(btnAddProduct,btnListProduct);
    }
    
}
