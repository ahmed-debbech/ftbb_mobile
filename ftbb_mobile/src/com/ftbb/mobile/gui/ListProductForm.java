/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author PC
 */
public class ListProductForm extends Form{

    public ListProductForm(Form previous) {
        setTitle("List Product");
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
    
}
