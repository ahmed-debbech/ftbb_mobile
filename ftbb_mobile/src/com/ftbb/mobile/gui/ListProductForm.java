/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.ftbb.mobile.entities.Product;
import com.ftbb.mobile.services.ServicesProduct;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class ListProductForm extends Form{

    public ListProductForm(Form previous) {
        setTitle("List Product");
        this.setLayout(BoxLayout.y());
        
        ArrayList<Product> list = ServicesProduct.getInstance().getProducts();
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new HomeForm().showBack());
        
        Resources theme = UIManager.initFirstTheme("/storeui");
        //UIBuilder.registerCustomComponent("ImageViewer",  com.codename1.components.ImageViewer.class);
        UIBuilder ui = new UIBuilder();
        Form grid_prod = (Form)ui.createContainer(theme, "store");
        for(Product p : list){
            Container prod = ui.createContainer(theme, "product");
            Label name = (Label) prod.getComponentAt(1);
            name.setText(p.getName());
            grid_prod.add(prod);
        }
        this.add(grid_prod);
    }
    
    
    
}
