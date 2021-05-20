/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import models.Competition;
import services.CompetitionService;

/**
 *
 * @author Lenovo
 */
public class AddCompetitionForm extends Form {
    
    public AddCompetitionForm(){
        this.setTitle("Add Competition");
        this.setLayout(BoxLayout.y());
        
        TextField tfname = new TextField("", "Insert Competition Name");
        
        Button submitTaskBtn = new Button("Submit");
        submitTaskBtn.addActionListener((evt) -> {
            
            if (tfname.getText().length() ==0 ) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                try {
                    
                Competition comp = new Competition(tfname.getText(),null);
                    if (CompetitionService.getInstance().addCompetitionAction(comp)) {
                        Dialog.show("Success", "Competition added successfully.",null, "OK");
                       
                    }
                    
                } catch (NumberFormatException e) {
                    Dialog.show("Alert", "Task's status must be a number.",null, "OK");
                }        
            }          
        });
         
        this.addAll(tfname,  submitTaskBtn);
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        
        
    }
    
    
    
}
