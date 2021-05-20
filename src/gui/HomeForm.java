/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.Storage;
import com.codename1.ui.Component;
import java.util.ArrayList;
import java.util.List;
import models.*;
/**
 *
 * @author khaledguedria
 */
public class HomeForm extends Form{
    
        public List<Object> listGames = new ArrayList<>();
        
    public HomeForm(){
        
        this.setTitle("Home Form");
        this.setLayout(BoxLayout.y());
      
        Button addTaskBtn = new Button("Add Competitions");
        Button listCompetitionBtn = new Button("Competitions List");
        Button listClassementBtn = new Button("Classement List");
        Button listGameBtn = new Button("Game List");
        Button listFavBtn = new Button("Fav List");

        
        addTaskBtn.addActionListener(e -> new AddCompetitionForm().show());
        listCompetitionBtn.addActionListener(a-> new ListCompetitionForm().show());
        listClassementBtn.addActionListener(b-> new ClassementUI().show());
        listGameBtn.addActionListener(c-> new GameUI().show());
        listFavBtn.addActionListener(d-> new FavUI().show());
        
        this.addAll(addTaskBtn, listCompetitionBtn ,listClassementBtn ,listGameBtn,listFavBtn);
        for (Object c : listGames) {
            System.out.println(c.toString());
        }
    }
    
}
