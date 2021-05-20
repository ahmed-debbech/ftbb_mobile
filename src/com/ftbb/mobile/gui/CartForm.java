/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.NumericSpinner;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.ftbb.mobile.entities.Product;
import com.ftbb.mobile.services.ServiceCart;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class CartForm extends Form{
    int somme;
    
    public CartForm( ){
        setTitle("Your Cart");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new ListProductForm(null).showBack());
        
        Resources theme = UIManager.initFirstTheme("/storeui");
        UIBuilder.registerCustomComponent("ImageViewer",  com.codename1.components.ImageViewer.class);
        UIBuilder.registerCustomComponent("Picker",  Picker.class);
        UIBuilder ui = new UIBuilder();
        Form grid_prod = (Form)ui.createContainer(theme, "cart");
        int mm = Display.getInstance().convertToPixels(3);
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 3, mm * 4, 0), false);
        int i=0;
        ArrayList<Product> list = ServiceCart.getInstance().showCart();
        System.out.println("listtt" + list.toString());
        for(Product p : list){
            Container prod = ui.createContainer(theme, "prod_cart");
            
            ImageViewer iv = (ImageViewer) prod.getComponentAt(0);
            Image image = URLImage.createToStorage(placeholder, "nnicon"+i, p.getPhoto());
            iv.setImage(image);
            Label name = (Label) prod.getComponentAt(1);
            name.setText(p.getName());
            Label price = (Label) prod.getComponentAt(2);
            price.setText(p.getPrice() + "DT");
            Picker bb = (Picker) prod.getComponentAt(3);
            bb.setType(Display.PICKER_TYPE_STRINGS);
            bb.setStrings("1","2","3","4","5","6","7","8","9");
            int q = (int)Double.parseDouble(p.getDetails());
            somme += p.getPrice();
            Button delete = (Button)prod.getComponentAt(4);
            delete.addActionListener((e) -> {
                ServiceCart.getInstance().remove(p.getRef_product());
                new CartForm().show();
            });
            bb.addActionListener((e) -> {
                int quant = Integer.parseInt(bb.getSelectedString());
                price.setText((quant * p.getPrice()) +"");
                ServiceCart.getInstance().addQuant(quant,p.getRef_product());
            });
            grid_prod.add(prod);
            i++;
        }
        Container cntt = (Container)grid_prod.getComponentAt(0);
        System.out.println("c" + cntt.toString());
        Label somme = (Label)cntt.getComponentAt(0);
        somme.setText(this.somme+"DT");
        Button pass = (Button)cntt.getComponentAt(1);
        pass.addActionListener((e)->{
            ServiceCart.getInstance().passCommand();
        });
        Button cmds = (Button)cntt.getComponentAt(2);
        cmds.addActionListener((e)->{
            new CommandForm(Integer.parseInt(ServiceCart.CLIENT_ID)).show();
        });
        this.add(grid_prod);
    }
}
