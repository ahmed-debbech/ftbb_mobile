/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.ftbb.mobile.services.ServicesProduct;

/**
 *
 * @author PC
 */
public class ListProductForm extends Form{

    public ListProductForm(Form previous) {
        setTitle("List Product");
        this.setLayout(BoxLayout.y());
        
        SpanLabel productListSP = new SpanLabel();
        productListSP.setText(ServicesProduct.getInstance().getProducts().toString());
        
        this.add(productListSP);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new HomeForm().showBack());
        
    }
    
    
    
}
