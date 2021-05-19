/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.ftbb.mobile.entities.Product;
import com.ftbb.mobile.services.ServiceCart;
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
        UIBuilder.registerCustomComponent("ImageViewer",  com.codename1.components.ImageViewer.class);
        UIBuilder ui = new UIBuilder();
        Form grid_prod = (Form)ui.createContainer(theme, "store");
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
        int i=0;
        Container cnt = (Container)grid_prod.getComponentAt(0);
        Button tt = (Button)cnt.getComponentAt(0);
        tt.addActionListener(e->{
                new CartForm().show();
        });
        for(Product p : list){
            Container prod = ui.createContainer(theme, "product");
            
            ImageViewer iv = (ImageViewer) prod.getComponentAt(0);
            Image image = URLImage.createToStorage(placeholder, "picon"+i, p.getPhoto());
            iv.setImage(image);
            Label name = (Label) prod.getComponentAt(1);
            name.setText(p.getName());
            Label price = (Label) prod.getComponentAt(2);
            price.setText(p.getPrice() + "DT");
            Button bb = (Button) prod.getComponentAt(3);
            bb.addActionListener(e->{
                ServiceCart.getInstance().addCart(p);
            });
            grid_prod.add(prod);
            i++;
        }
        this.add(grid_prod);
    }
    
    
    
}
