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

import com.codename1.messaging.Message;
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
public class GameUI  extends Form {

     List<Competition> competitions = new ArrayList<>();    
     List<Phase> phases = new ArrayList<>(); 
     List<Game> games = new ArrayList<>(); 
     List<Week> weeks = new ArrayList<>(); 
     SpanLabel initLabel = new SpanLabel();
     SpanLabel CompetitionLabel = new SpanLabel();
     SpanLabel PhaseLabel = new SpanLabel();
     SpanLabel WeekLabel = new SpanLabel();
     public Resources theme;
     String [] files = new String[500];
     
   
    public GameUI()
    {
          theme = UIManager.initFirstTheme("/theme");
           System.out.println(theme.toString());
       
        this.setTitle("Game List");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        competitions =  CompetitionService.getInstance().getCompetitions();
        phases =  PhaseService.getInstance().getPhases();
        weeks =  WeekService.getInstance().getWeeks();
        
        ComboBox<Map<String, String>> comboCompetition = new ComboBox<>();
        ComboBox<Map<String, String>> comboPhase = new ComboBox<>();
        ComboBox<Map<String, String>> comboWeek = new ComboBox<>();
        
       
          for(Phase p : phases)
        {  
            Map<String, String> entry = new HashMap<>();
            entry.put("id", Integer.toString(p.getId()));
             entry.put("name", p.getName());
            comboPhase.addItem(entry);      
        }
        for(Competition c : competitions)
        {  
            Map<String, String> entry = new HashMap<>();
            entry.put("id", Integer.toString(c.getId()));
             entry.put("name", c.getName());
            comboCompetition.addItem(entry);      
        }
           for(Week w : weeks)
        {  
            Map<String, String> entry = new HashMap<>();
            entry.put("id", Integer.toString(w.getId()));
             entry.put("name", w.getName_week());
            comboWeek.addItem(entry);      
        }
         games.clear();
        games =  GameService.getInstance().showGameAction(Integer.parseInt(comboCompetition.getSelectedItem().get("id")) ,Integer.parseInt(comboPhase.getSelectedItem().get("id")) ,Integer.parseInt(comboWeek.getSelectedItem().get("id")) );
 
            
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
                    Game p = games.get(iter);
                    if (p == null) {
                        return null;
                    }
                   
                String score_home = Integer.toString(p.getScore_home());
                String score_away = Integer.toString(p.getScore_away());
              
                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 150, comboCompetition.CENTER), true);
		Image airf_serv = URLImage.createToStorage(placeholder, p.getLogoHome(), "http://127.0.0.1/ftbb_web/ftbb_web/public/uploads/logo/"+p.getLogoHome(), URLImage.RESIZE_SCALE);
		ImageViewer imgViewer = new ImageViewer(airf_serv);
                
                
               
                Container element = new Container(BoxLayout.x().xCenter());
                element.add(imgViewer);
                Container infos = new Container(BoxLayout.y());
                //infos.add(new Label(id));
                infos.add(new Label(score_home + " - " + score_away));
                infos.add(new Label(p.getSalle()));
             
                
                CheckBox cb4 = new CheckBox("Fav");
               
                    cb4.addActionListener((evt) -> {
                      if(cb4.isSelected()) {
                
                             for (int i = 0 ; i< 5 ; i++)
                                {     
                                    
                                    Message m = new Message("Game Added To Fav  "+p.getNameHome()+" vs "+p.getNameAway());
m.getAttachments().put("/src/test.txt", "text/plain");
m.getAttachments().put("/src/logo1.png", "image/png");
Display.getInstance().sendMessage(new String[] {"ali.dagdoug55@gmail.com"}, "Game Fav", m);
                                       Object val =  Storage.getInstance().readObject(Integer.toString(i));
                                       
                                       if(val == null )
                                       {
                                           Storage.getInstance().writeObject(Integer.toString(i),p.toString() );
                                           break;
                                       }             
                                }
                           cb4.setOppositeSide(false);
                      }else
                      {                   
                          System.out.println("walit not selected");
                           cb4.setOppositeSide(true);
                      }
                      
                      
                    });
                infos.add(cb4);
                    
                EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(this.getWidth(), this.getWidth() / 5, comboCompetition.CENTER), true);
		Image airf_serv1 = URLImage.createToStorage(placeholder1, p.getLogoAway(), "http://127.0.0.1/ftbb_web/ftbb_web/public/uploads/logo/"+p.getLogoAway(), URLImage.RESIZE_SCALE_TO_FILL);
		ImageViewer imgViewer1 = new ImageViewer(airf_serv1);
                
               
                
                element.add(infos);
                
                 element.add(imgViewer1);
                    
               //  Button afficher = new Button();
             
                  //  element.setLeadComponent(afficher);
                    cmps[iter] = element;
                }
                return cmps;
            }
        };
        ic.setScrollableY(false);
     
        
        

        initLabel.setText(games.toString());  
    comboCompetition.addActionListener(new ActionListener() {
    @Override 
    public void actionPerformed(ActionEvent evt) {
        games.clear();
        games =  GameService.getInstance().showGameAction(Integer.parseInt(comboCompetition.getSelectedItem().get("id")) ,Integer.parseInt(comboPhase.getSelectedItem().get("id")) ,Integer.parseInt(comboWeek.getSelectedItem().get("id")) );
           for(Game g : games)
        {  
            System.out.println( g.getId()); 
            System.out.println( g.getNameHome()); 
            System.out.println( g.getLogoHome()); 
        }
      initLabel.setText(games.toString());  

    }    
    }); 
          comboPhase.addActionListener(new ActionListener() {
    @Override 
    public void actionPerformed(ActionEvent evt) {
        games.clear();
          games =  GameService.getInstance().showGameAction(Integer.parseInt(comboCompetition.getSelectedItem().get("id")) ,Integer.parseInt(comboPhase.getSelectedItem().get("id")) ,Integer.parseInt(comboWeek.getSelectedItem().get("id")) );
           for(Game g : games)
        {  
            System.out.println( g.getId());    
        } 
           initLabel.setText(games.toString());  
    }    
    }); 
              comboWeek.addActionListener(new ActionListener() {
    @Override 
    public void actionPerformed(ActionEvent evt) {
        games.clear();
        games =  GameService.getInstance().showGameAction(Integer.parseInt(comboCompetition.getSelectedItem().get("id")) ,Integer.parseInt(comboPhase.getSelectedItem().get("id")) ,Integer.parseInt(comboWeek.getSelectedItem().get("id")) );
           for(Game g : games)
        {  
            System.out.println( g.getId());    
        }
           initLabel.setText(games.toString());  
    }    
    }); 
this.add(comboCompetition);
this.add(comboPhase);
this.add(comboWeek);
//this.add(initLabel);
this.add(ic);
//this.add(table);
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeFormComp().showBack());
}
    
  
    
}
