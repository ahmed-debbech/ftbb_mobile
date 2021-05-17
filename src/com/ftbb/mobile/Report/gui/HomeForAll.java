/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;

/**
 *
 * @author narug
 */
public class HomeForAll extends Form{

    public HomeForAll() {
        
        Button ReportBtn = new Button(" Report");
        ReportBtn.setUIID("Report");
        Button FeedbackBtn = new Button("Feedback");
        FeedbackBtn.setUIID("Feedback");
        
        Button GalerieBtn = new Button("Galerie");
        GalerieBtn.setUIID("Galerie");
        
        ReportBtn.addActionListener(e -> new HomeReport().show());
        FeedbackBtn.addActionListener(x-> new HomeFeedback().show());
        GalerieBtn.addActionListener(y-> new GalerieView().show());
        
        this.addAll(ReportBtn, FeedbackBtn,GalerieBtn);
    }
}
