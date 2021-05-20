/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import models.*;
import services.*;

import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.DefaultTableModel;
import com.codename1.ui.table.Table;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableModel;
import com.codename1.ui.util.Resources;
import java.util.Hashtable;
import java.util.Vector;


/**
 *
 * @author Lenovo
 */
public class FavUI  extends Form {

     List<Object> games = new ArrayList<>(); 
     public Resources theme;
     String [] files = new String[500];
     
   
    public FavUI()
    {
          theme = UIManager.initFirstTheme("/theme");
       
        this.setTitle("Game List");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        
          for (int i = 0 ; i< 15 ; i++)
        {
               Object val =  Storage.getInstance().readObject(Integer.toString(i));
               if(val != null )
               {
                   games.add(val);
               }            
        }
          
          
          
          
          
          
        InfiniteContainer ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {

                if (index == 0) {
                    amount = games.size();
                }
                if (amount + index > games.size()) {
                    amount = games.size() - index;
                }
                if (index < 0) {
                    return null;
                }
                Component[] cmps = new Component[amount];
                for (int iter = index; iter < amount; iter++) {
                    Object p = games.get(iter);
 
                    if (p == null) {
                        return null;
                    }
                                                                               
                String score_home = p.toString();
                String score_away =  p.toString();
              
             //   EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 150, CENTER), true);
		//Image airf_serv = URLImage.createToStorage(placeholder, p.getLogoHome(), "http://127.0.0.1:8000/uploads/logo/"+p.getLogoHome(), URLImage.RESIZE_SCALE);
		//ImageViewer imgViewer = new ImageViewer(airf_serv);
                
                
               
                Container element = new Container(BoxLayout.x().xCenter());
             //   element.add(imgViewer);
                Container infos = new Container(BoxLayout.y());
                //infos.add(new Label(id));
                infos.add(new Label(score_home + " - " + score_away));
           //     infos.add(new Label(p.getSalle()));
             
          
                    
                //EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(this.getWidth(), this.getWidth() / 5, CENTER), true);
		//Image airf_serv1 = URLImage.createToStorage(placeholder1, p.getLogoAway(), "http://127.0.0.1:8000/uploads/logo/"+p.getLogoAway(), URLImage.RESIZE_SCALE_TO_FILL);
		//ImageViewer imgViewer1 = new ImageViewer(airf_serv1);
                
               
                
                element.add(infos);
                
              //   element.add(imgViewer1);
                    
               //  Button afficher = new Button();
             
                  //  element.setLeadComponent(afficher);
                    cmps[iter] = element;
                }
                return cmps;
            }
        };
        ic.setScrollableY(false);
     
        
        

        //  initLabel.setText(games.toString());  
    
//this.add(initLabel);
this.add(ic);
//this.add(table);
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeFormComp().showBack());
}
}
