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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import gui.MainForm;

/**
 *
 * @author narug
 */
public class HomeFeedback extends Form{
    
    public HomeFeedback(){
        Resources theme=UIManager.initFirstTheme("/reportui");    
        this.setTitle("Home Feedback");
        this.setLayout(BoxLayout.y());
        
        Button addFeedbackBtn = new Button("Add Feedback");
        addFeedbackBtn.setUIID("HomeForAllButtons");
        
        Button listFeedbacksBtn = new Button("Feedbacks List");
        listFeedbacksBtn.setUIID("HomeForAllButtons");
        
        addFeedbackBtn.addActionListener(e -> new AddFeedbackForm().show());
        listFeedbacksBtn.addActionListener(x-> new ListFeedbacksForm().show());
        
        this.addAll(addFeedbackBtn, listFeedbacksBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new MainForm().showBack());

}
}
