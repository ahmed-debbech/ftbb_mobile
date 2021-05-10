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
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.ftbb.mobile.Report.models.Feedback;
import com.ftbb.mobile.Report.services.FeedbackService;

/**
 *
 * @author narug
 */
public class AddFeedbackForm extends Form {

    public AddFeedbackForm() {
    
    this.setTitle("Add Feedback");
        this.setLayout(BoxLayout.y());
        
        TextField tfemail = new TextField("", "Insert email");
        TextField tftopic=new TextField("","Insert topic");
        TextField tftype=new TextField("","Insert type");
        TextField tftext=new TextField("","Insert text");

        Button submitFeedbackBtn = new Button("Submit");
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
    
    
}
