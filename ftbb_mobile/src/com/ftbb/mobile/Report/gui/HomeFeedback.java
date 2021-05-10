/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author narug
 */
public class HomeFeedback extends Form{
    
    public HomeFeedback(){
            
        this.setTitle("Home Feedback");
        this.setLayout(BoxLayout.y());
        
        Button addFeedbackBtn = new Button("Add Feedback");
        Button listFeedbacksBtn = new Button("Feedbacks List");
        
        addFeedbackBtn.addActionListener(e -> new AddFeedbackForm().show());
        listFeedbacksBtn.addActionListener(x-> new ListFeedbacksForm().show());
        
        this.addAll(addFeedbackBtn, listFeedbacksBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForAll().showBack());

}
}
