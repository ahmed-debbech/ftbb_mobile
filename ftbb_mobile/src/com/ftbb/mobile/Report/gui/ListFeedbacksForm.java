/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.ftbb.mobile.Report.services.FeedbackService;

/**
 *
 * @author narug
 */
public class ListFeedbacksForm extends Form {

    public ListFeedbacksForm() {
        
        this.setTitle("Feedbacks List");
        this.setLayout(BoxLayout.y());
        
        SpanLabel feedbacksListSP = new SpanLabel();
        feedbacksListSP.setText(FeedbackService.getInstance().getFeedbacks().toString());
        
        this.add(feedbacksListSP);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeFeedback().showBack());
        
        
        
    }
    
    
}
