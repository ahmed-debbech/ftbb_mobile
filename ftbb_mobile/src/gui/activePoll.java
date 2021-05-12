/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author sbs
 */
public class activePoll extends Form {
    public activePoll(){
        
        this.setTitle("Active Poll");
        this.setLayout(BoxLayout.y());      
        Button endedpollBtn = new Button("Ended Poll");
        
        endedpollBtn.addActionListener(e -> new endedPoll().show());
 
        this.addAll(endedpollBtn);
        
    }
        
    }


