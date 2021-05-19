/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ftbb.mobile.Report.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author narug
 */
public class HomeForAll extends Form{

    public HomeForAll() {
        Resources theme=UIManager.initFirstTheme("/reportui");
        this.setLayout(BoxLayout.y());
        Button ReportBtn = new Button(" Report");
        ReportBtn.setUIID("HomeForAllButtons");
        
        Button FeedbackBtn = new Button("Feedback");
        FeedbackBtn.setUIID("HomeForAllButtons");
        
        Button GalerieBtn = new Button("Galerie");  
        GalerieBtn.setUIID("HomeForAllButtons");
        
        ReportBtn.addActionListener(e -> new HomeReport().show());
        FeedbackBtn.addActionListener(x-> new HomeFeedback().show());
        GalerieBtn.addActionListener(y-> new GalerieView().show());
        
        this.addAll(ReportBtn, FeedbackBtn,GalerieBtn);
    }
}
