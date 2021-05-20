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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.Table;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.ftbb.mobile.entities.Command;
import com.ftbb.mobile.entities.Product;
import com.ftbb.mobile.services.ServiceCart;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class CommandForm extends Form {
    
    public CommandForm(int cli){
        setTitle("Your commands");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new CartForm().showBack());
        
        Resources theme = UIManager.initFirstTheme("/storeui");
        UIBuilder.registerCustomComponent("ImageViewer",  com.codename1.components.ImageViewer.class);
        UIBuilder.registerCustomComponent("Picker",  Picker.class);
        UIBuilder.registerCustomComponent("Table",  Table.class);
        UIBuilder ui = new UIBuilder();
        //Form cmdlist = ui.createContainer(theme, "command");
        ArrayList<Command> list = ServiceCart.getInstance().getAllCmds();
        for(Command p : list){
            Container rr = ui.createContainer(theme, "command");
            Container prod = (Container)rr.getComponentAt(0);
            Label name = (Label) prod.getComponentAt(0);
            name.setText(p.getCommand_id() + "");
            Label date = (Label) prod.getComponentAt(1);
            String gg = p.getDate_command()+ "";
            date.setText(gg.substring(0, 10));
            Label st = (Label) prod.getComponentAt(2);
            if(p.getStatus() == 1){
            st.setText("Valid");
            }else{
                st.setText("Not Valid");
            }
            Label price = (Label) prod.getComponentAt(3);
            price.setText(p.getTotal_price()+ "DT");
           this.add(rr);
        }

    }
}
