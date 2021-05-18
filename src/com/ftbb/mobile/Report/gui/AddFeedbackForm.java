/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.ftbb.mobile.Report.models.Feedback;
import com.ftbb.mobile.Report.services.FeedbackService;

/**
 *
 * @author narug
 */
public class AddFeedbackForm extends Form {

    public AddFeedbackForm() {
    Resources theme=UIManager.initFirstTheme("/reportui");
    this.setTitle("Add Feedback");
        this.setLayout(BoxLayout.y());
        
        TextField tfemail = new TextField("", "Insert email");
        tfemail.setUIID("TextFields");
        TextField tftopic=new TextField("","Insert topic");
        tftopic.setUIID("TextFields");
        TextField tftype=new TextField("","Insert type");
        tftype.setUIID("TextFields");
        TextField tftext=new TextField("","Insert text");
        tftext.setUIID("TextFields");

        Button submitFeedbackBtn = new Button("Submit");
        submitFeedbackBtn.setUIID("HomeForAllButtons");
        submitFeedbackBtn.addActionListener((evt) -> {
            
            if (tfemail.getText().length() ==0 || tftopic.getText().length()==0 || tftype.getText().length()==0 || tftext.getText().length()==0 ) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                try {
                    
                Feedback f= new Feedback();
                f.setEmail(tfemail.getText());
                f.setType(tftype.getText());
                f.setTopic(tftopic.getText());
                f.setText(tftext.getText());
                
                    if (FeedbackService.getInstance().addFeedbackAction(f)) {
                        Dialog.show("Success", "Feedback added successfully.",null, "OK");
                    }
                    
                } catch (NumberFormatException e) {
                    Dialog.show("Alert", "Feedback's status must be a number.",null, "OK");
                }
                
                
                
            }
            
            
            
        });
        
        this.addAll(tfemail,tftext,tftopic,tftype, submitFeedbackBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeFeedback().showBack());
    
    
    }
    public AddFeedbackForm(int id){
        Resources theme=UIManager.initFirstTheme("/reportui");
        this.setTitle("Modify Feedback");
        this.setLayout(BoxLayout.y());
        
        Label ied = new Label("ID: " + id);
        
        TextField tftext=new TextField("","Insert desc");
        tftext.setUIID("TextFields");
        
        Button submitFeedbackBtn = new Button("Submit");
        submitFeedbackBtn.setUIID("HomeForAllButtons");
        submitFeedbackBtn.addActionListener((evt) -> {
            
            if (tftext.getText().length()==0 ) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                try {
                    
                Feedback f= new Feedback();
                
                f.setText(tftext.getText());
                   FeedbackService.getInstance().modifyNowFed(f);
                        Dialog.show("Success", "Feedback modified successfully.",null, "OK");
                    
                    
                } catch (NumberFormatException e) {
                    Dialog.show("Alert", "Feedback's status must be a number.",null, "OK");
                }
                
                
                
            }
            
            
            
        });
        
        
        this.addAll(ied,tftext, submitFeedbackBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeFeedback().showBack());
        
    }
    
    
}
