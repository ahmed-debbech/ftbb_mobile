/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.ftbb.mobile.Report.models.Feedback;
import com.ftbb.mobile.Report.models.Report;
import com.ftbb.mobile.Report.services.FeedbackService;
import java.util.ArrayList;

/**
 *
 * @author narug
 */
public class ListFeedbacksForm extends Form {

    public ListFeedbacksForm() {
        
        this.setTitle("Feedbacks List");
        this.setLayout(BoxLayout.y());
        
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeFeedback().showBack());
        
        ArrayList<Feedback> ar = FeedbackService.getInstance().getFeedbacks();
        
    }
    
    
}
